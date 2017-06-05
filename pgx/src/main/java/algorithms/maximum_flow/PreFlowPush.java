package algorithms.maximum_flow;

import algorithms.Algorithm;
import oracle.pgx.api.*;
import oracle.pgx.api.internal.AnalysisResult;
import oracle.pgx.common.types.PropertyType;

import java.util.concurrent.ExecutionException;

public class PreFlowPush extends Algorithm {

    public PreFlowPush() throws ExecutionException, InterruptedException {
        super();
    }

    public int maximalFlow(CompiledProgram pgm, PgxGraph graph, int source, int destination,
                                             String propName)
            throws ExecutionException, InterruptedException {
        GraphBuilder<Integer> builder = session.newGraphBuilder();

        for(PgxEdge edge : graph.getEdges()) {
            builder.addEdge(edge.getId(), (Integer) edge.getSource().getId(),
                    (Integer) edge.getDestination().getId());
        }

        for(PgxEdge edge : graph.getEdges()) {
            builder.addEdge(graph.getEdges().size() + edge.getId(), (Integer) edge.getDestination().getId(),
                    (Integer) edge.getSource().getId());
        }

        PgxGraph biDriectional = builder.build();

        EdgeProperty<Integer> capacity = biDriectional.createEdgeProperty(PropertyType.INTEGER);
        for(PgxEdge edge: graph.getEdges()) {
            capacity.set(biDriectional.getEdge(edge.getId()), edge.getProperty(propName));
        }

        for(PgxEdge edge: graph.getEdges()) {
            capacity.set(biDriectional.getEdge(edge.getId() + graph.getEdges().size()), 0);
        }

        VertexProperty<Integer, Integer> id = biDriectional.createVertexProperty(PropertyType.INTEGER);

        int i = 0;
        for(PgxVertex vertex: biDriectional.getVertices()) {
            id.set(vertex, i++);
        }

        int queue_size = graph.getVertices().size() * 100;

        AnalysisResult<Integer> result = pgm.run(biDriectional,
                id,
                graph.getVertices().size(),
                queue_size,
                graph.getVertex(source),
                graph.getVertex(destination),
                capacity,
                Integer.MAX_VALUE);

        return result.getReturnValue();
    }

}

package algorithms.sssp;

import algorithms.Algorithm;
import oracle.pgx.api.*;
import oracle.pgx.api.internal.AnalysisResult;
import oracle.pgx.common.types.PropertyType;

import java.util.concurrent.ExecutionException;

public class Sssp extends Algorithm {

    public Sssp() throws ExecutionException, InterruptedException {
        super();
    }

    public PgxMap<PgxVertex<Integer>, Double> sssp(CompiledProgram pgm, PgxGraph graph,
                int root, int [] landmarks) throws ExecutionException, InterruptedException {

        PgxMap<PgxVertex<Integer>, Double> cost = graph.createMap(PropertyType.VERTEX, PropertyType.DOUBLE);
        VertexProperty<Integer, Boolean> landmarkProp = graph.createVertexProperty(PropertyType.BOOLEAN);

        landmarkProp.fill(false);
        for (int landmark : landmarks) {
            landmarkProp.set(graph.getVertex(landmark), true);
        }

        AnalysisResult<Boolean> result = pgm.run(graph, graph.getEdgeProperty("cost"),
                graph.getVertex(root), landmarkProp, cost);

        if(result.getReturnValue() == true) {
            return cost;
        }
        return null;
    }
}

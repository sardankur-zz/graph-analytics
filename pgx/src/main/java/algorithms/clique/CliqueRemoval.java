package algorithms.clique;

import algorithms.Algorithm;
import oracle.pgx.api.*;
import oracle.pgx.api.internal.AnalysisResult;
import oracle.pgx.common.types.PropertyType;
import org.apache.commons.lang3.ArrayUtils;

import java.util.concurrent.ExecutionException;

public class CliqueRemoval extends Algorithm {

    public CliqueRemoval() throws ExecutionException, InterruptedException {
        super();
    }

    public void cliqueRemoval(CompiledProgram pgm, PgxGraph graph) throws ExecutionException,
            InterruptedException {
        int n = graph.getVertices().size();
//        int size = 2*n;
        VertexProperty<Integer, Integer> id =
                graph.createVertexProperty(PropertyType.INTEGER);
        for(PgxVertex vertex : graph.getVertices()) {
            id.set(vertex, (Integer) vertex.getId());
        }
//
//        PgxVertex<Integer> root = graph.getVertex(0);
//        for(PgxVertex vertex : graph.getVertices()) {
//            cliques.set(vertex, new PgxVect<Integer>(ArrayUtils.toObject(new int[size])));
//        }

        VertexSet<Integer> c_i = graph.createVertexSet();
        VertexSet<Integer> i_i = graph.createVertexSet();

        AnalysisResult<Integer> result = pgm.run(graph, c_i, i_i);


        System.out.println(result.getReturnValue());

        for(PgxVertex<Integer> vertex : c_i) {
            System.out.println(vertex.getId());
        }

        for(PgxVertex<Integer> vertex : i_i) {
            System.out.println(vertex.getId());
        }
    }
}
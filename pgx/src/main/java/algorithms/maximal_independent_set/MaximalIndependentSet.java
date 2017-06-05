package algorithms.maximal_independent_set;

import algorithms.Algorithm;
import oracle.pgx.api.CompiledProgram;
import oracle.pgx.api.PgxGraph;
import oracle.pgx.api.VertexProperty;
import oracle.pgx.common.types.PropertyType;

import java.util.concurrent.ExecutionException;

public class MaximalIndependentSet extends Algorithm {

    public MaximalIndependentSet() throws ExecutionException, InterruptedException {
        super();
    }

    public VertexProperty<Integer, Boolean> maximalIndependentSet(CompiledProgram pgm, PgxGraph graph)
            throws ExecutionException, InterruptedException {
        VertexProperty<Integer, Boolean> independent_set = graph.createVertexProperty(PropertyType.BOOLEAN);
        VertexProperty<Integer, Boolean> inSet = graph.createVertexProperty(PropertyType.BOOLEAN);
        VertexProperty<Integer, Boolean> isIinSet = graph.createVertexProperty(PropertyType.BOOLEAN);
        pgm.run(graph, independent_set, inSet, isIinSet);
//        independent_set.getValues().forEach(pgxVertexBooleanEntry -> {
//            System.out.println(pgxVertexBooleanEntry.getKey() + " - " + pgxVertexBooleanEntry.getValue());
//        });
//        System.out.println();
//        inSet.getValues().forEach(pgxVertexBooleanEntry -> {
//            System.out.println(pgxVertexBooleanEntry.getKey() + " - " + pgxVertexBooleanEntry.getValue());
//        });
//        System.out.println();
//        isIinSet.getValues().forEach(pgxVertexBooleanEntry -> {
//            System.out.println(pgxVertexBooleanEntry.getKey() + " - " + pgxVertexBooleanEntry.getValue());
//        });
        return independent_set;
    }

}

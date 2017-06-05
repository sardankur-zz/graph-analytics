package bugs.error_division;

import algorithms.Algorithm;
import oracle.pgx.api.*;
import oracle.pgx.common.types.PropertyType;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class ErrorDivision extends Algorithm {

    public ErrorDivision() throws ExecutionException, InterruptedException {
        super();
    }

    public VertexProperty<Integer, Double> reproduceBug(CompiledProgram pgm, PgxGraph graph,
                                                        String propName)
            throws ExecutionException, InterruptedException {

        VertexProperty closeness = graph.createVertexProperty(PropertyType.DOUBLE,"closeness");

        pgm.run(graph, closeness);
        return closeness;
    }

}

package algorithms.average_degree_connectivity;

import algorithms.Algorithm;
import oracle.pgx.api.*;
import oracle.pgx.common.types.PropertyType;

import java.util.concurrent.ExecutionException;

public class AverageDegreeConnectivity extends Algorithm {

    public AverageDegreeConnectivity() throws ExecutionException, InterruptedException {
        super();
    }

    public PgxMap<Integer, Double> averageDegreeConnectivity(CompiledProgram pgm, PgxGraph graph) throws ExecutionException,
            InterruptedException {
        PgxMap<Integer, Double> avgDegConnectivity = graph.createMap(PropertyType.INTEGER, PropertyType.DOUBLE);
        pgm.run(graph, avgDegConnectivity);
        return avgDegConnectivity;
    }
}
package algorithms.average_neighbor_degree;

import algorithms.Algorithm;
import oracle.pgx.api.CompiledProgram;
import oracle.pgx.api.PgxGraph;
import oracle.pgx.api.VertexProperty;
import oracle.pgx.api.internal.AnalysisResult;
import oracle.pgx.common.types.PropertyType;

import java.util.concurrent.ExecutionException;

public class AverageNeighborDegree extends Algorithm {

    public AverageNeighborDegree() throws ExecutionException, InterruptedException {
        super();
    }

    public VertexProperty<Integer, Double> averageNeighborDegree(CompiledProgram pgm, PgxGraph graph) throws ExecutionException,
            InterruptedException {
        VertexProperty<Integer, Double> averageNeighborDegree = graph.createVertexProperty(PropertyType.DOUBLE);
        pgm.run(graph, averageNeighborDegree);
        return averageNeighborDegree;
    }
}
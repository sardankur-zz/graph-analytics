package algorithms.average_clustering;

import algorithms.Algorithm;
import oracle.pgx.api.CompiledProgram;
import oracle.pgx.api.PgxGraph;
import oracle.pgx.api.internal.AnalysisResult;

import java.util.concurrent.ExecutionException;

public class AverageClustering extends Algorithm {

    public AverageClustering() throws ExecutionException, InterruptedException {
        super();
    }

    public double averageClustering(CompiledProgram pgm, PgxGraph graph) throws ExecutionException,
            InterruptedException {
        int n = graph.getVertices().size();
        AnalysisResult<Double> result = pgm.run(graph, n, 1000);
        return result.getReturnValue();
    }
}

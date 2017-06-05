package algorithms.clique;

import algorithms.Algorithm;
import oracle.pgx.api.*;
import oracle.pgx.api.internal.AnalysisResult;
import oracle.pgx.common.types.PropertyType;
import org.apache.commons.lang3.ArrayUtils;

import java.util.concurrent.ExecutionException;

public class MaxClique extends Algorithm {

    public MaxClique() throws ExecutionException, InterruptedException {
        super();
    }

    public void maxClique(CompiledProgram pgm, PgxGraph graph) throws ExecutionException,
            InterruptedException {

    }
}
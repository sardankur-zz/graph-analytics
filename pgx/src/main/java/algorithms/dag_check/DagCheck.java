package algorithms.dag_check;

import algorithms.Algorithm;
import oracle.pgx.api.*;
import oracle.pgx.api.internal.AnalysisResult;
import oracle.pgx.common.types.PropertyType;
import util.FileIO;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class DagCheck extends Algorithm {

    public DagCheck() throws ExecutionException, InterruptedException {
        super();
    }

    public AnalysisResult<Boolean> dagCheck(CompiledProgram pgm, PgxGraph graph) throws ExecutionException,
            InterruptedException {
        return pgm.run(graph);
    }
}

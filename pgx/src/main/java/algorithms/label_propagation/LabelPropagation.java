package algorithms.label_propagation;

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

public class LabelPropagation extends Algorithm {

    public LabelPropagation() throws ExecutionException, InterruptedException {
        super();
    }

    public VertexProperty<Integer, Integer> labelPropagation(CompiledProgram pgm, PgxGraph graph, int iterations) throws ExecutionException,
            InterruptedException {
        VertexProperty<Integer, Integer> labels = graph.createVertexProperty(PropertyType.INTEGER);
        pgm.run(graph, iterations, labels);
        return labels;
    }
}

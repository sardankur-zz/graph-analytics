package algorithms.topological_sort;

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

public class TopologicalSort extends Algorithm {

    public TopologicalSort() throws ExecutionException, InterruptedException {
        super();
    }

    public  VertexProperty<Integer, Integer> topologicalSort(CompiledProgram pgm, PgxGraph graph)
            throws ExecutionException, InterruptedException {
        VertexProperty<Integer, Integer> sequence_order = graph.createVertexProperty(PropertyType.INTEGER);
        pgm.run(graph, sequence_order);
        return sequence_order;
    }
}

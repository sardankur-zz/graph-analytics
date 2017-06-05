package bugs.error_graph_property;

import algorithms.Algorithm;
import oracle.pgx.api.CompiledProgram;
import oracle.pgx.api.PgxGraph;
import oracle.pgx.api.VertexProperty;
import oracle.pgx.common.types.PropertyType;

import java.util.concurrent.ExecutionException;

public class ErrorGraphProperty extends Algorithm {

    public ErrorGraphProperty() throws ExecutionException, InterruptedException {
        super();
    }

    public void reproduceBug(CompiledProgram pgm, PgxGraph graph1, PgxGraph graph2)
            throws ExecutionException, InterruptedException {

        VertexProperty closeness = graph1.createVertexProperty(PropertyType.DOUBLE,"closeness");
        pgm.run(graph1, graph2, closeness);
    }
}

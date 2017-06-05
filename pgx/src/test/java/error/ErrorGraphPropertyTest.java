package error;

import bugs.error_division.ErrorDivision;
import bugs.error_graph_property.ErrorGraphProperty;
import oracle.pgx.api.CompiledProgram;
import oracle.pgx.api.PgxGraph;
import oracle.pgx.api.PgxVertex;
import oracle.pgx.api.VertexProperty;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class ErrorGraphPropertyTest {

    private static ErrorGraphProperty algorithm = null;
    private static CompiledProgram pgm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new ErrorGraphProperty();
        pgm = algorithm.compile("src/main/java/bugs/error_graph_property/error_graph_property.gm");
    }


//    @Test
//    public void testErrorDivision() throws InterruptedException, ExecutionException {
//        PgxGraph graph1 = algorithm.getDataWithCost
//                ("src/test/java/data/sample_data5.adj", "cost");
//        PgxGraph graph2 = algorithm.getDataWithCost
//                ("src/test/java/data/sample_data5.adj", "cost");
//        algorithm.reproduceBug(pgm, graph1, graph2);
//    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }
}

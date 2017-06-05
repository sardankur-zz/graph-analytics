package error;

import bugs.error_division.ErrorDivision;
import oracle.pgx.api.CompiledProgram;
import oracle.pgx.api.PgxGraph;
import oracle.pgx.api.PgxVertex;
import oracle.pgx.api.VertexProperty;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by ankursarda on 3/22/17.
 */
public class ErrorDivisionTest {

    private static ErrorDivision algorithm = null;
    private static CompiledProgram pgm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new ErrorDivision();
        pgm = algorithm.compile("src/main/java/bugs/error_division/error_division.gm");
    }

//    @Test
//    public void testErrorDivision() throws InterruptedException, ExecutionException {
//        PgxGraph graph = algorithm.getDataWithCost
//                ("src/test/java/data/sample_data5.adj", "cost");
//        VertexProperty<Integer, Double> closeness = algorithm.reproduceBug(pgm, graph, "cost");
//        for(PgxVertex vertex : graph.getVertices()) {
//            System.out.println(closeness.get(vertex));
//        }
//    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }

}
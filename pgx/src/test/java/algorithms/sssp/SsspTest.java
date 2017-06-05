package algorithms.sssp;

import algorithms.AlgorithmTest;
import oracle.pgx.api.CompiledProgram;
import oracle.pgx.api.PgxMap;
import oracle.pgx.api.PgxVertex;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class SsspTest extends AlgorithmTest {

    private static Sssp algorithm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new Sssp();
        pgm = algorithm.compile("src/main/java/algorithms/sssp/sssp.gm");
    }

    @Test
    public void testSssp() throws InterruptedException, ExecutionException {
        PgxMap<PgxVertex<Integer>, Double> cost = algorithm.sssp(pgm,
                algorithm.getDataWithCost("src/test/java/data/sample_data2.adj"),
                1, new int[] {2,3});
        if(cost != null) {
            cost.entries().forEach((entry) -> {
                System.out.println(entry.getKey().getId() + " : " + entry.getValue());
            });
        }
    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }
}

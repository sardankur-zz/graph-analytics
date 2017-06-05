package algorithms.sssp;

import algorithms.AlgorithmTest;
import oracle.pgx.api.CompiledProgram;
import oracle.pgx.api.PgxMap;
import oracle.pgx.api.PgxVertex;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class SsspExtTest extends AlgorithmTest {

    private static Sssp algorithm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new Sssp();
        pgm = algorithm.compile("src/main/java/algorithms/sssp/sssp.gm");
    }

    @Test
    public void testSssp() throws InterruptedException, ExecutionException {
        if(dataFile != null) {
            int source = Integer.valueOf(System.getProperty("source"));
            String[] landmarksStr = System.getProperty("landmarks").split(",");
            int [] landmarks = new int[landmarksStr.length];
            for(int i  = 0; i < landmarks.length; ++i) { landmarks[i] = Integer.valueOf(landmarksStr[i]); }
            PgxMap<PgxVertex<Integer>, Double> cost = algorithm.sssp(pgm,
                    algorithm.getDataWithCost(dataFile),
                    source, landmarks);
            if (cost != null) {
                cost.entries().forEach((entry) -> {
                    System.out.println(entry.getKey().getId() + " : " + entry.getValue());
                });
            }
        }
    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }
}

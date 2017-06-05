package algorithms.average_degree_connectivity;

import algorithms.AlgorithmTest;
import oracle.pgx.api.PgxMap;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AverageDegreeConnectivityExtTest extends AlgorithmTest {

    private static AverageDegreeConnectivity algorithm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new AverageDegreeConnectivity();
        pgm = algorithm.compile("src/main/java/algorithms/average_degree_connectivity" +
                "/average_degree_connectivity.gm");

    }

    @Test
    public void testAverageDegreeConnectivity() throws InterruptedException, ExecutionException {
        if(dataFile != null) {
            PgxMap<Integer, Double> avgDegree =
                    algorithm.averageDegreeConnectivity(pgm,
                            algorithm.getData(dataFile));
            List<Integer> keys = new ArrayList<>();
            avgDegree.keys().forEach(key -> { keys.add(key);});
            keys.sort((o1, o2) -> {return o1 - o2;});
            List<String> output = new ArrayList<>();
            for(Integer key : keys) {
                if(avgDegree.get(key) != 0) {
                    output.add(String.format("%d: '%.3f'", key, avgDegree.get(key)));
                }
            }
            System.err.println("###{" + String.join(", ", output) + "}###");
        }
    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }
}

package algorithms.average_clustering;

import algorithms.AlgorithmTest;
import algorithms.average_degree_connectivity.AverageDegreeConnectivity;
import oracle.pgx.api.PgxMap;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class AverageClusteringTest extends AlgorithmTest {

    private static AverageClustering algorithm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new AverageClustering();
        pgm = algorithm.compile("src/main/java/algorithms/average_clustering" +
                "/average_clustering.gm");
    }

    @Test
    public void testAverageDegreeConnectivity() throws InterruptedException, ExecutionException {
        double averageClustering =
                algorithm.averageClustering(pgm,
                        algorithm.getDataWithCost("src/test/java/data/sample_data8.adj").undirect());
        System.out.println(averageClustering);
    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }
}

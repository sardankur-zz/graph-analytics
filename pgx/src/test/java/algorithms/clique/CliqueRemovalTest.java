package algorithms.clique;

import algorithms.AlgorithmTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class CliqueRemovalTest extends AlgorithmTest {

    private static CliqueRemoval algorithm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new CliqueRemoval();
        pgm = algorithm.compile("src/main/java/algorithms/clique" +
                "/clique_removal.gm");
    }

    @Test
    public void testCliqueRemoval() throws InterruptedException, ExecutionException {
        algorithm.cliqueRemoval(pgm,
                        algorithm.getData("src/test/java/data/sample_data10.adj").undirect());
    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }
}

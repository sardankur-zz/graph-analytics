package algorithms.cycles;

import algorithms.AlgorithmTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SimpleCyclesTest extends AlgorithmTest {

    private static SimpleCycles algorithm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new SimpleCycles();
        pgm = algorithm.compile("src/main/java/algorithms/cycles/simple_cycles.gm");
    }

    @Test
    public void testSimpleCycles() throws InterruptedException, ExecutionException {
        List<List<Integer>> cycleList = algorithm.simpleCycles(pgm,
                algorithm.getData("src/test/java/data/sample_data9.adj"));
        for(List<Integer> cycle : cycleList) {
            for (Integer ele : cycle) {
                System.out.print(ele + " : ");
            }
            System.out.println();
        }
    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }
}


package algorithms.cycles;

import algorithms.AlgorithmTest;
import oracle.pgx.api.PgxVertex;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FindCycleTest extends AlgorithmTest {

    private static FindCycle algorithm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new FindCycle();
        pgm = algorithm.compile("src/main/java/algorithms/cycles/find_cycle.gm");
    }

    @Test
    public void testFindCycle() throws InterruptedException, ExecutionException {
        List<Integer> cycleList = algorithm.findCycle(pgm,
                algorithm.getData("src/test/java/data/sample_data11.adj"));
        List<String> output = new ArrayList<>();
        for(int i = cycleList.size() - 1; i >= 0; i--) {
            if(i != 0)
                output.add(String.format("(%s, %s)", cycleList.get(i), cycleList.get(i-1)));
            else
                output.add(String.format("(%s, %s)", cycleList.get(i), cycleList.get(cycleList.size() - 1)));

        }
        System.out.println("###[" + String.join(", ", output) + "]###");
    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }
}
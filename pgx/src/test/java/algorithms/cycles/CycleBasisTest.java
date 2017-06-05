package algorithms.cycles;

import algorithms.AlgorithmTest;
import algorithms.sssp.Sssp;
import oracle.pgx.api.PgxMap;
import oracle.pgx.api.PgxVertex;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CycleBasisTest extends AlgorithmTest {

    private static CycleBasis algorithm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new CycleBasis();
        pgm = algorithm.compile("src/main/java/algorithms/cycles/cycle_basis.gm");
    }

    @Test
    public void testCycleBasis() throws InterruptedException, ExecutionException {
        List<List<PgxVertex<Integer>>> cycleList = algorithm.cycleBasis(pgm,
                algorithm.getUnidirectData("src/test/java/data/sample_data3.adj"));
        List<String> output = new ArrayList<>();
        List<String> suboutput = new ArrayList<>();
        for(List<PgxVertex<Integer>> cycle : cycleList) {
            suboutput.clear();
            for(PgxVertex<Integer> vertex : cycle) {
                suboutput.add(vertex.getId().toString());
            }
            output.add("[" + String.join(", ", suboutput) + "]");
        }
        System.out.println("###[" + String.join(", ", output) + "]###");
    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }
}

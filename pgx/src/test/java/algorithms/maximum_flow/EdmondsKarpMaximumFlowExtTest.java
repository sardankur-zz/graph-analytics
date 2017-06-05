package algorithms.maximum_flow;

import algorithms.AlgorithmTest;
import oracle.pgx.api.CompiledProgram;
import oracle.pgx.api.PgxGraph;
import oracle.pgx.common.types.PropertyType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class EdmondsKarpMaximumFlowExtTest extends AlgorithmTest {

    private static EdmondsKarpMaximumFlow algorithm = null;
    private static CompiledProgram pgm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new EdmondsKarpMaximumFlow();
        pgm = algorithm.compile("src/main/java/algorithms/maximum_flow/edmondskarp_maximumflow.gm");

    }

    @Test
    public void testEdmondsKarpMaximumFlow() throws InterruptedException, ExecutionException {
        if(dataFile != null) {
            int source = Integer.valueOf(System.getProperty("source"));
            int destination = Integer.valueOf(System.getProperty("dest"));
            PgxGraph graph = algorithm.getDataWithCost(dataFile,
                    PropertyType.INTEGER,
                    "cost");
            int maximalFlow = algorithm.maximalFlow(pgm, graph,
                    source,
                    destination,
                    "cost");
            System.out.println(String.format("###%.1f###", (float) maximalFlow));
        }
    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }
}

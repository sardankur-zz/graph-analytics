package algorithms.maximum_flow;

import algorithms.AlgorithmTest;
import oracle.pgx.api.CompiledProgram;
import oracle.pgx.api.PgxGraph;
import oracle.pgx.common.types.PropertyType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class PreFlowPushExtTest extends AlgorithmTest {

    private static PreFlowPush algorithm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new PreFlowPush();
        pgm = algorithm.compile("src/main/java/algorithms/maximum_flow/preflow_push.gm");
    }

    @Test
    public void testPreFlowPush() throws InterruptedException, ExecutionException {
        if (dataFile != null) {
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

package algorithms.maximum_flow;

import algorithms.AlgorithmTest;
import oracle.pgx.api.CompiledProgram;
import oracle.pgx.api.PgxGraph;
import oracle.pgx.api.internal.AnalysisResult;
import oracle.pgx.common.types.PropertyType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class PreFlowPushTest extends AlgorithmTest {

    private static PreFlowPush algorithm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new PreFlowPush();
        pgm = algorithm.compile("src/main/java/algorithms/maximum_flow/preflow_push.gm");
    }

    @Test
    public void testPreFlowPush() throws InterruptedException, ExecutionException {
        PgxGraph graph = algorithm.getDataWithCost("src/test/java/data/sample_data4.adj",
                PropertyType.INTEGER,
                "cost");
        int maximalFlow = algorithm.maximalFlow(pgm, graph,
                0,
                graph.getVertices().size() - 1,
                "cost");
        System.out.println(String.format("%.1f", (float) maximalFlow));
    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }

}

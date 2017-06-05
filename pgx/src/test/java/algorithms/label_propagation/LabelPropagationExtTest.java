package algorithms.label_propagation;

import algorithms.AlgorithmTest;
import oracle.pgx.api.CompiledProgram;
import oracle.pgx.api.PgxVertex;
import oracle.pgx.api.VertexProperty;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class LabelPropagationExtTest extends AlgorithmTest {

    private static LabelPropagation algorithm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new LabelPropagation();
        pgm = algorithm.compile("src/main/java/algorithms/label_propagation/label_propagation.gm");
    }

    @Test
    public void testLabelPropagation() throws InterruptedException, ExecutionException {
        if(dataFile != null) {
            System.out.println(System.getProperty("iters"));
            int iterations = Integer.valueOf(System.getProperty("iters") == null ? "50"
                    : System.getProperty("iters"));
            VertexProperty<Integer, Integer> labels = algorithm.labelPropagation(pgm,
                    algorithm.getData(dataFile), iterations);
            for (Map.Entry<PgxVertex<Integer>, Integer> entry : labels.getValues()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }
}

package algorithms.label_propagation;

import algorithms.AlgorithmTest;
import algorithms.dag_check.DagCheck;
import oracle.pgx.api.CompiledProgram;
import oracle.pgx.api.PgxVertex;
import oracle.pgx.api.VertexProperty;
import oracle.pgx.api.internal.AnalysisResult;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class LabelPropagationTest extends AlgorithmTest {

    private static LabelPropagation algorithm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new LabelPropagation();
        pgm = algorithm.compile("src/main/java/algorithms/label_propagation/label_propagation.gm");
    }

    @Test
    public void testLabelPropagation() throws InterruptedException, ExecutionException {
        VertexProperty<Integer, Integer> labels =  algorithm.labelPropagation(pgm,
                algorithm.getData("src/test/java/data/sample_data1.adj"), 50);
        for(Map.Entry<PgxVertex<Integer>, Integer> entry : labels.getValues()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }
}

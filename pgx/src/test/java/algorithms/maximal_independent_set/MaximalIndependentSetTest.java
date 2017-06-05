package algorithms.maximal_independent_set;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MaximalIndependentSetTest extends AlgorithmTest {

    private static MaximalIndependentSet algorithm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new MaximalIndependentSet();
        pgm = algorithm.compile("src/main/java/algorithms/" +
                "maximal_independent_set/maximal_independent_set.gm");
    }

    @Test
    public void testMaximalIndependentSet() throws InterruptedException, ExecutionException {
        VertexProperty<Integer, Boolean> result =  algorithm.maximalIndependentSet(pgm,
                algorithm.getData("src/test/java/data/sample_data3.adj"));
        List<Integer> keys = new ArrayList<>();
        result.getValues().forEach(pgxVertexBooleanEntry -> {
            if(pgxVertexBooleanEntry.getValue()) {
                keys.add(pgxVertexBooleanEntry.getKey().getId());
            }
        });

        keys.sort((o1, o2) -> {return o1 - o2;});

        List<String> output = new ArrayList<>();
        for(Integer key : keys) {
            output.add(key.toString());
        }
        System.out.println("{" + String.join(", ", output) + "}");
    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }
}

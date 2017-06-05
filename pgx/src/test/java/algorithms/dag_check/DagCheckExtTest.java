package algorithms.dag_check;


import algorithms.AlgorithmTest;
import oracle.pgx.api.CompiledProgram;
import oracle.pgx.api.internal.AnalysisResult;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class DagCheckExtTest extends AlgorithmTest {

    private static DagCheck algorithm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new DagCheck();
        pgm = algorithm.compile("src/main/java/algorithms/dag_check/dag_check.gm");
    }

    @Test
    public void testDag() throws InterruptedException, ExecutionException {
        if (dataFile != null) {
            AnalysisResult<Boolean> result = algorithm.dagCheck(pgm,
                    algorithm.getData(dataFile));
            System.out.println("###"+result.getReturnValue()+"###");
        }
    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }
}

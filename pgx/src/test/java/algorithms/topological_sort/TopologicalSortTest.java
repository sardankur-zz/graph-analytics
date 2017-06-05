package algorithms.topological_sort;

import algorithms.AlgorithmTest;
import algorithms.dag_check.DagCheck;
import oracle.pgx.api.CompiledProgram;
import oracle.pgx.api.PgxVertex;
import oracle.pgx.api.VertexProperty;
import oracle.pgx.api.internal.AnalysisResult;
import oracle.pgx.common.types.PropertyType;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class TopologicalSortTest extends AlgorithmTest {

    private static TopologicalSort algorithm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new TopologicalSort();
        pgm = algorithm.compile("src/main/java/algorithms/topological_sort/topological_sort.gm");
    }

    @Test
    public void testTopologicalSort() throws InterruptedException, ExecutionException {
        VertexProperty<Integer, Integer> sequence_order =
        algorithm.topologicalSort(pgm,
                algorithm.getData("src/test/java/data/sample_data3.adj"));
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> keys = new ArrayList<>();
        for (Map.Entry<PgxVertex<Integer>, Integer> entry : sequence_order.getValues()) {
            map.put(entry.getValue(), entry.getKey().getId());
            keys.add(entry.getValue());
        }
        keys.sort((o1, o2) -> {return o1 - o2;});

        List<String> output = new ArrayList<>();
        for(Integer key : keys) {
            output.add(map.get(key).toString());
        }
        System.out.println("[" + String.join(", ", output) + "]");
    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }

}

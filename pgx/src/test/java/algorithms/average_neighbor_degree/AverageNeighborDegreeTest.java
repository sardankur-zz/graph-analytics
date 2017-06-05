package algorithms.average_neighbor_degree;

import algorithms.AlgorithmTest;
import algorithms.topological_sort.TopologicalSort;
import oracle.pgx.api.CompiledProgram;
import oracle.pgx.api.PgxVertex;
import oracle.pgx.api.VertexProperty;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class AverageNeighborDegreeTest extends AlgorithmTest {

    private static AverageNeighborDegree algorithm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new AverageNeighborDegree();
        pgm = algorithm.compile("src/main/java/algorithms/average_neighbor_degree" +
                "/average_neighbor_degree.gm");
    }

    @Test
    public void testAverageNeighborDegree() throws InterruptedException, ExecutionException {
        VertexProperty<Integer, Double> avgDegree =
                algorithm.averageNeighborDegree(pgm,
                        algorithm.getData("src/test/java/data/sample_data3.adj"));

        Map<Integer, Double> map = new HashMap<>();
        List<Integer> keys = new ArrayList<>();
        for(Map.Entry<PgxVertex<Integer>, Double> entry : avgDegree.getValues()) {
            map.put(entry.getKey().getId(), entry.getValue());
            keys.add(entry.getKey().getId());
        }
        keys.sort((o1, o2) -> {return o1 - o2;});

        List<String> output = new ArrayList<>();
        for(Integer key : keys) {
            output.add(String.format("%d: '%.3f'", key, map.get(key)));
        }
        System.out.println("{" + String.join(", ", output) + "}");

    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }
}

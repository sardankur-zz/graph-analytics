package algorithms.current_flow.current_flow_betweenness;

import algorithms.AlgorithmTest;
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

public class CurrentFlowBetweennessExtTest extends AlgorithmTest {

    private static CurrentFlowBetweenness algorithm = null;

    @BeforeClass
    public static void before() throws InterruptedException, ExecutionException {
        algorithm = new CurrentFlowBetweenness();
        pgm = algorithm.compile("src/main/java/algorithms/current_flow/current_flow_betweenness" +
                "/current_flow_betweenness.gm");
    }

    @Test
    public void testCurrentFlowCloseness() throws InterruptedException, ExecutionException {
        if(dataFile != null) {
            String propName = "resistance";
            VertexProperty<Integer, Double> betweenness =
                    algorithm.currentFlowBetweenness(pgm,
                            algorithm.getDataWithCost(dataFile, propName),
                            propName);

            Map<Integer, Double> map = new HashMap<>();
            List<Integer> keys = new ArrayList<>();
            for (Map.Entry<PgxVertex<Integer>, Double> entry : betweenness.getValues()) {
                map.put(entry.getKey().getId(), (entry.getValue()));
                keys.add(entry.getKey().getId());
            }
            keys.sort((o1, o2) -> {return o1 - o2;});

            List<String> output = new ArrayList<>();
            for(Integer key : keys) {
                output.add(String.format("%d: '%.3f'", key, map.get(key)));
            }
            System.out.println("###{" + String.join(", ", output) + "}###");
        }
    }

    @AfterClass
    public static void after() {
        algorithm.close();
    }

}

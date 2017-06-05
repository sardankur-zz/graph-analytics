package algorithms.cycles;

import algorithms.Algorithm;
import oracle.pgx.api.*;
import oracle.pgx.api.internal.AnalysisResult;
import oracle.pgx.common.types.PropertyType;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CycleBasis extends Algorithm {

    public CycleBasis() throws ExecutionException, InterruptedException {
        super();
    }

    public List<List<PgxVertex<Integer>>> cycleBasis(CompiledProgram pgm, PgxGraph graph)
            throws ExecutionException, InterruptedException {
        int n = graph.getVertices().size();
        int n2 = n*(n+1);

        VertexProperty<Integer, PgxVect<Integer>> cycle_prop =
                graph.createVertexVectorProperty(PropertyType.INTEGER, n2);

        VertexProperty<Integer, Integer> id = graph.createVertexProperty(PropertyType.INTEGER);

        for(PgxVertex vertex: graph.getVertices()) {
            id.set(vertex, (Integer) vertex.getId());
        }

        PgxVertex<Integer> root = graph.getVertex(0);

        for(PgxVertex vertex : graph.getVertices()) {
            cycle_prop.set(vertex, new PgxVect<Integer>(ArrayUtils.toObject(new int[n2])));
        }

        AnalysisResult<Integer> result = pgm.run(graph, n, n2, id, root, cycle_prop);
//        System.out.println(result.getReturnValue());

        PgxVect<Integer> cycleInt = cycle_prop.get(root);

        List<List<PgxVertex<Integer>>> cycleList = new ArrayList<>();

        for(int i = 0; i < result.getReturnValue(); ++i) {
            List<PgxVertex<Integer>> cycle = new ArrayList<>();
            for(int j = 0; j < n; ++j) {
                if(cycleInt.get(i*n + j) != -1) {
                    cycle.add(graph.getVertex(cycleInt.get(i*n + j)));
                } else {
                    break;
                }
            }
            cycleList.add(cycle);
        }

        return cycleList;
    }

}

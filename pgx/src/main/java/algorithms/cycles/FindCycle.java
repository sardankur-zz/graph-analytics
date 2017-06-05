package algorithms.cycles;

import algorithms.Algorithm;
import oracle.pgx.api.*;
import oracle.pgx.api.internal.AnalysisResult;
import oracle.pgx.common.types.PropertyType;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FindCycle extends Algorithm {

    public FindCycle() throws ExecutionException, InterruptedException {
        super();
    }

    public List<Integer> findCycle(CompiledProgram pgm, PgxGraph graph)
            throws ExecutionException, InterruptedException {

        int n = graph.getVertices().size();

        VertexProperty<Integer, PgxVect<Integer>> cycle_prop =
                graph.createVertexVectorProperty(PropertyType.INTEGER, n);

        VertexProperty<Integer, Integer> id = graph.createVertexProperty(PropertyType.INTEGER);

        int i = 0;

        for(PgxVertex vertex: graph.getVertices()) {
            id.set(vertex, i++);
        }

        PgxVertex<Integer> root = graph.getVertex(0);

        for(PgxVertex vertex : graph.getVertices()) {
            cycle_prop.set(vertex, new PgxVect<Integer>(ArrayUtils.toObject(new int[n])));
        }

        AnalysisResult<Integer> result = pgm.run(graph, n, id, root, true, cycle_prop);

        PgxVect<Integer> vect = cycle_prop.get(root);
        List<Integer> cycleList = new ArrayList<>(result.getReturnValue());
        for(i = 0 ; i < result.getReturnValue(); ++i) {
            cycleList.add(vect.get(i));
        }

        return cycleList;
    }
}

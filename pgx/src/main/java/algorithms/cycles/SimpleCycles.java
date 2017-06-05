package algorithms.cycles;

import algorithms.Algorithm;
import oracle.pgx.api.*;
import oracle.pgx.api.internal.AnalysisResult;
import oracle.pgx.common.types.PropertyType;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class SimpleCycles  extends Algorithm {

    public SimpleCycles() throws ExecutionException, InterruptedException {
        super();
    }

    public List<List<Integer>> simpleCycles(CompiledProgram pgm, PgxGraph graph)
            throws ExecutionException, InterruptedException {

        /*
        for(PgxVertex vertex : graph.getVertices()) {
            System.out.print(vertex.getId() + " ");
            vertex.getOutNeighbors().forEach(o -> {
                System.out.print(((PgxVertex) o).getId() + " ");
            });
            System.out.println();
        }
        */

        int n = graph.getVertices().size();
        int size = 1000;
        VertexProperty<Integer, PgxVect<Integer>> cycle_prop =
                graph.createVertexVectorProperty(PropertyType.INTEGER, size);

        VertexProperty<Integer, Integer> id = graph.createVertexProperty(PropertyType.INTEGER);


        for(PgxVertex vertex: graph.getVertices()) {
            id.set(vertex, (Integer) vertex.getId());
        }

        PgxVertex<Integer> root = graph.getVertex(0);
        PgxVertex<Integer> root2 = graph.getVertex(1);
        for(PgxVertex vertex : graph.getVertices()) {
            cycle_prop.set(vertex, new PgxVect<Integer>(ArrayUtils.toObject(new int[size])));
        }

        AnalysisResult<Integer> result = pgm.run(graph, n, size, id, root, cycle_prop);

        List<List<Integer>> cycleList = new ArrayList<>();

        PgxVect<Integer> vect = cycle_prop.get(root);


        List<Integer> sublist = new ArrayList<>();

        int i = 0;

        System.out.println(result.getReturnValue());
        i = 0;
        while(i <= result.getReturnValue()) {
            System.out.print(vect.get(i) + " ");
            if(vect.get(i) == -1) {
                cycleList.add(sublist);
                sublist = new ArrayList<>();
            }
            else {
                sublist.add(vect.get(i));
            }
            ++i;
        }
        System.out.println();

        System.out.println(cycleList.size());
        return cycleList;
    }
}
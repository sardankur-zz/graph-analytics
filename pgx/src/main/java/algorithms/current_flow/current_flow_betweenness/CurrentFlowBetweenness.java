package algorithms.current_flow.current_flow_betweenness;

import algorithms.current_flow.CurrentFlow;
import oracle.pgx.api.*;
import oracle.pgx.common.types.PropertyType;
import oracle.pgx.runtime.util.vectors.DoubleVect;
import oracle.pgx.runtime.util.vectors.IntVect;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.Pair;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class CurrentFlowBetweenness extends CurrentFlow {

    public CurrentFlowBetweenness() throws ExecutionException, InterruptedException {
        super();
    }

    public int [] sortAndPosition(double row[]) {
        Pair<Double, Integer> [] pos = new Pair[row.length];

        for(int i = 0; i < pos.length; ++i) {
            pos[i] = new Pair<>(row[i], i);
        }

        Arrays.sort(pos, (o1, o2) -> { return o2.getFirst() > o1.getFirst() ? 1 : -1; });

        int [] newPos = new int[row.length];
        for(int i = 0; i < newPos.length; ++i) {
            newPos[pos[i].getSecond()] = i;
        }
        return newPos;
    }

    public void update(PgxGraph graph,
                       RealMatrix C,
                       String propName,
                       EdgeProperty<PgxVect<Integer>> position,
                       EdgeProperty<PgxVect<Double>> value)
            throws ExecutionException, InterruptedException {

        int n = graph.getVertices().size();
        int m = graph.getEdges().size();

        RealMatrix B = new Array2DRowRealMatrix(n, m);

        for(PgxVertex vertex : graph.getVertices()) {
            for(PgxEdge edge : graph.getEdges()) {

                int source, destination;
                source = (Integer) edge.getSource().getId();
                destination = (Integer) edge.getDestination().getId();
//                if((Integer) edge.getSource().getId() < (Integer) edge.getDestination().getId()) {
//                    source = (Integer) edge.getSource().getId();
//                    destination = (Integer) edge.getDestination().getId();
//                } else {
//                    source = (Integer) edge.getDestination().getId();
//                    destination = (Integer) edge.getSource().getId();
//                }

                if(source == (Integer) vertex.getId()) {
                    B.setEntry(source, edge.getId().intValue(), (double) edge.getProperty(propName));
                } else if(destination == (Integer) vertex.getId()) {
                    B.setEntry(destination, edge.getId().intValue(), - (double) edge.getProperty(propName));
                } else {
                    B.setEntry((Integer) vertex.getId(), edge.getId().intValue(), 0);
                }
            }
        }

        RealMatrix BC = B.transpose().multiply(C);

        for(PgxEdge edge : graph.getEdges()) {
            double row[] = BC.getRow(edge.getId().intValue());
            value.set(edge, new PgxVect<>(ArrayUtils.toObject(row)));
            position.set(edge, new PgxVect<>(ArrayUtils.toObject(sortAndPosition(row))));
        }
    }

    public VertexProperty<Integer, Double> currentFlowBetweenness(CompiledProgram pgm, PgxGraph graph,
            String propName) throws ExecutionException, InterruptedException {

        String betweennessPropName = "betweenness";
        int n = graph.getVertices().size();

        RealMatrix laplacianInverse = getLaplacianInverseMatrix(graph, propName);

        EdgeProperty<PgxVect<Integer>>  position =
                graph.createEdgeVectorProperty(PropertyType.INTEGER,
                        n, "position");

        EdgeProperty<PgxVect<Double>> value =
                graph.createEdgeVectorProperty(PropertyType.DOUBLE,
                        n, "value");

        update(graph, laplacianInverse, propName, position, value);

        VertexProperty<Integer, Integer> id =
                graph.createVertexProperty(PropertyType.INTEGER, "id");
        for(PgxVertex vertex : graph.getVertices()) {
            id.set(vertex, (Integer) vertex.getId());
        }


        VertexProperty<Integer, Double> currentFlowBetweenness =
                graph.createVertexProperty(PropertyType.DOUBLE, betweennessPropName);


/*
        // Java version for the algorithm
        for (PgxEdge edge : graph.getEdges()) {

            int source, destination;
            source = (Integer) edge.getSource().getId();
            destination = (Integer) edge.getDestination().getId();

            int i = 0;
            while(i < n) {

                currentFlowBetweenness.set(graph.getVertex(source),
                        currentFlowBetweenness.get((Integer) source) +
                        (i - ((IntVect) edge.getProperty("position")).get(i))
                               * ((DoubleVect) edge.getProperty("value")).get(i));

                currentFlowBetweenness.set(graph.getVertex(destination),
                        currentFlowBetweenness.get((Integer) destination) +
                               (n - i - 1
                                        - ((IntVect) edge.getProperty("position")).get(i))
                                        * ((DoubleVect) edge.getProperty("value")).get(i));

                i++;
            }
        }
        n = (n-1)*(n-2);
        for (PgxVertex vertex : graph.getVertices()) {
            vertex.setProperty(betweennessPropName, (2*((Double) vertex.getProperty(betweennessPropName)
                    - (Integer) vertex.getId()))/n);
        }
*/

        pgm.run(graph,
                n,
                position,
                value,
                id,
                currentFlowBetweenness);



        return currentFlowBetweenness;
    }
}

package algorithms.current_flow.current_flow_closeness;

import algorithms.Algorithm;
import algorithms.current_flow.CurrentFlow;
import oracle.pgx.api.*;
import oracle.pgx.common.types.PropertyType;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.concurrent.ExecutionException;

public class CurrentFlowCloseness extends CurrentFlow {

    public CurrentFlowCloseness() throws ExecutionException, InterruptedException {
        super();
    }

    public void addMatrixToVertexProperty(RealMatrix C, PgxGraph graph, String propName)
            throws ExecutionException, InterruptedException {
        VertexProperty vProp = graph.createVertexVectorProperty(PropertyType.DOUBLE,
                graph.getVertices().size(), propName);
        for(PgxVertex vertex : graph.getVertices())  {
            vProp.set(vertex, new PgxVect<Double>(
                    ArrayUtils.toObject(C.getRow((Integer) vertex.getId()))));
        }
    }

    public VertexProperty<Integer, Double> currentFlowCloseness(CompiledProgram pgm, PgxGraph graph,
                String propName) throws ExecutionException, InterruptedException {
//        PgxGraph laplacianGraph = getLaplacianInverseGraph(getLaplacianInverseMatrix(graph, propName),
//                graph, "cost");
        addMatrixToVertexProperty(getLaplacianInverseMatrix(graph, propName), graph, "row");
        VertexProperty<Integer, Integer> id =
                graph.createVertexProperty(PropertyType.INTEGER, "id");
        for(PgxVertex vertex : graph.getVertices()) {
            id.set(vertex, (Integer) vertex.getId());
        }
        VertexProperty<Integer, Double> currentFlowCloseness =
                graph.createVertexProperty(PropertyType.DOUBLE, "closeness");
        pgm.run(graph,
                graph.getVertices().size(),
                graph.getVertexProperty("row"),
                id,
                currentFlowCloseness);
        return currentFlowCloseness;
    }
}

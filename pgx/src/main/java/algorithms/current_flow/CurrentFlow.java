package algorithms.current_flow;

import algorithms.Algorithm;
import oracle.pgx.api.*;
import oracle.pgx.common.types.PropertyType;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class CurrentFlow extends Algorithm {

    public CurrentFlow() throws ExecutionException, InterruptedException {
        super();
    }

    public RealMatrix getLaplacianInverseMatrix(PgxGraph graph, String propName)
            throws ExecutionException, InterruptedException {

        RealMatrix matrix = new Array2DRowRealMatrix(graph.getVertices().size(),
                graph.getVertices().size());

        for(int i = 0; i < graph.getVertices().size(); ++i) {
            for (int j = 0; j < graph.getVertices().size(); ++j) {
                matrix.setEntry(i,j,0);
            }
        }

        for(PgxEdge edge : graph.getEdges()) {
            int i = (int) edge.getSource().getId();
            int j = (int) edge.getDestination().getId();

            if(i != j) {
                matrix.setEntry(i,j, -(Double) edge.getProperty(propName));
                matrix.setEntry(j,i, -(Double) edge.getProperty(propName));
                matrix.setEntry(i,i, matrix.getEntry(i,i) + (Double) edge.getProperty(propName));
                matrix.setEntry(j,j, matrix.getEntry(j,j) + (Double) edge.getProperty(propName));
            }
        }

        RealMatrix matrix2 = new Array2DRowRealMatrix(graph.getVertices().size() - 1,
                graph.getVertices().size() - 1);

        for(int i = 1; i < graph.getVertices().size(); ++i) {
            for(int j = 1; j < graph.getVertices().size(); ++j) {
                matrix2.setEntry(i-1,j-1, matrix.getEntry(i,j));
            }
        }

        matrix2 = MatrixUtils.inverse(matrix2);

        for(int i = 0; i < graph.getVertices().size(); ++i) {
            for(int j = 0; j < graph.getVertices().size(); ++j) {
                if(i == 0 || j == 0) {
                    matrix.setEntry(i,j, 0);
                } else {
                    matrix.setEntry(i,j, matrix2.getEntry(i-1,j-1));
                }

            }
        }

        return matrix;
    }

    public PgxGraph getLaplacianInverseGraph(RealMatrix matrix, PgxGraph graph, String propName)
            throws ExecutionException, InterruptedException {

        GraphBuilder<Integer> builder = session.newGraphBuilder();
        long index = 0;

        for(int i = 0; i < graph.getVertices().size(); ++i) {
            for (int j = 0; j < graph.getVertices().size(); ++j) {
                builder.addEdge(index++, i, j);
            }
        }

        PgxGraph laplacianGraph = builder.build("laplacianGraph");
        EdgeProperty<Double> edgeProperty = laplacianGraph.createEdgeProperty(PropertyType.DOUBLE, propName);

        index = 0;
        for(int i = 0; i < laplacianGraph.getVertices().size(); ++i) {
            for (int j = 0; j < laplacianGraph.getVertices().size(); ++j) {
                edgeProperty.set(laplacianGraph.getEdge(index++), matrix.getEntry(i, j));
            }
        }

        return laplacianGraph;
    }
}
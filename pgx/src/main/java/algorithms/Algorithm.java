package algorithms;

import oracle.pgx.api.*;
import oracle.pgx.common.types.PropertyType;
import oracle.pgx.config.Format;
import oracle.pgx.config.GraphConfigBuilder;
//import oracle.pgx.config.FileGraphConfig; // pgx 2.3+
import oracle.pgx.config.SingleFileGraphConfig; // pgx 2.2

import java.util.concurrent.ExecutionException;

public abstract class Algorithm {

    protected PgxSession session = null;

    protected Algorithm() throws ExecutionException, InterruptedException {
        this("session");
    }

    protected Algorithm(String sessionName) throws ExecutionException, InterruptedException {
        session = Pgx.createSession(sessionName);
    }

    public PgxGraph getDataWithCost(String graphDataFile) throws ExecutionException, InterruptedException  {
        return getDataWithCost(graphDataFile, "cost");
    }

    public PgxGraph getDataWithCost(String graphDataFile, String propName) throws ExecutionException, InterruptedException  {
        return getDataWithCost(graphDataFile, PropertyType.DOUBLE, Format.ADJ_LIST, propName);
    }

    public PgxGraph getDataWithCost(String graphDataFile, String propName, Format format) throws ExecutionException, InterruptedException {
        return getDataWithCost(graphDataFile, PropertyType.DOUBLE, format, propName);
    }

    public PgxGraph getDataWithCost(String graphDataFile, PropertyType propertyType, String propName) throws ExecutionException, InterruptedException {
        return getDataWithCost(graphDataFile, propertyType, Format.ADJ_LIST, propName);
    }

    public PgxGraph getDataWithCost(String graphDataFile, PropertyType propertyType, Format format, String propName) throws ExecutionException, InterruptedException  {
        // pgx 2.3+
//        FileGraphConfig config = GraphConfigBuilder.forFileFormat(format)
//                .addUri(graphDataFile)
//                .setSeparator(" ")
//                .addEdgeProperty(propName, propertyType)
//                .build();

        // pgx 2.2
        SingleFileGraphConfig config = GraphConfigBuilder.forSingleFileFormat(format)
                .setUri(graphDataFile)
                .setSeparator(" ")
                .addEdgeProperty(propName, propertyType)
                .build();
        return session.readGraphWithProperties(config);
    }

    public PgxGraph getData(String graphDataFile) throws ExecutionException, InterruptedException  {
        return getData(graphDataFile, Format.ADJ_LIST);
    }

    public PgxGraph getUnidirectData(String graphDataFile) throws ExecutionException, InterruptedException {
        return getData(graphDataFile, Format.ADJ_LIST).undirect();
    }

    public PgxGraph getData(String graphDataFile, Format format) throws ExecutionException, InterruptedException  {
        // pgx 2.3+
//        FileGraphConfig config = GraphConfigBuilder.forFileFormat(format)
//                .addUri(graphDataFile)
//                .setSeparator(" ")
//                .build();

        // pgx 2.2
        SingleFileGraphConfig config = GraphConfigBuilder.forSingleFileFormat(format)
                .setUri(graphDataFile)
                .setSeparator(" ")
                .build();
        return session.readGraphWithProperties(config);
    }

    public CompiledProgram compile(String greenMarlFile) throws ExecutionException, InterruptedException  {
        return session.compileProgram(greenMarlFile);
    }

    public void close() throws NullPointerException {
        if(session == null) throw new NullPointerException();
        session.close();
    }
}

package algorithms;

import oracle.pgx.api.CompiledProgram;

public class AlgorithmTest {

    protected static CompiledProgram pgm = null;
    protected static String dataFile = null;

    protected final static String DATA_FILE = "dFile";

    static {
        dataFile = System.getProperty(DATA_FILE);
    }
}

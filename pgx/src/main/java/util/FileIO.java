package util;

import oracle.pgx.api.PgxGraph;
import oracle.pgx.api.PgxSession;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileIO {

    public static String readFromFile(String filePath) throws IOException {
        final StringBuilder content = new StringBuilder();
        Files.lines(Paths.get(filePath)).forEach(s -> {
            content.append(s);
        });
        return content.toString();

    }

    public static JsonStructure readJson(String filePath) throws FileNotFoundException {
        JsonReader reader = Json.createReader(new FileReader(filePath));
        return reader.read();
    }
}
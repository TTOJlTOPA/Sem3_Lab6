package by.sem3.lab6_7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public abstract class FormatReader {
    private BufferedReader reader;

    FormatReader(String path) throws IOException {
        this.reader = new BufferedReader(new FileReader(path));
    }

    protected String readLine() throws IOException {
        return reader.readLine();
    }

    protected abstract List<String[]> read() throws IOException;

    protected void close() throws IOException {
        reader.close();
    }
}

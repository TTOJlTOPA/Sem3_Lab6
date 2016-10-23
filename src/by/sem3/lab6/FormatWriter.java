package by.sem3.lab6;

import java.io.FileWriter;
import java.io.IOException;

public abstract class FormatWriter {
    private FileWriter writer;

    protected FormatWriter(String path) throws IOException {
        this.writer = new FileWriter(path);
    }

    protected void write(Format format) throws IOException {
        writer.write(format.getFormat());
    }

    protected void flush() throws IOException {
        writer.flush();
    }

    protected void close() throws IOException {
        writer.close();
    }
}

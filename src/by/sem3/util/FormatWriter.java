package by.sem3.util;

import java.io.FileWriter;
import java.io.IOException;

public abstract class FormatWriter {
    private FileWriter writer;

    protected FormatWriter(String path) throws IOException {
        this.writer = new FileWriter(path, false);
    }

    protected void write(Format format) throws IOException {
        writer.write(format.getFormat());
    }

    public void flush() throws IOException {
        writer.flush();
    }

    public void close() throws IOException {
        writer.close();
    }
}

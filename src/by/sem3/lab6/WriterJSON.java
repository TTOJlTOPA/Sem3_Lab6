package by.sem3.lab6;

import java.io.IOException;

public class WriterJSON extends FormatWriter {
    public WriterJSON(String path) throws IOException {
        super(path);
    }

    @Override
    public void write(Format json) throws IOException {

    }

    @Override
    public void flush() throws IOException {
        super.flush();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}

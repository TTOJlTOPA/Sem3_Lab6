package by.sem3.lab6;

import java.io.IOException;

public class WriterJSON extends FormatWriter {
    WriterJSON(String path) throws IOException {
        super(path);
    }

    @Override
    public void write() throws IOException{

    }

    @Override
    public void flush() throws IOException{
        super.flush();
    }

    @Override
    public void close() throws IOException{
        super.close();
    }
}

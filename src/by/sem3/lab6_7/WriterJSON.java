package by.sem3.lab6_7;

import java.io.IOException;

public class WriterJSON extends FormatWriter {
    public WriterJSON(String path) throws IOException {
        super(path);
    }

    public void write(FormatJSON json) throws IOException {
        super.write(json);
    }
}

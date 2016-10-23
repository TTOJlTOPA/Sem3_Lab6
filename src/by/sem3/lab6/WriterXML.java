package by.sem3.lab6;

import java.io.IOException;

public class WriterXML extends FormatWriter {
    public WriterXML(String path) throws IOException {
        super(path);
    }

    public void write(FormatXML xml) throws IOException {
        super.write(xml);
    }
}

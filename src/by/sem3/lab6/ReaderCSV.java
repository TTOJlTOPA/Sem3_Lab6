package by.sem3.lab6;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderCSV extends FormatReader {
    public ReaderCSV(String path) throws IOException {
        super(path);
    }

    public String[] nameOfColumns() throws IOException {
        return super.readLine().split(";");
    }

    @Override
    public List<String[]> read() throws IOException {
        List<String[]> list = new ArrayList();
        String line;
        super.readLine();
        while ((line = super.readLine()) != null) {
            list.add(line.split(";"));
        }
        return list;
    }

    @Override
    public void close() throws IOException {
        super.close();
    }
}

package by.sem3.lab6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    private String[] nameOfColumns(String path) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        return reader.readLine().split(";");
    }

    private List<Company> readFromCSV(String path) throws IOException {
        List<Company> listOfCompanies = new ArrayList<>();
        String[] parameters;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String line;
        reader.readLine();
        while (!(line = reader.readLine()).isEmpty()) {
            parameters = line.split(";");
            listOfCompanies.add(new Company(parameters));
        }
        return listOfCompanies;
    }
}

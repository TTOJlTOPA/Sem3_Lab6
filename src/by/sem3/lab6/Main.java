package by.sem3.lab6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String filePath = "resources/input.csv";
        try {
            String[] columns = nameOfColumns(filePath);
            print(columns);
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    private static String[] nameOfColumns(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        return reader.readLine().split(";");
    }

    private static List<Company> readFromCSV(String path) throws IOException {
        List<Company> listOfCompanies = new ArrayList<>();
        String[] parameters;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String line;
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            parameters = line.split(";");
            listOfCompanies.add(new Company(parameters));
        }
        return listOfCompanies;
    }

    private static void print(String[] columnsNames){
        System.out.format("%9s%16s%19s%19s%28s%23s%12s%12s%20s%20s%15s%20s", columnsNames);
    }
}

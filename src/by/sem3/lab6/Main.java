package by.sem3.lab6;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String filePath = "resources/input.csv";
        try {
            String[] columns = Functional.nameOfColumns(filePath);
            List<Company> listOfCompanies = Functional.readFromCSV(filePath);
            Functional.print(columns, listOfCompanies);
            System.out.println("\n\n");
            Functional.menu(listOfCompanies);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

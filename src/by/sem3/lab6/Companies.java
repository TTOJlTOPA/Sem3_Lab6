package by.sem3.lab6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Companies {
    private List<Company> companies;

    public Companies(List<Company> companies) {
        this.companies = new ArrayList();
        this.companies = companies;
    }

    public Companies(String csvPath) throws IOException {
        this.companies = new ArrayList();
        readCSV(csvPath);
    }

    private void readCSV(String csvPath) throws IOException {
        String[] parameters;
        BufferedReader reader = new BufferedReader(new FileReader(csvPath));
        String line;
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            parameters = line.split(";");
            companies.add(new Company(parameters));
        }
    }

    public void print(String[] columnsNames) {
        System.out.format("%9s%16s%19s%19s%28s%23s%12s%12s%20s%20s%15s%20s", columnsNames);
        System.out.println();
        for (Company iter : companies) {
            iter.print();
        }
    }

    public Company findByShortTitle() throws IOException {
        Scanner scan = new Scanner(System.in);
        String title;
        System.out.print("Enter short title: ");
        title = scan.nextLine();
        for (Company iter : companies) {
            if (title.equalsIgnoreCase(iter.getShortTitle())) {
                return iter;
            }
        }
        System.out.println("Not found.");
        return null;
    }

    public Stream<Company> filterByBranch(String inBranch) throws IOException {
        Stream<Company> selection;
        selection = (companies.stream()).filter(company -> (company.getBranch()).equalsIgnoreCase(inBranch));
        return selection;
    }

    public Stream<Company> filterByActivity(String inActivity) throws IOException {
        Stream<Company> selection;
        selection = (companies.stream()).filter(company -> (company.getActivity()).equalsIgnoreCase(inActivity));
        return selection;
    }

    public Stream<Company> filterByDateOfFoundation(String fromDate, String toDate) throws IOException {
        Stream<Company> selection;
        selection = (companies.stream()).filter(company -> company.isDateOfFoundationInInterval(fromDate, toDate));
        return selection;
    }

    public Stream<Company> filterByCountOfEmployees(int fromCount, int toCount)
            throws IOException {
        Stream<Company> selection;
        selection = (companies.stream()).filter(company -> company.isCountOfEmployeesInInterval(fromCount, toCount));
        return selection;
    }
}

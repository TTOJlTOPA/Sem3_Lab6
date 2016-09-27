package by.sem3.lab6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final String filePath = "resources/input.csv";
        try {
            String[] columns = nameOfColumns(filePath);
            List<Company> listOfCompanies = readFromCSV(filePath);
            Company find;
            print(columns, listOfCompanies);
            System.out.println("\n\n");
            find = findByShortTitle(listOfCompanies);
            if (find != null) {
                find.print();
            } else {
                System.out.println("Not found!");
            }
            filterByBranch(listOfCompanies);
            filterByActivity(listOfCompanies);
            filterByDateOfFoundation(listOfCompanies);
            filterByCountOfEmployees(listOfCompanies);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static String[] nameOfColumns(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        return reader.readLine().split(";");
    }

    private static List<Company> readFromCSV(String path) throws IOException {
        List<Company> listOfCompanies = new ArrayList<>();
        String[] parameters;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            parameters = line.split(";");
            listOfCompanies.add(new Company(parameters));
        }
        return listOfCompanies;
    }

    private static void print(String[] columnsNames, List<Company> companyList) {
        System.out.format("%9s%16s%19s%19s%28s%23s%12s%12s%20s%20s%15s%20s", columnsNames);
        System.out.println();
        for (Company iter : companyList) {
            iter.print();
        }
    }

    private static Company findByShortTitle(List<Company> companyList) throws IOException {
        Scanner scan = new Scanner(System.in);
        String title;
        System.out.print("Enter short title: ");
        title = scan.nextLine();
        for (Company iter : companyList) {
            if (title.equalsIgnoreCase(iter.getShortTitle())) {
                return iter;
            }
        }
        return null;
    }

    private static void filterByBranch(List<Company> companyList) throws IOException{
        Scanner scan = new Scanner(System.in);
        Stream<Company> selection;
        String inBranch;
        System.out.print("Enter branch: ");
        inBranch = scan.nextLine();
        selection = (companyList.stream()).filter(company -> (company.getBranch()).equalsIgnoreCase(inBranch));
        selection.forEach(company -> company.print());
    }

    private static void filterByActivity(List<Company> companyList) throws IOException{
        Scanner scan = new Scanner(System.in);
        Stream<Company> selection;
        String inActivity;
        System.out.print("Enter branch: ");
        inActivity = scan.nextLine();
        selection = (companyList.stream()).filter(company -> (company.getActivity()).equalsIgnoreCase(inActivity));
        selection.forEach(company -> company.print());
    }

    private static void filterByDateOfFoundation(List<Company> companyList) throws IOException{
        Scanner scan = new Scanner(System.in);
        Stream<Company> selection;
        String fromDate;
        String toDate;
        System.out.print("Enter date interval:\nFrom: ");
        fromDate = scan.nextLine();
        System.out.print("To: ");
        toDate = scan.nextLine();
        selection = (companyList.stream()).filter(company -> company.isDateOfFoundationInInterval(fromDate, toDate));
        selection.forEach(company -> company.print());
    }

    private static void filterByCountOfEmployees(List<Company> companyList) throws IOException{
        Scanner scan = new Scanner(System.in);
        Stream<Company> selection;
        int fromCount;
        int toCount;
        System.out.print("Enter date interval:\nFrom: ");
        fromCount = scan.nextInt();
        System.out.print("To: ");
        toCount = scan.nextInt();
        selection = (companyList.stream()).filter(company -> company.isCountOfEmployeesInInterval(fromCount, toCount));
        selection.forEach(company -> company.print());
    }
}

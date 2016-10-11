package by.sem3.lab6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

class Functional {
    private static Logger logger;

    public static void run() {
        try {
            logger = new Logger();
            logger.write("Process started.");
            try {
                logger.write("Start read the table.");
                String[] columns = nameOfColumns("resources/input.csv");
                List<Company> listOfCompanies = readFromCSV("resources/input.csv");
                logger.write("Read successful.");
                print(columns, listOfCompanies);
                System.out.println("\n\n");
                menu(listOfCompanies);
                logger.flush();
            } catch (IOException e) {
                System.out.println(e);
                logger.write(e.toString());
                logger.flush();
            }
        } catch (LoggerException e) {
            System.out.println(e);
        }
    }

    public static void menu(List<Company> companyList) throws IOException, LoggerException {
        Scanner scan = new Scanner(System.in);
        int answer;
        System.out.print("\tChoose an action:\n1. Find company by short title.\n2. Filter companies by branch.\n" +
                "3. Filter companies by activity.\n4. Filter companies by date of foundation.\n" +
                "5. Filter companies by count of employees.\nEnter your choice: ");
        answer = scan.nextInt();
        switch (answer) {
            case 1:
                case1(companyList);
                break;
            case 2:
                case2(companyList);
                break;
            case 3:
                case3(companyList);
                break;
            case 4:
                case4(companyList);
                break;
            case 5:
                case5(companyList);
                break;
            default:
                throw new IOException("Your answer is not in range from 1 to 5.");
        }
    }

    private static void case1(List<Company> companies) throws IOException, LoggerException {
        logger.write("Start search.");
        Company company = findByShortTitle(companies);
        if (company != null) {
            logger.write("Search successful.");
            logger.write("Start write to XML.");
            writeToXML(company);
            logger.write("Write to XML successful.");
            logger.write("Start write to JSON.");
            writeToJSON(company);
            logger.write("Write to JSON successful.");
        } else {
            System.out.println("Not found.");
            logger.write("Object no found.");
        }
    }

    private static void case2(List<Company> companies) throws IOException, LoggerException {
        Scanner scanner = new Scanner(System.in);
        String branch;
        System.out.print("Enter branch: ");
        branch = scanner.nextLine();
        logger.write("Start filter by branch.");
        filterByBranch(companies, branch).forEach(comp -> comp.print());
        logger.write("Filter successful.");
        logger.write("Start write to XML.");
        writeToXML(filterByBranch(companies, branch));
        logger.write("Write to XML successful.");
        logger.write("Start write to JSON.");
        writeToJSON(filterByBranch(companies, branch));
        logger.write("Write to JSON successful.");
    }

    private static void case3(List<Company> companies) throws IOException, LoggerException {
        Scanner scanner = new Scanner(System.in);
        String activity;
        System.out.print("Enter activity: ");
        activity = scanner.nextLine();
        logger.write("Start filter by activity.");
        filterByActivity(companies, activity).forEach(comp -> comp.print());
        logger.write("Filter successful.");
        logger.write("Start write to XML.");
        writeToXML(filterByActivity(companies, activity));
        logger.write("Write to XML successful.");
        logger.write("Start write to JSON.");
        writeToJSON(filterByActivity(companies, activity));
        logger.write("Write to JSON successful.");
    }

    private static void case4(List<Company> companies) throws IOException, LoggerException {
        Scanner scanner = new Scanner(System.in);
        String from;
        String to;
        System.out.print("Enter date interval:\nFrom: ");
        from = scanner.nextLine();
        System.out.print("To: ");
        to = scanner.nextLine();
        logger.write("Start filter by date of foundation.");
        filterByDateOfFoundation(companies, from, to).forEach(comp -> comp.print());
        logger.write("Filter successful.");
        logger.write("Start write to XML.");
        writeToXML(filterByDateOfFoundation(companies, from, to));
        logger.write("Write to XML successful.");
        logger.write("Start write to JSON.");
        writeToJSON(filterByDateOfFoundation(companies, from, to));
        logger.write("Write to JSON successful.");
    }

    private static void case5(List<Company> companies) throws IOException, LoggerException {
        Scanner scanner = new Scanner(System.in);
        int fromNum;
        int toNum;
        System.out.print("Enter count interval:\nFrom: ");
        fromNum = scanner.nextInt();
        System.out.print("To: ");
        toNum = scanner.nextInt();
        logger.write("Start filter by count of employees.");
        filterByCountOfEmployees(companies, fromNum, toNum).forEach(comp -> comp.print());
        logger.write("Filter successful.");
        logger.write("Start write to XML.");
        writeToXML(filterByCountOfEmployees(companies, fromNum, toNum));
        logger.write("Write to XML successful.");
        logger.write("Start write to JSON.");
        writeToJSON(filterByCountOfEmployees(companies, fromNum, toNum));
        logger.write("Write to JSON successful.");
    }

    public static String[] nameOfColumns(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        return reader.readLine().split(";");
    }

    public static List<Company> readFromCSV(String path) throws IOException {
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

    public static void print(String[] columnsNames, List<Company> companyList) {
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
        System.out.println("Not found.");
        return null;
    }

    private static Stream<Company> filterByBranch(List<Company> companyList, String inBranch) throws IOException {
        Stream<Company> selection;
        selection = (companyList.stream()).filter(company -> (company.getBranch()).equalsIgnoreCase(inBranch));
        return selection;
    }

    private static Stream<Company> filterByActivity(List<Company> companyList, String inActivity) throws IOException {
        Stream<Company> selection;
        selection = (companyList.stream()).filter(company -> (company.getActivity()).equalsIgnoreCase(inActivity));
        return selection;
    }

    private static Stream<Company> filterByDateOfFoundation(List<Company> companyList, String fromDate, String toDate)
            throws IOException {
        Stream<Company> selection;
        selection = (companyList.stream()).filter(company -> company.isDateOfFoundationInInterval(fromDate, toDate));
        return selection;
    }

    private static Stream<Company> filterByCountOfEmployees(List<Company> companyList, int fromCount, int toCount)
            throws IOException {
        Stream<Company> selection;
        selection = (companyList.stream()).filter(company -> company.isCountOfEmployeesInInterval(fromCount, toCount));
        return selection;
    }

    private static void writeToXML(Company comp) throws IOException {
        FileWriter writer = new FileWriter("out/outputXML.xml");
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<company>\n" + comp.toXML() + "</company>");
        writer.flush();
    }

    private static void writeToXML(Stream<Company> companyStream) throws IOException {
        FileWriter writer = new FileWriter("out/outputXML.xml");
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<company>\n");
        companyStream.forEach(company -> {
            try {
                writer.write(company.toXML());
            } catch (IOException e) {
                System.out.println(e);
            }
        });
        writer.write("</company>");
        writer.flush();
    }

    private static void writeToJSON(Company comp) throws IOException {
        FileWriter writer = new FileWriter("out/outputJSON.json");
        writer.write("{\n" + comp.toJSON() + "\n}");
        writer.flush();
    }

    private static void writeToJSON(Stream<Company> companyStream) throws IOException {
        FileWriter writer = new FileWriter("out/outputJSON.json");
        Iterator<Company> iter = companyStream.iterator();
        writer.write("{\n");
        while (iter.hasNext()) {
            writer.write(iter.next().toJSON());
            if (iter.hasNext()) {
                writer.write(",\n");
            } else {
                writer.write("\n");
            }
        }
        writer.write("}");
        writer.flush();
    }
}

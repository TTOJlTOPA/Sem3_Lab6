package by.sem3.lab6;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
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
                ReaderCSV readerCSV = new ReaderCSV("resources/input.csv");
                Companies companies = new Companies(readerCSV.read());
                readerCSV.close();
                logger.write("Read successful.");
                companies.print();
                System.out.println("\n");
                menu(companies);
                logger.flush();
            } catch (IOException | CompaniesIsEmptyException e) {
                System.out.println(e);
                logger.write(e.toString());
                logger.flush();
            }
        } catch (LoggerException e) {
            System.out.println(e);
        }
    }

    public static void menu(Companies companies) throws IOException, LoggerException, CompaniesIsEmptyException {
        Scanner scan = new Scanner(System.in);
        int answer;
        System.out.print("\tChoose an action:\n1. Find company by short title.\n2. Filter companies by branch.\n" +
                "3. Filter companies by activity.\n4. Filter companies by date of foundation.\n" +
                "5. Filter companies by count of employees.\nEnter your choice: ");
        answer = scan.nextInt();
        switch (answer) {
            case 1:
                case1(companies);
                break;
            case 2:
                case2(companies);
                break;
            case 3:
                case3(companies);
                break;
            case 4:
                case4(companies);
                break;
            case 5:
                case5(companies);
                break;
            default:
                throw new IOException("Your answer is not in range from 1 to 5.");
        }
    }

    private static void case1(Companies companies) throws IOException, LoggerException, CompaniesIsEmptyException {
        logger.write("Start search.");
        Company company = companies.findByShortTitle();
        if (company != null) {
            logger.write("Search successful.");
            company.printColumnsNames();
            company.print();
            logger.write("Start write to XML.");
            company.writeToXML();
            logger.write("Write to XML successful.");
            logger.write("Start write to JSON.");
            company.writeToJSON();
            logger.write("Write to JSON successful.");
        } else {
            System.out.println("Not found.");
            logger.write("Object no found.");
        }
    }

    private static void case2(Companies companies) throws IOException, LoggerException, CompaniesIsEmptyException {
        Scanner scanner = new Scanner(System.in);
        String branch;
        System.out.print("Enter branch: ");
        branch = scanner.nextLine();
        logger.write("Start filter by branch.");
        companies.printColumnsNames();
        companies.filterByBranch(branch).forEach(comp -> comp.print());
        logger.write("Filter successful.");
        logger.write("Start write to XML.");
        writeToXML(companies.filterByBranch(branch));
        logger.write("Write to XML successful.");
        logger.write("Start write to JSON.");
        writeToJSON(companies.filterByBranch(branch));
        logger.write("Write to JSON successful.");
    }

    private static void case3(Companies companies) throws IOException, LoggerException, CompaniesIsEmptyException {
        Scanner scanner = new Scanner(System.in);
        String activity;
        System.out.print("Enter activity: ");
        activity = scanner.nextLine();
        logger.write("Start filter by activity.");
        companies.printColumnsNames();
        companies.filterByActivity(activity).forEach(comp -> comp.print());
        logger.write("Filter successful.");
        logger.write("Start write to XML.");
        writeToXML(companies.filterByActivity(activity));
        logger.write("Write to XML successful.");
        logger.write("Start write to JSON.");
        writeToJSON(companies.filterByActivity(activity));
        logger.write("Write to JSON successful.");
    }

    private static void case4(Companies companies) throws IOException, LoggerException, CompaniesIsEmptyException {
        Scanner scanner = new Scanner(System.in);
        String from;
        String to;
        System.out.print("Enter date interval:\nFrom: ");
        from = scanner.nextLine();
        System.out.print("To: ");
        to = scanner.nextLine();
        logger.write("Start filter by date of foundation.");
        companies.printColumnsNames();
        companies.filterByDateOfFoundation(from, to).forEach(comp -> comp.print());
        logger.write("Filter successful.");
        logger.write("Start write to XML.");
        writeToXML(companies.filterByDateOfFoundation(from, to));
        logger.write("Write to XML successful.");
        logger.write("Start write to JSON.");
        writeToJSON(companies.filterByDateOfFoundation(from, to));
        logger.write("Write to JSON successful.");
    }

    private static void case5(Companies companies) throws IOException, LoggerException, CompaniesIsEmptyException {
        Scanner scanner = new Scanner(System.in);
        int fromNum;
        int toNum;
        System.out.print("Enter count interval:\nFrom: ");
        fromNum = scanner.nextInt();
        System.out.print("To: ");
        toNum = scanner.nextInt();
        logger.write("Start filter by count of employees.");
        companies.printColumnsNames();
        companies.filterByCountOfEmployees(fromNum, toNum).forEach(comp -> comp.print());
        logger.write("Filter successful.");
        logger.write("Start write to XML.");
        writeToXML(companies.filterByCountOfEmployees(fromNum, toNum));
        logger.write("Write to XML successful.");
        logger.write("Start write to JSON.");
        writeToJSON(companies.filterByCountOfEmployees(fromNum, toNum));
        logger.write("Write to JSON successful.");
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

package by.sem3.lab6;

import java.io.IOException;
import java.util.Scanner;

class Functional {
    private static Logger logger;
    private static WriterXML xmlWriter;
    private static WriterJSON jsonWriter;

    public static void run() {
        try {
            logger = new Logger("out/logfile.txt");
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
                logger.write("Program completed successfully.");
            } catch (Exception e) {
                e.printStackTrace();
                logger.write(e.toString());
                for (StackTraceElement item : e.getStackTrace()) {
                    logger.write(item.toString());
                }
            } finally {
                logger.close();
            }
        } catch (LoggerException e) {
            e.printStackTrace();
        }
    }

    private static void menu(Companies companies) throws Exception {
        Scanner scan = new Scanner(System.in);
        xmlWriter = new WriterXML("output/outputXML.xml");
        jsonWriter = new WriterJSON("output/outputJSON.json");
        int answer;
        System.out.print("\tChoose an action:\n1. Find company by short title.\n2. Filter companies by branch.\n" +
                "3. Filter companies by activity.\n4. Filter companies by date of foundation.\n" +
                "5. Filter companies by count of employees.\nEnter your choice: ");
        try {
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
        } catch (Exception e) {
            throw e;
        } finally {
            xmlWriter.close();
            jsonWriter.close();
        }
    }

    private static void case1(Companies companies)
            throws IOException, LoggerException, CompaniesIsEmptyException, IncorrectFormatException {
        logger.write("Start search.");
        Company company = companies.findByShortTitle();
        if (company != null) {
            logger.write("Search successful.");
            company.printColumnsNames();
            company.print();
            logger.write("Start write to XML.");
            xmlWriter.write(company.toXML());
            logger.write("Write to XML successful.");
            logger.write("Start write to JSON.");
            jsonWriter.write(company.toJSON());
            logger.write("Write to JSON successful.");
        } else {
            System.out.println("Not found.");
            logger.write("Object no found.");
        }
    }

    private static void case2(Companies companies)
            throws IOException, LoggerException, CompaniesIsEmptyException, IncorrectFormatException {
        Scanner scanner = new Scanner(System.in);
        String branch;
        System.out.print("Enter branch: ");
        branch = scanner.nextLine();
        logger.write("Start filter by branch.");
        companies.printColumnsNames();
        companies.filterByBranch(branch).forEach(Company::print);
        logger.write("Filter successful.");
        logger.write("Start write to XML.");
        xmlWriter.write(companies.toXML(companies.filterByBranch(branch)));
        logger.write("Write to XML successful.");
        logger.write("Start write to JSON.");
        jsonWriter.write(companies.toJSON(companies.filterByBranch(branch)));
        logger.write("Write to JSON successful.");
    }

    private static void case3(Companies companies)
            throws IOException, LoggerException, CompaniesIsEmptyException, IncorrectFormatException {
        Scanner scanner = new Scanner(System.in);
        String activity;
        System.out.print("Enter activity: ");
        activity = scanner.nextLine();
        logger.write("Start filter by activity.");
        companies.printColumnsNames();
        companies.filterByActivity(activity).forEach(Company::print);
        logger.write("Filter successful.");
        logger.write("Start write to XML.");
        xmlWriter.write(companies.toXML(companies.filterByActivity(activity)));
        logger.write("Write to XML successful.");
        logger.write("Start write to JSON.");
        jsonWriter.write(companies.toJSON(companies.filterByActivity(activity)));
        logger.write("Write to JSON successful.");
    }

    private static void case4(Companies companies)
            throws IOException, LoggerException, CompaniesIsEmptyException, IncorrectFormatException {
        Scanner scanner = new Scanner(System.in);
        String from;
        String to;
        System.out.print("Enter date interval:\nFrom: ");
        from = scanner.nextLine();
        System.out.print("To: ");
        to = scanner.nextLine();
        logger.write("Start filter by date of foundation.");
        companies.printColumnsNames();
        companies.filterByDateOfFoundation(from, to).forEach(Company::print);
        logger.write("Filter successful.");
        logger.write("Start write to XML.");
        xmlWriter.write(companies.toXML(companies.filterByDateOfFoundation(from, to)));
        logger.write("Write to XML successful.");
        logger.write("Start write to JSON.");
        jsonWriter.write(companies.toJSON(companies.filterByDateOfFoundation(from, to)));
        logger.write("Write to JSON successful.");
    }

    private static void case5(Companies companies)
            throws IOException, LoggerException, CompaniesIsEmptyException, IncorrectFormatException {
        Scanner scanner = new Scanner(System.in);
        int fromNum;
        int toNum;
        System.out.print("Enter count interval:\nFrom: ");
        fromNum = scanner.nextInt();
        System.out.print("To: ");
        toNum = scanner.nextInt();
        logger.write("Start filter by count of employees.");
        companies.printColumnsNames();
        companies.filterByCountOfEmployees(fromNum, toNum).forEach(Company::print);
        logger.write("Filter successful.");
        logger.write("Start write to XML.");
        xmlWriter.write(companies.toXML(companies.filterByCountOfEmployees(fromNum, toNum)));
        logger.write("Write to XML successful.");
        logger.write("Start write to JSON.");
        jsonWriter.write(companies.toJSON(companies.filterByCountOfEmployees(fromNum, toNum)));
        logger.write("Write to JSON successful.");
    }
}

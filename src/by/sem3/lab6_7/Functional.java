package by.sem3.lab6_7;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

class Functional {
    static WriterXML xmlWriter;
    static WriterJSON jsonWriter;
    static Logger logger;

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

    private static void menu(Companies companies)
            throws IOException, IncorrectFormatException, CompaniesIsEmptyException, LoggerException {
        Scanner scanner = new Scanner(System.in);
        boolean isContinue;
        int counter = 1;
        do {
            System.out.print("\tChoose type of request:\n1. Simple request.\n2. SQL request.\nEnter your choice: ");
            logger.write("Start choosing type of request.");
            try {
                switch (scanner.nextInt()) {
                    case 1:
                        logger.write("Request type is: simple.");
                        submenuSimpleRequest(companies, counter, scanner);
                        break;
                    case 2:
                        logger.write("Request type is: SQL.");
                        submenuSQLRequest(companies, counter, scanner);
                        break;
                    default:
                        logger.write("Method \"menu\": Answer is not in range from 1 to 2.");
                        System.out.println("Your answer is not in range from 1 to 2.");
                }
            } catch (InputMismatchException e) {
                logger.write("Method \"menu\": Incorrect input information. The answer must be an integer.");
                System.out.println("Incorrect input information. The answer must be an integer.");
            }
            isContinue = isContinuing("You want to make another request?\n1. Yes.\n2. No(Quit).\nEnter your choice: ",
                    scanner);
        } while (isContinue);
        scanner.close();
    }

    private static void submenuSimpleRequest(Companies companies, int counter, Scanner scanner)
            throws IOException, LoggerException, CompaniesIsEmptyException, IncorrectFormatException {
        boolean isContinue;
        do {
            System.out.print("\tChoose an action:\n1. Find company by short title.\n" +
                    "2. Filter companies by branch.\n3. Filter companies by activity.\n" +
                    "4. Filter companies by date of foundation.\n5. Filter companies by count of employees.\n" +
                    "Enter your choice: ");
            logger.write("Start choosing simple request.");
            try {
                switch (scanner.nextInt()) {
                    case 1:
                        filesCreation(counter);
                        Requests.findByShortTitle(companies);
                        counter = caseFinishing(counter);
                        break;
                    case 2:
                        filesCreation(counter);
                        Requests.filterByBranch(companies, scanner);
                        counter = caseFinishing(counter);
                        break;
                    case 3:
                        filesCreation(counter);
                        Requests.filterByActivity(companies, scanner);
                        counter = caseFinishing(counter);
                        break;
                    case 4:
                        filesCreation(counter);
                        Requests.filterByDateOfFoundation(companies, scanner);
                        counter = caseFinishing(counter);
                        break;
                    case 5:
                        filesCreation(counter);
                        Requests.filterByCountOfEmployees(companies, scanner);
                        counter = caseFinishing(counter);
                        break;
                    default:
                        logger.write("Method \"submenuSimpleRequest\": Answer is not in range from 1 to 5.");
                        System.out.println("Your answer is not in range from 1 to 5.");
                }
            } catch (InputMismatchException e) {
                logger.write("Method \"submenuSimpleRequest\": Incorrect input information. " +
                        "The answer must be an integer.");
                System.out.println("Incorrect input information. The answer must be an integer.");
            }
            isContinue = isContinuing("You want to continue with simple requests?\n1. Yes.\n" +
                    "2. No(Return to type selection).\nEnter your choice: ", scanner);
        } while (isContinue);
    }

    private static void submenuSQLRequest(Companies companies, int counter, Scanner scanner)
            throws LoggerException, IncorrectFormatException, IOException {
        String request;
        boolean isContinue;
        do {
            logger.write("Start entering SQL request.");
            try {
                scanner.nextLine();
                System.out.println("Enter SQL request:");
                request = scanner.nextLine();
                logger.write("Entered SQL request: " + request);
                if (isSQLRequestSelect(request)) {
                    try {
                        filesCreation(counter);
                        Requests.requestSQL(companies, request);
                        counter = caseFinishing(counter);
                    } catch (SQLException e) {
                        logger.write("Method \"submenuSQLRequest\": " + e.getMessage());
                        System.out.println(e.getMessage());
                    }
                } else {
                    logger.write("Method \"submenuSQLRequest\": Incorrect or unsupported request.");
                    System.out.println("Incorrect or unsupported request.");
                }
            } catch (InputMismatchException e) {
                logger.write("Method \"submenuSQLRequest\": Incorrect input information. " +
                        "The answer must be an integer.");
                System.out.println("Incorrect input information. The answer must be an integer.");
            }
            isContinue = isContinuing("You want to continue with SQL requests?\n1. Yes.\n" +
                    "2. No(Return to type selection).\nEnter your choice: ", scanner);
        } while(isContinue);
    }

    private static void filesCreation(int counter) throws IOException, LoggerException {
        logger.write("Creating XML file.");
        xmlWriter = new WriterXML("output/outputXML" + counter + ".xml");
        logger.write("Creating JSON file.");
        jsonWriter = new WriterJSON("output/outputJSON" + counter + ".json");
    }

    private static int caseFinishing(int counter) throws IOException, LoggerException {
        logger.write("Closing XML file.");
        xmlWriter.close();
        logger.write("Closing JSON file.");
        jsonWriter.close();
        counter++;
        return counter;
    }

    private static boolean isContinuing(String header, Scanner scanner) throws LoggerException{
        System.out.print(header);
        try {
            switch (scanner.nextInt()) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    logger.write("Method \"isContinuing\": Answer is not in range from 1 to 2.");
                    System.out.println("Your answer is not in range from 1 to 2.");
            }
        } catch (InputMismatchException e) {
            logger.write("Method \"isContinuing\": Incorrect input information. The answer must be an integer.");
            System.out.println("Incorrect input information. The answer must be an integer.");
        }
        return false;
    }

    private static boolean isSQLRequestSelect(String request) {
        Pattern pattern = Pattern.compile("SELECT ((((name)|(sh[o0]rtT[i1]tle)|(dateUpdate)|(address)|" +
                "(dateF[o0]undat[i1][o0]n)|(c[o0]untEmpl[o0]yees)|(aud[i1]t[o0]r)|(ph[o0]ne)|(ema[i1]l)|(branch)|" +
                "(act[i1]v[i1]ty)|(l[i1]nk))(,[ ]?((name)|(sh[o0]rtT[i1]tle)|(dateUpdate)|(address)|" +
                "(dateF[o0]undat[i1][o0]n)|(c[o0]untEmpl[o0]yees)|(aud[i1]t[o0]r)|(ph[o0]ne)|(ema[i1]l)|(branch)|" +
                "(act[i1]v[i1]ty)|(l[i1]nk)))+)|(ALL)) FROM dataBase( WHERE (((name)|(sh[o0]rtT[i1]tle)|(dateUpdate)|" +
                "(address)|(dateF[o0]undat[i1][o0]n)|(c[o0]untEmpl[o0]yees)|(aud[i1]t[o0]r)|(ph[o0]ne)|(ema[i1]l)|" +
                "(branch)|(act[i1]v[i1]ty)|(l[i1]nk))(( BETWEEN (\\d+|('[\\w+&\"\\-:! ]+')) " +
                "AND (\\d+|('[\\w+&\"\\-:! ]+')))|(([ ]?(([<>][=]?)|(=))[ ]?(\\d+|('[\\w+&\"\\-:! ]+')))( " +
                "((AND)|(OR)) ((name)|(sh[o0]rtT[i1]tle)|(dateUpdate)|(address)|(dateF[o0]undat[i1][o0]n)|" +
                "(c[o0]untEmpl[o0]yees)|(aud[i1]t[o0]r)|(ph[o0]ne)|(ema[i1]l)|(branch)|(act[i1]v[i1]ty)|(l[i1]nk))" +
                "([ ]?(([<>][=]?)|(=))[ ]?(\\d+|('[\\w+&\"\\-:! ]+'))))?))))?;", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(request).matches();
    }
}

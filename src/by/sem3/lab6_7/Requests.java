package by.sem3.lab6_7;

import by.sem3.util.FormatJSON;
import by.sem3.util.FormatXML;
import by.sem3.util.IncorrectFormatException;
import by.sem3.util.LoggerException;

import java.io.IOException;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Requests {
    public static void findByShortTitle(Companies companies)
            throws LoggerException, CompaniesIsEmptyException, IncorrectFormatException, IOException,
            IncorrectColumnsException {
        Functional.logger.write("Start search.");
        Company company = companies.findByShortTitle();
        if (company != null) {
            Functional.logger.write("Search successful.");
            CompanyUtil.printColumnsNames();
            CompanyUtil.print(company);
            Functional.logger.write("Start write to XML.");
            Functional.xmlWriter.write(CompanyUtil.singleElementToXML(company));
            Functional.logger.write("Write to XML successful.");
            Functional.logger.write("Start write to JSON.");
            Functional.jsonWriter.write(CompanyUtil.singleElementToJSON(company));
            Functional.logger.write("Write to JSON successful.");
        } else {
            System.out.println("Not found.");
            Functional.logger.write("Object no found.");
        }
    }

    public static void filterByBranch(Companies companies, Scanner scanner)
            throws LoggerException, CompaniesIsEmptyException, IncorrectFormatException, IOException,
            IncorrectColumnsException {
        String branch;
        System.out.print("Enter branch: ");
        branch = scanner.nextLine();
        Functional.logger.write("Start filter by branch.");
        CompanyUtil.printColumnsNames();
        companies.filterByBranch(branch).forEach(CompanyUtil::print);
        Functional.logger.write("Filter successful.");
        Functional.logger.write("Start write to XML.");
        Functional.xmlWriter.write(companies.toXML(companies.filterByBranch(branch)));
        Functional.logger.write("Write to XML successful.");
        Functional.logger.write("Start write to JSON.");
        Functional.jsonWriter.write(companies.toJSON(companies.filterByBranch(branch)));
        Functional.logger.write("Write to JSON successful.");
    }

    public static void filterByActivity(Companies companies, Scanner scanner)
            throws LoggerException, CompaniesIsEmptyException, IncorrectFormatException, IOException,
            IncorrectColumnsException {
        String activity;
        System.out.print("Enter activity: ");
        activity = scanner.nextLine();
        Functional.logger.write("Start filter by activity.");
        CompanyUtil.printColumnsNames();
        companies.filterByActivity(activity).forEach(CompanyUtil::print);
        Functional.logger.write("Filter successful.");
        Functional.logger.write("Start write to XML.");
        Functional.xmlWriter.write(companies.toXML(companies.filterByActivity(activity)));
        Functional.logger.write("Write to XML successful.");
        Functional.logger.write("Start write to JSON.");
        Functional.jsonWriter.write(companies.toJSON(companies.filterByActivity(activity)));
        Functional.logger.write("Write to JSON successful.");
    }

    public static void filterByDateOfFoundation(Companies companies, Scanner scanner)
            throws LoggerException, CompaniesIsEmptyException, IncorrectFormatException, IOException,
            IncorrectColumnsException {
        String from;
        String to;
        System.out.print("Enter date interval:\nFrom: ");
        from = scanner.nextLine();
        System.out.print("To: ");
        to = scanner.nextLine();
        Functional.logger.write("Start filter by date of foundation.");
        CompanyUtil.printColumnsNames();
        companies.filterByDateOfFoundation(from, to).forEach(CompanyUtil::print);
        Functional.logger.write("Filter successful.");
        Functional.logger.write("Start write to XML.");
        Functional.xmlWriter.write(companies.toXML(companies.filterByDateOfFoundation(from, to)));
        Functional.logger.write("Write to XML successful.");
        Functional.logger.write("Start write to JSON.");
        Functional.jsonWriter.write(companies.toJSON(companies.filterByDateOfFoundation(from, to)));
        Functional.logger.write("Write to JSON successful.");
    }

    public static void filterByCountOfEmployees(Companies companies, Scanner scanner)
            throws LoggerException, CompaniesIsEmptyException, IncorrectFormatException, IOException,
            IncorrectColumnsException {
        int fromNum;
        int toNum;
        System.out.print("Enter count interval:\nFrom: ");
        try {
            fromNum = scanner.nextInt();
            System.out.print("To: ");
            toNum = scanner.nextInt();
        } catch (InputMismatchException e) {
            Functional.logger.write("Method \"filterByCountOfEmployees\": Incorrect input information. " +
                    "The answer must be an integer.");
            System.out.println("Incorrect input information. The answer must be an integer.");
            return;
        }
        Functional.logger.write("Start filter by count of employees.");
        CompanyUtil.printColumnsNames();
        companies.filterByCountOfEmployees(fromNum, toNum).forEach(CompanyUtil::print);
        Functional.logger.write("Filter successful.");
        Functional.logger.write("Start write to XML.");
        Functional.xmlWriter.write(companies.toXML(companies.filterByCountOfEmployees(fromNum, toNum)));
        Functional.logger.write("Write to XML successful.");
        Functional.logger.write("Start write to JSON.");
        Functional.jsonWriter.write(companies.toJSON(companies.filterByCountOfEmployees(fromNum, toNum)));
        Functional.logger.write("Write to JSON successful.");
    }

    public static void requestSQL(Companies companies, String request)
            throws SQLException, LoggerException, IncorrectFormatException, IOException {
        Scanner scanner = new Scanner(request).useDelimiter("((,[ ]?)|([ ]?')|('[ ]?)|[ ;])");
        List<String> columns;
        List<String> logics;
        String format;
        String[] columnsNames;
        int groups[];
        int numberOfColumns;
        Pattern patternColumns = Pattern.compile("((name)|(sh[o0]rtT[i1]tle)|(dateUpdate)|(address)|" +
                "(dateF[o0]undat[i1][o0]n)|(c[o0]untEmpl[o0]yees)|(aud[i1]t[o0]r)|(ph[o0]ne)|(ema[i1]l)|(branch)|" +
                "(act[i1]v[i1]ty)|(l[i1]nk)|(all)|(\\*))", Pattern.CASE_INSENSITIVE);
        numberOfColumns = patternColumns.matcher("").groupCount() - 1;
        Functional.logger.write("Start parsing SQL request.");
        columns = parseSQLRequestOnColumns(patternColumns, scanner);
        logics = parseSQLRequestOnLogics(scanner);
        groups = numbersOfGroups(columns, patternColumns);
        format = formatOfColumns(groups);
        columnsNames = fillNamesOfColumns(groups, numberOfColumns);
        selectionSQL(companies, logics, format, columnsNames, groups, patternColumns, numberOfColumns);
        scanner.close();
    }

    private static void selectionSQL(Companies companies, List<String> logics, String format, String[] columnsNames,
                                     int[] groups, Pattern patternColumns, int numberOfColumns)
            throws LoggerException, IncorrectFormatException, IOException {
        Iterator<Company> iterator;
        List<Company> selection = companies.getStream()
                .filter(company -> getLogicsResult(logics, company, patternColumns))
                .collect(Collectors.toList());
        if (selection.size() > 0) {
            printNamesOfColumns(format, columnsNames);
            if (selection.size() > 1) {
                iterator = selection.iterator();
                while (iterator.hasNext()) {
                    printElement(format, fillColumns(groups, iterator.next(), numberOfColumns));
                }
                Functional.logger.write("Start write to XML.");
                Functional.xmlWriter.write(getArrayAsXML(groups, selection, numberOfColumns));
                Functional.logger.write("Write to XML successful.");
                Functional.logger.write("Start write to JSON.");
                Functional.jsonWriter.write(getArrayAsJSON(groups, selection, numberOfColumns));
                Functional.logger.write("Write to JSON successful.");
            } else {
                printElement(format, fillColumns(groups, selection.get(0), numberOfColumns));
                Functional.logger.write("Start write to XML.");
                Functional.xmlWriter.write(getElementAsXML(groups, selection.get(0)));
                Functional.logger.write("Write to XML successful.");
                Functional.logger.write("Start write to JSON.");
                Functional.jsonWriter.write(getElementAsJSON(groups, selection.get(0)));
                Functional.logger.write("Write to JSON successful.");
            }
        } else {
            Functional.logger.write("Method \"requestSQL\": Elements not found.");
            System.out.println("Not found.");
        }
    }

    private static List<String> parseSQLRequestOnColumns(Pattern patternColumns, Scanner scanner)
            throws SQLException {
        String token;
        List<String> result = new ArrayList<>();
        while (scanner.hasNext()) {
            token = scanner.next();
            if (token.equalsIgnoreCase("from")) {
                break;
            }
            if (patternColumns.matcher(token).matches()) {
                if (!result.contains(token)) {
                    result.add(token);
                } else {
                    throw new SQLException();
                }
            }
        }
        return result;
    }

    private static List<String> parseSQLRequestOnLogics(Scanner scanner) {
        List<String> result = new ArrayList<>();
        Pattern central = Pattern.compile(".+((<)|(>)|(<=)|(>=)|(=)).+");
        Pattern postfix = Pattern.compile(".+((<)|(>)|(<=)|(>=)|(=))");
        Pattern prefix = Pattern.compile("((<)|(>)|(<=)|(>=)|(=)).+");
        Matcher matcher;
        Scanner sc;
        String token;
        int length = central.matcher("").groupCount();
        boolean isLogics = false;
        while (scanner.hasNext()) {
            token = scanner.next();
            if (isLogics && (matcher = central.matcher(token)).matches()) {
                sc = new Scanner(token).useDelimiter("((<=)|(>=)|(<)|(>)|(=))");
                result.add(sc.next());
                result.add(getLogicOperator(length, matcher));
                result.add(sc.next());
            } else if (isLogics && (matcher = prefix.matcher(token)).matches()) {
                sc = new Scanner(token).useDelimiter("((<=)|(>=)|(<)|(>)|(=))");
                result.add(getLogicOperator(length, matcher));
                result.add(sc.next());
            } else if (isLogics && (matcher = postfix.matcher(token)).matches()) {
                sc = new Scanner(token).useDelimiter("((<=)|(>=)|(<)|(>)|(=))");
                result.add(sc.next());
                result.add(getLogicOperator(length, matcher));
            } else if (isLogics && !token.isEmpty()) {
                result.add(token);
            }
            if (token.equalsIgnoreCase("where")) {
                isLogics = true;
            }
        }
        return result;
    }

    private static String getLogicOperator(int length, Matcher matcher) {
        for (int i = 2; i < length + 1; i++) {
            if (matcher.group(i) != null) {
                return logicOperators(i);
            }
        }
        return null;
    }

    private static String logicOperators(int number) {
        switch (number) {
            case 2:
                return "<";
            case 3:
                return ">";
            case 4:
                return "<=";
            case 5:
                return ">=";
            case 6:
                return "=";
            default:
                return null;
        }
    }

    private static void printNamesOfColumns(String format, String[] namesOfColumns) {
        System.out.format(format, namesOfColumns);
        System.out.println();
    }

    private static void printElement(String format, String[] columns) {
        System.out.format(format, columns);
        System.out.println();
    }

    private static int[] numbersOfGroups(List<String> columns, Pattern patternColumns) {
        int length = columns.size();
        int[] groups = new int[length];
        for (int i = 0; i < length; i++) {
            groups[i] = numberOfColumn(patternColumns.matcher(columns.get(i)));
        }
        return groups;
    }

    private static int numberOfColumn(Matcher matcher) {
        int length = matcher.groupCount();
        if (matcher.find()) {
            for (int i = 2; i < length + 1; i++) {
                if (matcher.group(i) != null) {
                    return i;
                }
            }
        }
        return 0;
    }

    private static String formatOfColumns(int[] groups) {
        StringBuilder format = new StringBuilder();
        for (int group : groups) {
            format.append(formatsOfColumns(group));
        }
        return format.toString();
    }

    private static String formatsOfColumns(int numberOfColumn) {
        switch (numberOfColumn) {
            case 2:
                return "%-9s";
            case 3:
                return "%-16s";
            case 4:
                return "%-19s";
            case 5:
                return "%-19s";
            case 6:
                return "%-28s";
            case 7:
                return "%-23s";
            case 8:
                return "%-12s";
            case 9:
                return "%-12s";
            case 10:
                return "%-20s";
            case 11:
                return "%-20s";
            case 12:
                return "%-15s";
            case 13:
                return "%-20s";
            case 14:
                return "%-9s%-16s%-19s%-19s%-28s%-23s%-12s%-12s%-20s%-20s%-15s%-20s";
            case 15:
                return "%-9s%-16s%-19s%-19s%-28s%-23s%-12s%-12s%-20s%-20s%-15s%-20s";
            default:
                return null;
        }
    }

    private static String[] fillNamesOfColumns(int[] groups, int numberOfColumns) {
        List<String> columnsNames = new ArrayList<>();
        String[] result;
        if (groups.length == 1) {
            for (int i = 2; i < numberOfColumns; i++) {
                columnsNames.add(namesOfColumns(i));
            }
        } else {
            for (int group : groups) {
                columnsNames.add(namesOfColumns(group));
            }
        }
        result = new String[columnsNames.size()];
        return columnsNames.toArray(result);
    }

    private static String namesOfColumns(int numberOfColumn) {
        switch (numberOfColumn) {
            case 2:
                return "Name";
            case 3:
                return "Short Title";
            case 4:
                return "Date of update";
            case 5:
                return "Address";
            case 6:
                return "Date of foundation";
            case 7:
                return "Count of employees";
            case 8:
                return "Auditor";
            case 9:
                return "Phone";
            case 10:
                return "Email";
            case 11:
                return "Branch";
            case 12:
                return "Activity";
            case 13:
                return "Link";
            default:
                return null;
        }
    }

    private static String namesOfColumnsToNameOfField(int numberOfColumn) {
        switch (numberOfColumn) {
            case 2:
                return "name";
            case 3:
                return "shortTitle";
            case 4:
                return "dateUpdate";
            case 5:
                return "address";
            case 6:
                return "dateFoundation";
            case 7:
                return "countEmployees";
            case 8:
                return "auditor";
            case 9:
                return "phone";
            case 10:
                return "email";
            case 11:
                return "branch";
            case 12:
                return "activity";
            case 13:
                return "link";
            default:
                return null;
        }
    }

    private static String[] fillColumns(int[] groups, Company company, int numberOfColumns) {
        List<String> columns = new ArrayList<>();
        String[] result;
        if (groups.length == 1) {
            for (int i = 2; i < numberOfColumns; i++) {
                columns.add(getColumn(i, company));
            }
        } else {
            for (int group : groups) {
                columns.add(getColumn(group, company));
            }
        }
        result = new String[columns.size()];
        return columns.toArray(result);
    }

    private static String getColumn(int numberOfColumn, Company company) {
        switch (numberOfColumn) {
            case 2:
                return company.getName();
            case 3:
                return company.getShortTitle();
            case 4:
                return CompanyUtil.dateToString(company.getDateUpdate());
            case 5:
                return company.getAddress();
            case 6:
                return CompanyUtil.dateToString(company.getDateFoundation());
            case 7:
                return ((Integer) company.getCountEmployees()).toString();
            case 8:
                return company.getAuditor();
            case 9:
                return company.getPhone();
            case 10:
                return company.getEmail();
            case 11:
                return company.getBranch();
            case 12:
                return company.getActivity();
            case 13:
                return company.getLink();
            default:
                return null;
        }
    }

    private static boolean getLogicsResult(List<String> logics, Company company, Pattern patternColumns) {
        List<Boolean> predicates = new ArrayList<>();
        List<String> logicsOperators = new ArrayList<>();
        Pattern comparisonOperators = Pattern.compile("(([<>][=]?)|(=))");
        Matcher matcherColumns;
        String field = "";
        String token;
        int size;
        boolean lastResult = false;
        Iterator<String> iterator = logics.iterator();
        if (logics.isEmpty()) {
            return true;
        }
        while (iterator.hasNext()) {
            token = iterator.next();
            matcherColumns = patternColumns.matcher(token);
            if (matcherColumns.matches()) {
                matcherColumns.reset();
                field = getColumn(numberOfColumn(matcherColumns), company);
            }
            if (token.equalsIgnoreCase("between") && field != null && !field.isEmpty()) {
                return getResultOfBetweenPredicate(iterator, field);
            }
            if (comparisonOperators.matcher(token).matches() && field != null) {
                predicates.add(getResultOfPredicateWithComparisonOperator(field, iterator.next(), token));
            }
            if (token.equalsIgnoreCase("and") || token.equalsIgnoreCase("or")) {
                logicsOperators.add(token.toLowerCase());
            }
        }
        size = logicsOperators.size();
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                lastResult = getResultOfPredicateWithLogicOperator(predicates.get(i), predicates.get(i + 1),
                        logicsOperators.get(i));
                predicates.set(i + 1, lastResult);
            }
        } else {
            lastResult = predicates.get(0);
        }
        return lastResult;
    }

    private static boolean getResultOfBetweenPredicate(Iterator<String> iterator, String field) {
        String lowerBound;
        String upperBound;
        double fieldNumber;
        lowerBound = iterator.next();
        iterator.next();
        upperBound = iterator.next();
        try {
            fieldNumber = Double.parseDouble(field);
            double lowerBoundNumber = Double.parseDouble(lowerBound);
            double upperBoundNumber = Double.parseDouble(upperBound);
            return fieldNumber >= lowerBoundNumber && fieldNumber <= upperBoundNumber;
        } catch (NumberFormatException e) {
        }
        return field.compareTo(lowerBound) >= 0 && field.compareTo(upperBound) <= 0;
    }

    private static boolean getResultOfPredicateWithComparisonOperator(String field, String rightElement,
                                                                      String comparisonOperator) {
        double fieldNumber = 0;
        double rightElementNumber = 0;
        boolean isNumber = false;
        try {
            fieldNumber = Double.parseDouble(field);
            rightElementNumber = Double.parseDouble(rightElement);
            isNumber = true;
        } catch (NumberFormatException e) {
        }
        switch (comparisonOperator) {
            case "<":
                return isNumber ? fieldNumber < rightElementNumber : field.compareTo(rightElement) < 0;
            case "<=":
                return isNumber ? fieldNumber <= rightElementNumber : field.compareTo(rightElement) <= 0;
            case ">":
                return isNumber ? fieldNumber > rightElementNumber : field.compareTo(rightElement) > 0;
            case ">=":
                return isNumber ? fieldNumber >= rightElementNumber : field.compareTo(rightElement) >= 0;
            case "=":
                return isNumber ? fieldNumber == rightElementNumber : field.compareTo(rightElement) == 0;
            default:
                return false;
        }
    }

    private static boolean getResultOfPredicateWithLogicOperator(boolean left, boolean right, String logicOperator) {
        switch (logicOperator) {
            case "and":
                return left && right;
            case "or":
                return left || right;
            default:
                return false;
        }
    }

    private static FormatXML getElementAsXML(int[] groups, Company company) throws IncorrectFormatException {
        FormatXML xml = new FormatXML();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<company>\n");
        for (int group : groups) {
            xml.append("\t");
            xml.appendWithTag(namesOfColumnsToNameOfField(group), getColumn(group, company));
        }
        xml.append("</company>");
        return xml;
    }

    private static FormatJSON getElementAsJSON(int[] groups, Company company) throws IncorrectFormatException {
        FormatJSON json = new FormatJSON();
        json.append("{\n");
        for (int i = 0; i < groups.length; i++) {
            json.append("\t");
            json.appendWithNameOfField(namesOfColumnsToNameOfField(groups[i]), getColumn(groups[i], company));
            if (i < groups.length - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("}");
        return json;
    }

    private static FormatXML getArrayAsXML(int[] groups, List<Company> selection, int numberOfColumns)
            throws IncorrectFormatException {
        FormatXML xml = new FormatXML();
        Company current;
        Iterator<Company> iterator = selection.iterator();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<companies>\n");
        while (iterator.hasNext()) {
            xml.append("\t<company>\n");
            current = iterator.next();
            if (groups.length == 1) {
                for (int i = 2; i < numberOfColumns; i++) {
                    xml.append("\t\t");
                    xml.appendWithTag(namesOfColumnsToNameOfField(i), getColumn(i, current));
                }
            } else {
                for (int group : groups) {
                    xml.append("\t\t");
                    xml.appendWithTag(namesOfColumnsToNameOfField(group), getColumn(group, current));
                }
            }
            xml.append("\t</company>\n");
        }
        xml.append("</companies>");
        return xml;
    }

    private static FormatJSON getArrayAsJSON(int[] groups, List<Company> selection, int numberOfColumns)
            throws IncorrectFormatException {
        FormatJSON json = new FormatJSON();
        Company current;
        Iterator<Company> iterator = selection.iterator();
        json.append("{\n\t\"companies\": [\n");
        while (iterator.hasNext()) {
            json.append("\t\t{\n");
            current = iterator.next();
            if (groups.length == 1) {
                for (int i = 2; i < numberOfColumns; i++) {
                    json.append("\t\t\t");
                    json.appendWithNameOfField(namesOfColumnsToNameOfField(i), getColumn(i, current));
                    if (i < numberOfColumns - 1) {
                        json.append(",");
                    }
                    json.append("\n");
                }
            } else {
                for (int i = 0; i < groups.length; i++) {
                    json.append("\t\t\t");
                    json.appendWithNameOfField(namesOfColumnsToNameOfField(groups[i]),
                            getColumn(groups[i], current));
                    if (i < groups.length - 1) {
                        json.append(",");
                    }
                    json.append("\n");
                }
            }
            json.append("\t\t}");
            if (iterator.hasNext()) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("\t]\n}");
        return json;
    }
}

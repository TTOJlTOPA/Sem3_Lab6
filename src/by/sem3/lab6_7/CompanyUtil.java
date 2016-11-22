package by.sem3.lab6_7;

import by.sem3.util.FormatJSON;
import by.sem3.util.FormatXML;
import by.sem3.util.IncorrectFormatException;

import java.util.Date;

class CompanyUtil {
    public static Date stringToDate(String date) {
        String[] dateParam;
        dateParam = date.split("[.]");
        return new Date(Integer.parseInt(dateParam[2]), Integer.parseInt(dateParam[1]) - 1,
                Integer.parseInt(dateParam[0]));
    }

    public static String dateToString(Date date) {
        return Integer.toString(date.getDate()) + "." + Integer.toString(date.getMonth() + 1) + "." +
                Integer.toString(date.getYear());
    }

    public static void print(Company company) {
        System.out.format("%-9s%-16s%-19s%-19s%-28s%-23d%-12s%-12s%-20s%-20s%-15s%-20s",
                company.getName(), company.getShortTitle(), dateToString(company.getDateUpdate()), company.getAddress(),
                dateToString(company.getDateFoundation()), company.getCountEmployees(), company.getAuditor(),
                company.getPhone(), company.getEmail(), company.getBranch(), company.getActivity(), company.getLink());
        System.out.println();
    }

    public static void printColumnsNames() throws IncorrectColumnsException {
        String[] columnsNames = Company.getColumnsNames();
        if (columnsNames.length != 12) {
            throw new IncorrectColumnsException();
        } else {
            System.out.format("%-9s%-16s%-19s%-19s%-28s%-23s%-12s%-12s%-20s%-20s%-15s%-20s", Company.getColumnsNames());
            System.out.println();
        }
    }

    public static FormatXML singleElementToXML(Company company) throws IncorrectFormatException {
        FormatXML xml = new FormatXML();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<company>\n");
        xml.append("\t");
        xml.appendWithTag("name", company.getName());
        xml.append("\t");
        xml.appendWithTag("shortTitle", company.getShortTitle());
        xml.append("\t");
        xml.appendWithTag("dateUpdate", dateToString(company.getDateUpdate()));
        xml.append("\t");
        xml.appendWithTag("address", company.getAddress());
        xml.append("\t");
        xml.appendWithTag("dateFoundation", dateToString(company.getDateFoundation()));
        xml.append("\t");
        xml.appendWithTag("countEmployees", ((Integer) company.getCountEmployees()).toString());
        xml.append("\t");
        xml.appendWithTag("auditor", company.getAuditor());
        xml.append("\t");
        xml.appendWithTag("phone", company.getPhone());
        xml.append("\t");
        xml.appendWithTag("email", company.getEmail());
        xml.append("\t");
        xml.appendWithTag("branch", company.getBranch());
        xml.append("\t");
        xml.appendWithTag("activity", company.getActivity());
        xml.append("\t");
        xml.appendWithTag("link", company.getLink());
        xml.append("</company>");
        return xml;
    }

    public static FormatXML arrayElementToXML(Company company) throws IncorrectFormatException {
        FormatXML xml = new FormatXML();
        xml.append("\t<company>\n");
        xml.append("\t\t");
        xml.appendWithTag("name", company.getName());
        xml.append("\t\t");
        xml.appendWithTag("shortTitle", company.getShortTitle());
        xml.append("\t\t");
        xml.appendWithTag("dateUpdate", dateToString(company.getDateUpdate()));
        xml.append("\t\t");
        xml.appendWithTag("address", company.getAddress());
        xml.append("\t\t");
        xml.appendWithTag("dateFoundation", dateToString(company.getDateFoundation()));
        xml.append("\t\t");
        xml.appendWithTag("countEmployees", ((Integer) company.getCountEmployees()).toString());
        xml.append("\t\t");
        xml.appendWithTag("auditor", company.getAuditor());
        xml.append("\t\t");
        xml.appendWithTag("phone", company.getPhone());
        xml.append("\t\t");
        xml.appendWithTag("email", company.getEmail());
        xml.append("\t\t");
        xml.appendWithTag("branch", company.getBranch());
        xml.append("\t\t");
        xml.appendWithTag("activity", company.getActivity());
        xml.append("\t\t");
        xml.appendWithTag("link", company.getLink());
        xml.append("\t</company>\n");
        return xml;
    }

    public static FormatJSON singleElementToJSON(Company company) throws IncorrectFormatException {
        FormatJSON json = new FormatJSON();
        json.append("{\n\t");
        json.appendWithNameOfField("name", company.getName());
        json.append(",\n\t");
        json.appendWithNameOfField("shortTitle", company.getShortTitle());
        json.append(",\n\t");
        json.appendWithNameOfField("dateUpdate", dateToString(company.getDateUpdate()));
        json.append(",\n\t");
        json.appendWithNameOfField("address", company.getAddress());
        json.append(",\n\t");
        json.appendWithNameOfField("dateFoundation", dateToString(company.getDateFoundation()));
        json.append(",\n\t");
        json.appendWithNameOfField("countEmployees", ((Integer) company.getCountEmployees()).toString());
        json.append(",\n\t");
        json.appendWithNameOfField("auditor", company.getAuditor());
        json.append(",\n\t");
        json.appendWithNameOfField("phone", company.getPhone());
        json.append(",\n\t");
        json.appendWithNameOfField("email", company.getEmail());
        json.append(",\n\t");
        json.appendWithNameOfField("branch", company.getBranch());
        json.append(",\n\t");
        json.appendWithNameOfField("activity", company.getActivity());
        json.append(",\n\t");
        json.appendWithNameOfField("link", company.getLink());
        json.append("\n}");
        return json;
    }

    public static FormatJSON arrayElementToJSON(Company company, boolean isLast) throws IncorrectFormatException {
        FormatJSON json = new FormatJSON();
        json.append("\n\t\t{\n\t\t\t");
        json.appendWithNameOfField("name", company.getName());
        json.append(",\n\t\t\t");
        json.appendWithNameOfField("shortTitle", company.getShortTitle());
        json.append(",\n\t\t\t");
        json.appendWithNameOfField("dateUpdate", dateToString(company.getDateUpdate()));
        json.append(",\n\t\t\t");
        json.appendWithNameOfField("address", company.getAddress());
        json.append(",\n\t\t\t");
        json.appendWithNameOfField("dateFoundation", dateToString(company.getDateFoundation()));
        json.append(",\n\t\t\t");
        json.appendWithNameOfField("countEmployees", ((Integer) company.getCountEmployees()).toString());
        json.append(",\n\t\t\t");
        json.appendWithNameOfField("auditor", company.getAuditor());
        json.append(",\n\t\t\t");
        json.appendWithNameOfField("phone", company.getPhone());
        json.append(",\n\t\t\t");
        json.appendWithNameOfField("email", company.getEmail());
        json.append(",\n\t\t\t");
        json.appendWithNameOfField("branch", company.getBranch());
        json.append(",\n\t\t\t");
        json.appendWithNameOfField("activity", company.getActivity());
        json.append(",\n\t\t\t");
        json.appendWithNameOfField("link", company.getLink());
        json.append("\n\t\t}");
        if (!isLast) {
            json.append(",");
        }
        return json;
    }

    public static boolean isDateOfFoundationInInterval(Company company, String from, String to) {
        Date fromDate = stringToDate(from);
        Date toDate = stringToDate(to);
        Date companyDate = company.getDateFoundation();
        return companyDate.compareTo(fromDate) >= 0 && companyDate.compareTo(toDate) <= 0;
    }

    public static boolean isCountOfEmployeesInInterval(Company company, int from, int to) {
        int companyCountEmployees = company.getCountEmployees();
        return companyCountEmployees >= from && companyCountEmployees <= to;
    }
}

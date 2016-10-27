package by.sem3.lab6;

import java.util.Date;

class Company {
    private String name;
    private String shortTitle;
    private Date dateUpdate;
    private String address;
    private Date dateFoundation;
    private int countEmployees;
    private String auditor;
    private String phone;
    private String email;
    private String branch;
    private String activity;
    private String link;
    private static String[] columnsNames;

    public Company() {
        this.name = null;
        this.shortTitle = null;
        this.dateUpdate = new Date(0, 0, 0);
        this.address = null;
        this.dateFoundation = new Date(0, 0, 0);
        this.countEmployees = 0;
        this.auditor = null;
        this.phone = null;
        this.email = null;
        this.branch = null;
        this.activity = null;
        this.link = null;
        columnsNames = null;
    }

    public Company(String[] param) {
        this.name = param[0];
        this.shortTitle = param[1];
        this.dateUpdate = stringToDate(param[2]);
        this.address = param[3];
        this.dateFoundation = stringToDate(param[4]);
        this.countEmployees = Integer.parseInt(param[5]);
        this.auditor = param[6];
        this.phone = param[7];
        this.email = param[8];
        this.branch = param[9];
        this.activity = param[10];
        this.link = param[11];
    }

    private Date stringToDate(String date) {
        String[] dateParam;
        dateParam = date.split("[.]");
        return new Date(Integer.parseInt(dateParam[2]), Integer.parseInt(dateParam[1]) - 1,
                Integer.parseInt(dateParam[0]));
    }

    private String dateUpdateToString() {
        return Integer.toString(dateUpdate.getDate()) + "." + Integer.toString(dateUpdate.getMonth() + 1) + "." +
                Integer.toString(dateUpdate.getYear());
    }

    private String dateFoundationToString() {
        return Integer.toString(dateFoundation.getDate()) + "." + Integer.toString(dateFoundation.getMonth() + 1)
                + "." + Integer.toString(dateFoundation.getYear());
    }

    public void print() {
        System.out.format("%9s%16s%19s%19s%28s%23d%12s%12s%20s%20s%15s%20s", name, shortTitle, dateUpdateToString(),
                address, dateFoundationToString(), countEmployees, auditor, phone, email, branch, activity, link);
        System.out.println();
    }

    public void printColumnsNames() {
        System.out.format("%9s%16s%19s%19s%28s%23s%12s%12s%20s%20s%15s%20s", columnsNames);
        System.out.println();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateFoundation() {
        return dateFoundation;
    }

    public void setDateFoundation(Date dateFoundation) {
        this.dateFoundation = dateFoundation;
    }

    public int getCountEmployees() {
        return countEmployees;
    }

    public void setCountEmployees(int countEmployees) {
        this.countEmployees = countEmployees;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public static String[] getColumnsNames() {
        return columnsNames;
    }

    public static void setColumnsNames(String[] columnsNames) {
        Company.columnsNames = columnsNames;
    }

    public FormatXML toXML() throws IncorrectFormatException {
        FormatXML xml = new FormatXML();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<company>\n\t<name>" + name +
                "</name>\n\t<shorttitle>" + shortTitle + "</shorttitle>\n\t<dateupdate>" +
                dateUpdateToString() + "</dateupdate>\n\t<address>" + address + "</address>\n\t<datefoundation>" +
                dateFoundationToString() + "</datefoundation>\n\t<countemployees>" + countEmployees +
                "</countemployees>\n\t<auditor>" + auditor + "</auditor>\n\t<phone>" + phone + "</phone>\n\t<email>" +
                email + "</email>\n\t<branch>" + branch + "</branch>\n\t<activity>" + activity +
                "</activity>\n\t<link>" + link + "</link>\n</company>");
        return xml;
    }

    public FormatXML toXML(boolean isInArray) throws IncorrectFormatException {
        FormatXML xml = new FormatXML();
        if (isInArray) {
            xml.append("\t\t<company>\n\t\t\t<name>" + name + "</name>\n\t\t\t<shorttitle>" + shortTitle +
                    "</shorttitle>\n\t\t\t<dateupdate>" + dateUpdateToString() + "</dateupdate>\n\t\t\t<address>" +
                    address + "</address>\n\t\t\t<datefoundation>" + dateFoundationToString() +
                    "</datefoundation>\n\t\t\t<countemployees>" + countEmployees +
                    "</countemployees>\n\t\t\t<auditor>" + auditor + "</auditor>\n\t\t\t<phone>" + phone +
                    "</phone>\n\t\t\t<email>" + email + "</email>\n\t\t\t<branch>" + branch +
                    "</branch>\n\t\t\t<activity>" + activity + "</activity>\n\t\t\t<link>" + link +
                    "</link>\n\t\t</company>\n");
        } else {
            xml = toXML();
        }
        return xml;
    }

    public FormatJSON toJSON() throws IncorrectFormatException {
        FormatJSON json = new FormatJSON();
        json.append("{\n\t\"name\": \"" + name + "\",\n\t\"shortTitle\": \"" + shortTitle + "\",\n\t\"dateUpdate\": \"" +
                dateUpdateToString() + "\",\n\t\"address\": \"" + address + "\",\n\t\"dateFoundation\": \"" +
                dateFoundationToString() + "\",\n\t\"countEmployees\": " + countEmployees + ",\n\t\"auditor\": \"" +
                auditor + "\",\n\t\"phone\": \"" + phone + "\",\n\t\"email\": \"" + email + "\",\n\t\"branch\": \"" +
                branch + "\",\n\t\"activity\": \"" + activity + "\",\n\t\"link\": \"" + link + "\"\n}");
        return json;
    }

    public FormatJSON toJSON(boolean isInArray, boolean isLast) throws IncorrectFormatException {
        FormatJSON json = new FormatJSON();
        String tmp = "\n\t\t{\n\t\t\t\"name\": \"" + name + "\",\n\t\t\t\"shortTitle\": \"" + shortTitle +
                "\",\n\t\t\t\"dateUpdate\": \"" + dateUpdateToString() + "\",\n\t\t\t\"address\": \"" + address +
                "\",\n\t\t\t\"dateFoundation\": \"" + dateFoundationToString() +
                "\",\n\t\t\t\"countEmployees\": " + countEmployees + ",\n\t\t\t\"auditor\": \"" + auditor +
                "\",\n\t\t\t\"phone\": \"" + phone + "\",\n\t\t\t\"email\": \"" + email +
                "\",\n\t\t\t\"branch\": \"" + branch + "\",\n\t\t\t\"activity\": \"" + activity +
                "\",\n\t\t\t\"link\": \"" + link + "\"\n\t\t}";
        if (isInArray) {
            if (isLast) {
                json.append(tmp);
            } else {
                json.append(tmp + ",");
            }
        } else {
            json = toJSON();
        }
        return json;
    }

    public boolean isDateOfFoundationInInterval(String from, String to) {
        Date fromDate = stringToDate(from);
        Date toDate = stringToDate(to);
        return dateFoundation.compareTo(fromDate) >= 0 && dateFoundation.compareTo(toDate) <= 0;
    }

    public boolean isCountOfEmployeesInInterval(int from, int to) {
        return countEmployees >= from && countEmployees <= to;
    }
}

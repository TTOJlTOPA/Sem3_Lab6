package by.sem3.lab6;

import java.io.FileWriter;
import java.io.IOException;
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

    public Company() {
        this.name = "-";
        this.shortTitle = "-";
        this.dateUpdate = new Date(0, 0, 0);
        this.address = "-";
        this.dateFoundation = new Date(0, 0, 0);
        this.countEmployees = 0;
        this.auditor = "-";
        this.phone = "-";
        this.email = "-";
        this.branch = "-";
        this.activity = "-";
        this.link = "-";
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

    public String getName() {
        return name;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public String getAddress() {
        return address;
    }

    public Date getDateFoundation() {
        return dateFoundation;
    }

    public int getCountEmployees() {
        return countEmployees;
    }

    public String getAuditor() {
        return auditor;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getBranch() {
        return branch;
    }

    public String getActivity() {
        return activity;
    }

    public String getLink() {
        return link;
    }

    public String toXML() {
        return "\t<name>" + name + "</name>\n\t<shorttitle>" + shortTitle + "</shorttitle>\n\t<dateupdate>" +
                dateUpdateToString() + "</dateupdate>\n\t<address>" + address + "</address>\n\t<datefoundation>" +
                dateFoundationToString() + "</datefoundation>\n\t<countemployees>" + countEmployees +
                "</countemployees>\n\t<auditor>" + auditor + "</auditor>\n\t<phone>" + phone + "</phone>\n\t<email>" +
                email + "</email>\n\t<branch>" + branch + "</branch>\n\t<activity>" + activity +
                "</activity>\n\t<link>" + link + "</link>\n";
    }

    public void writeToXML() throws IOException {
        FileWriter writer = new FileWriter("out/outputXML.xml");
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<company>\n" + toXML() + "</company>");
        writer.flush();
    }

    public String toJSON() {
        return "\t\"name\": \"" + name + "\",\n\t\"shortTitle\": \"" + shortTitle + "\",\n\t\"dateUpdate\": \"" +
                dateUpdateToString() + "\",\n\t\"address\": \"" + address + "\",\n\t\"dateFoundation\": \"" +
                dateFoundationToString() + "\",\n\t\"countEmployees\": \"" + countEmployees + "\",\n\t\"auditor\": \"" +
                auditor + "\",\n\t\"phone\": \"" + phone + "\",\n\t\"email\": \"" + email + "\",\n\t\"branch\": \"" +
                branch + "\",\n\t\"activity\": \"" + activity + "\",\n\t\"link\": \"" + link + "\"";
    }

    public void writeToJSON() throws IOException {
        FileWriter writer = new FileWriter("out/outputJSON.json");
        writer.write("{\n" + toJSON() + "\n}");
        writer.flush();
    }

    public boolean isDateOfFoundationInInterval(String from, String to) {
        Date fromDate = stringToDate(from);
        Date toDate = stringToDate(to);
        if (dateFoundation.compareTo(fromDate) >= 0 && dateFoundation.compareTo(toDate) <= 0) {
            return true;
        }
        return false;
    }

    public boolean isCountOfEmployeesInInterval(int from, int to) {
        if (countEmployees >= from && countEmployees <= to) {
            return true;
        }
        return false;
    }
}

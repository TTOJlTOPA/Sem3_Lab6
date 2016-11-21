package by.sem3.lab6_7;

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
        columnsNames = null;
    }

    public Company(String[] param) {
        this.name = param[0];
        this.shortTitle = param[1];
        this.dateUpdate = CompanyUtil.stringToDate(param[2]);
        this.address = param[3];
        this.dateFoundation = CompanyUtil.stringToDate(param[4]);
        this.countEmployees = Integer.parseInt(param[5]);
        this.auditor = param[6];
        this.phone = param[7];
        this.email = param[8];
        this.branch = param[9];
        this.activity = param[10];
        this.link = param[11];
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
}

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

    Company() {
        name = "-";
        shortTitle = "-";
        dateUpdate = new Date(0, 0, 0);
        address = "-";
        dateFoundation = new Date(0, 0, 0);
        countEmployees = 0;
        auditor = "-";
        phone = "-";
        email = "-";
        branch = "-";
        activity = "-";
        link = "-";
    }

    Company(String[] param) {
        name = param[0];
        shortTitle = param[1];
        dateUpdate = stringToDate(param[2]);
        address = param[3];
        dateFoundation = stringToDate(param[4]);
        countEmployees = Integer.parseInt(param[5]);
        auditor = param[6];
        phone = param[7];
        email = param[8];
        branch = param[9];
        activity = param[10];
        link = param[11];
    }

    private Date stringToDate(String date) {
        String[] dateParam;
        dateParam = date.split("[.-]");
        return new Date(Integer.parseInt(dateParam[2]), Integer.parseInt(dateParam[1]) - 1,
                Integer.parseInt(dateParam[0]));
    }

    public void print() {
        System.out.format("%9s%16s%19s%19s%28s%23d%12s%12s%20s%20s%15s%20s", name, shortTitle,
                Integer.toString(dateUpdate.getDate()) + "." + Integer.toString(dateUpdate.getMonth()) + "." +
                        Integer.toString(dateUpdate.getYear()), address, Integer.toString(dateFoundation.getDate()) + "." +
                        Integer.toString(dateFoundation.getMonth() + 1) + "." + Integer.toString(dateFoundation.getYear()),
                countEmployees, auditor, phone, email, branch, activity, link);
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

    public boolean isDateOfFoundationInInterval(String from, String to){
        Date fromDate = stringToDate(from);
        Date toDate = stringToDate(to);
        if(dateFoundation.compareTo(fromDate) >= 0 && dateFoundation.compareTo(toDate) <= 0){
            return true;
        }
        return false;
    }

    public boolean isCountOfEmployeesInInterval(int from, int to){
        if(countEmployees >= from && countEmployees <= to){
            return true;
        }
        return false;
    }
}

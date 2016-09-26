package by.sem3.lab6;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Company {
    private String name;
    private String shortTitle;
    private Date dateUpdate;
    private String adress;
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
        adress = "-";
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
        dateUpdate = toDate(param[2]);
        adress = param[3];
        dateFoundation = toDate(param[4]);
        countEmployees = Integer.parseInt(param[5]);
        auditor = param[6];
        phone = param[7];
        email = param[8];
        branch = param[9];
        activity = param[10];
        link = param[11];
    }

    private boolean isName(String name) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private Date toDate(String date) {
        String[] dateParam;
        dateParam = date.split("[.]");
        return new Date(Integer.parseInt(dateParam[2]), Integer.parseInt(dateParam[1]),
                Integer.parseInt(dateParam[0]));
    }

    public void print(){
        System.out.format("%9s%16s%19s%19s%28s%23d%12s%12s%20s%20s%15s%20s", name, shortTitle,
                Integer.toString(dateUpdate.getDate()) + "." + Integer.toString(dateUpdate.getMonth()) + "." +
                Integer.toString(dateUpdate.getYear()), adress, Integer.toString(dateFoundation.getDate()) + "." +
                        Integer.toString(dateFoundation.getMonth()) + "." + Integer.toString(dateFoundation.getYear()),
                countEmployees, auditor, phone, email, branch, activity, link);
    }
}

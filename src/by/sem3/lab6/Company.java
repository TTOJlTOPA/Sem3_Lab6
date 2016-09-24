package by.sem3.lab6;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Company {
    String name;
    String shortTitle;
    Date dateUpdate;
    String adress;
    Date dateFoundation;
    int countEmployees;
    String auditor;
    String phone;
    String email;
    String branch;
    String activity;
    String link;

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
        dateParam = date.split("[:]");
        return new Date(Integer.parseInt(dateParam[2]), Integer.parseInt(dateParam[1]), Integer.parseInt(dateParam[0]));
    }
}

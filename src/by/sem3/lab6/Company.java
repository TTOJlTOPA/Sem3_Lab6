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

    //Company(String[] param) {}

    private boolean isName(String name) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}

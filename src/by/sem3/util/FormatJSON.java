package by.sem3.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatJSON extends Format {
    public FormatJSON() {
        super();
    }

    public void append(FormatJSON json) throws IncorrectFormatException {
        super.append(json.getFormat());
    }

    @Override
    public void append(String str) throws IncorrectFormatException {
        if (isCorrect(str)) {
            super.append(str);
        } else {
            throw new IncorrectFormatException();
        }
    }

    public void appendWithNameOfField(String field, String str) throws IncorrectFormatException {
        if (isFieldCorrect(field) && isFieldValueCorrect(str)) {
            super.append("\"");
            super.append(field);
            super.append("\": ");
            try {
                Double.parseDouble(str);
                super.append(str);
            } catch (NumberFormatException e) {
                if (str.equals("true") || str.equals("false")) {
                    super.append(str);
                }
                super.append("\"");
                super.append(str);
                super.append("\"");
            }
        } else {
            throw new IncorrectFormatException();
        }
    }

    @Override
    public boolean isCorrect(String str) {
        String[] lines = str.split("\\n");
        Pattern pattern = Pattern.compile("(\\s*((\\{|\\})|(\\t+[\\[\\]])" +
                "|(\"\\w+\": (\\d+|\\[|(\"[№\\w\\p{Punct}&&[^\\\\\"]]+\")|(true)|(false)))),?)|\\t+|,");
        Matcher matcher;
        for (String item : lines) {
            if (!item.isEmpty()) {
                matcher = pattern.matcher(item);
                if (!matcher.matches()) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isFieldCorrect(String field) {
        Pattern pattern = Pattern.compile("\\w+");
        return pattern.matcher(field).matches();
    }

    private boolean isFieldValueCorrect(String fieldValue) {
        Pattern pattern = Pattern.compile("(\\d+|\\[|([№\\w\\p{Punct}&&[^\\\\\"]]+)|(true)|(false))");
        return pattern.matcher(fieldValue).matches();
    }
}

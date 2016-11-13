package by.sem3.lab6_7;

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
}

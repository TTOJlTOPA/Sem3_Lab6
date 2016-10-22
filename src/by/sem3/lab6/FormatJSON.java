package by.sem3.lab6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatJSON extends Format {
    public FormatJSON() {
        super();
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
        Pattern pattern = Pattern.compile("(\\s*((\\{|\\})" +
                "|(\"\\w+\": (\\d+|[\\[\\]]|(\"[№\\w\\p{Punct}&&[^\\\\\"]]+\")|(true)|(false)),?)))");
        Matcher matcher;
        for (String item : lines) {
            matcher = pattern.matcher(item);
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getFormat() {
        return super.getFormat();
    }
}

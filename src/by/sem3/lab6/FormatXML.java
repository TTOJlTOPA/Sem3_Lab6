package by.sem3.lab6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatXML extends Format {
    public FormatXML() {
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
        Pattern pattern = Pattern.compile("(<\\?xml version=\"1[.][01]\"" +
                "( encoding=\"(UTF-(8|16)|windows-1251)\")?( standalone=\"(yes|no)\")?\\?>)" +
                "|(\\s*((<\\w+>)|([№\\w\\p{Punct}&&[^\\\\\"&<>]]+[ \t]*[№\\w\\p{Punct}&&[^\\\\\"&<>]]*)" +
                "|(</\\w+>))+)");
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

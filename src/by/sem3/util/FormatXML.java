package by.sem3.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatXML extends Format {
    public FormatXML() {
        super();
    }

    public void append(FormatXML xml) throws IncorrectFormatException {
        super.append(xml.getFormat());
    }

    @Override
    public void append(String str) throws IncorrectFormatException {
        if (isCorrect(str)) {
            super.append(str);
        } else {
            throw new IncorrectFormatException();
        }
    }

    public void appendWithTag(String tag, String str) throws IncorrectFormatException {
        if (isCorrect(str) && isTagCorrect(tag)) {
            super.append("<");
            super.append(tag);
            super.append(">");
            super.append(str);
            super.append("</");
            super.append(tag);
            super.append(">");
            super.append("\n");
        } else {
            throw new IncorrectFormatException();
        }
    }

    @Override
    public boolean isCorrect(String str) {
        String[] lines = str.split("\\n");
        Pattern pattern = Pattern.compile("(<\\?xml version=\"1[.][01]\"" +
                "( encoding=\"(UTF-(8|16)|windows-1251)\")?( standalone=\"(yes|no)\")?\\?>)" +
                "|(\\s*((</?\\w+>)|([№\\w\\p{Punct}&&[^\\\\\"&<>]]+[ \t]*[№\\w\\p{Punct}&&[^\\\\\"&<>]]*))+)|\\t+");
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

    private boolean isTagCorrect(String tag) {
        Pattern pattern = Pattern.compile("\\w+");
        return pattern.matcher(tag).matches();
    }
}

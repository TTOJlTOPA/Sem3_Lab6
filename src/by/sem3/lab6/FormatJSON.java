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
        Pattern pattern = Pattern.compile("");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    @Override
    public String getFormat() {
        return super.getFormat();
    }
}

package by.sem3.util;

public abstract class Format {
    final StringBuilder format;

    protected Format() {
        this.format = new StringBuilder();
    }

    protected void append(String str) throws IncorrectFormatException {
        format.append(str);
    }

    protected abstract boolean isCorrect(String str);

    protected String getFormat() {
        return format.toString();
    }
}

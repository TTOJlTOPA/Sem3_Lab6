package by.sem3.lab6;

public abstract class Format {
    StringBuilder format;

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

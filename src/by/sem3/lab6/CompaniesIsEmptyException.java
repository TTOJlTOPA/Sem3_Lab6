package by.sem3.lab6;

public class CompaniesIsEmptyException extends Exception {
    public CompaniesIsEmptyException() {
        super("List of companies is empty!");
    }
}

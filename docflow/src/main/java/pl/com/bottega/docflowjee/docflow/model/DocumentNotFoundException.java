package pl.com.bottega.docflowjee.docflow.model;

public class DocumentNotFoundException extends RuntimeException {
    private String number;

    public DocumentNotFoundException(String number) {
        super("document with number " + number + " not found");
        this.number = number;
    }
}

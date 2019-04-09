package pl.com.bottega.docflowjee.docflow.application;

public class DocumentNumberOccupied extends RuntimeException {

    private String number;

    public DocumentNumberOccupied(String number) {
        super("document number " + number + " is occupied");
        this.number = number;
    }

}

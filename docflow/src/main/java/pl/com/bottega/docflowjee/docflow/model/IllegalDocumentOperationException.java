package pl.com.bottega.docflowjee.docflow.model;

public class IllegalDocumentOperationException extends RuntimeException {

    public IllegalDocumentOperationException(String message) {
        super(message);
    }
}

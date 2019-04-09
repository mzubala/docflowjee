package pl.com.bottega.docflowjee.docflow.application;

public class IllegalDocumentOperationException extends RuntimeException {

    public IllegalDocumentOperationException(String message) {
        super(message);
    }
}

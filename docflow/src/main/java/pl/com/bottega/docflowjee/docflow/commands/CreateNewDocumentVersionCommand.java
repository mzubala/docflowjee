package pl.com.bottega.docflowjee.docflow.commands;

import java.util.UUID;

public class CreateNewDocumentVersionCommand {
    public UUID documentId;
    public Long employeeId;

    public CreateNewDocumentVersionCommand(UUID documentId, Long employeeId) {
        this.documentId = documentId;
        this.employeeId = employeeId;
    }
}

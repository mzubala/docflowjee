package pl.com.bottega.docflowjee.docflow.commands;

import java.util.UUID;

public class PassToVerificationCommand {
    public UUID documentId;
    public Long employeeId;
    public Long aggregateVersion;

    public PassToVerificationCommand(UUID documentId, Long employeeId) {
        this.documentId = documentId;
        this.employeeId = employeeId;
    }

    public PassToVerificationCommand() {
    }
}

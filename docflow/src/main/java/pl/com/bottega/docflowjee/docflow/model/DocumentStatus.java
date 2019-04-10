package pl.com.bottega.docflowjee.docflow.model;

import java.util.Arrays;
import java.util.List;

import static pl.com.bottega.docflowjee.docflow.model.DocumentOperation.ACCEPT;
import static pl.com.bottega.docflowjee.docflow.model.DocumentOperation.ARCHIVE;
import static pl.com.bottega.docflowjee.docflow.model.DocumentOperation.CREATE_NEW_VERSION;
import static pl.com.bottega.docflowjee.docflow.model.DocumentOperation.PUBLISH;
import static pl.com.bottega.docflowjee.docflow.model.DocumentOperation.REJECT;
import static pl.com.bottega.docflowjee.docflow.model.DocumentOperation.PASS_TO_VERIFICATION;
import static pl.com.bottega.docflowjee.docflow.model.DocumentOperation.UPDATE;

public enum DocumentStatus {

    DRAFT(UPDATE, PASS_TO_VERIFICATION, ARCHIVE),
    WAITING_VERIFICATION(PASS_TO_VERIFICATION, UPDATE, ACCEPT, REJECT, ARCHIVE),
    VERIFIED(ACCEPT, UPDATE, PUBLISH, ARCHIVE),
    PUBLISHED(PUBLISH, CREATE_NEW_VERSION, ARCHIVE),
    ARCHIVED(ARCHIVE, CREATE_NEW_VERSION);

    private List<DocumentOperation> allowedOperations;

    DocumentStatus(DocumentOperation... allowedOperations) {
        this.allowedOperations = Arrays.asList(allowedOperations);
    }

    public void checkOperationPermitted(DocumentOperation operation) {
        if(!allowedOperations.contains(operation)) {
            throw new IllegalDocumentOperationException(
                String.format("operation %s is not permitted in %s status", operation.name(), name())
            );
        }
    }

}

enum DocumentOperation {
    UPDATE,
    PASS_TO_VERIFICATION,
    ACCEPT,
    REJECT,
    PUBLISH,
    ARCHIVE,
    CREATE_NEW_VERSION
}

package pl.com.bottega.docflowjee.catalog.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class DocumentDetails {

    private UUID documentId;

    private DocumentVersion currentVersion;

    private List<DocumentVersion> previousVersions = new LinkedList<>();

    public UUID getDocumentId() {
        return documentId;
    }

    public void setDocumentId(UUID documentId) {
        this.documentId = documentId;
    }

    public DocumentVersion getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(DocumentVersion currentVersion) {
        this.currentVersion = currentVersion;
    }

    public List<DocumentVersion> getPreviousVersions() {
        return previousVersions;
    }

    public void setPreviousVersions(List<DocumentVersion> previousVersions) {
        this.previousVersions = previousVersions;
    }
}

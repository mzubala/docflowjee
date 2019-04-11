package pl.com.bottega.docflowjee.catalog.service;

import pl.com.bottega.docflowjee.catalog.model.BasicDocumentInfo;
import pl.com.bottega.docflowjee.catalog.model.DocumentDetails;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class DocumentFinder {

    @Inject
    private EntityManager entityManager;

    public DocumentDetails findDocumendDetails(String documentId) {
        return entityManager.find(DocumentDetails.class, documentId);
    }

    public DocumentSearchResults search(DocumentQuery query) {

        return new DocumentSearchResults();
    }

}

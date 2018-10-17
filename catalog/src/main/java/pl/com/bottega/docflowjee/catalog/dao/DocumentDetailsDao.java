package pl.com.bottega.docflowjee.catalog.dao;

import pl.com.bottega.docflowjee.catalog.model.BasicDocumentInfo;
import pl.com.bottega.docflowjee.catalog.model.DocumentDetails;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.print.Doc;
import java.util.UUID;

public class DocumentDetailsDao {

    @Inject
    private EntityManager entityManager;

    public void save(DocumentDetails details) {
        entityManager.merge(details);
    }

    public DocumentDetails find(UUID aggregateId) {
        return entityManager.find(DocumentDetails.class, aggregateId);
    }
}

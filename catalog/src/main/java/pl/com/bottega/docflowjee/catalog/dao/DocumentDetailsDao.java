package pl.com.bottega.docflowjee.catalog.dao;

import pl.com.bottega.docflowjee.catalog.model.DocumentDetails;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class DocumentDetailsDao {

    @Inject
    private EntityManager entityManager;

    public void save(DocumentDetails details) {
        entityManager.merge(details);
    }
}

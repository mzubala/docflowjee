package pl.com.bottega.docflowjee.catalog.dao;

import pl.com.bottega.docflowjee.catalog.model.BasicDocumentInfo;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.UUID;

public class BasicDocumentInfoDao {

    @Inject
    private EntityManager entityManager;

    public void save(BasicDocumentInfo basicDocumentInfo) {
        entityManager.merge(basicDocumentInfo);
    }

    public BasicDocumentInfo find(UUID aggregateId) {
        return entityManager.find(BasicDocumentInfo.class, aggregateId);
    }
}

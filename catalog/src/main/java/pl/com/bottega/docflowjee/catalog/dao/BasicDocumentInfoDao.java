package pl.com.bottega.docflowjee.catalog.dao;

import pl.com.bottega.docflowjee.catalog.model.BasicDocumentInfo;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class BasicDocumentInfoDao {

    @Inject
    private EntityManager entityManager;

    public void save(BasicDocumentInfo basicDocumentInfo) {
        entityManager.merge(basicDocumentInfo);
    }
}

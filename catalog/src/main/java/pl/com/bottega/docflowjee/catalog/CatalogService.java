package pl.com.bottega.docflowjee.catalog;

import pl.com.bottega.docflowjee.catalog.dao.BasicDocumentInfoDao;
import pl.com.bottega.docflowjee.catalog.dao.DocumentDetailsDao;
import pl.com.bottega.docflowjee.catalog.model.BasicDocumentInfo;
import pl.com.bottega.docflowjee.catalog.model.DocumentDetails;
import pl.com.bottega.docflowjee.catalog.model.DocumentStatus;
import pl.com.bottega.docflowjee.catalog.model.DocumentVersion;
import pl.com.bottega.docflowjee.docflow.events.DocumentArchivedEvent;
import pl.com.bottega.docflowjee.docflow.events.DocumentCreatedEvent;
import pl.com.bottega.docflowjee.docflow.events.DocumentPassedToVerification;
import pl.com.bottega.docflowjee.docflow.events.DocumentPublishedEvent;
import pl.com.bottega.docflowjee.docflow.events.DocumentRejectedEvent;
import pl.com.bottega.docflowjee.docflow.events.DocumentUpdatedEvent;
import pl.com.bottega.docflowjee.docflow.events.DocumentVerifiedEvent;

import javax.inject.Inject;
import javax.transaction.Transactional;

public class CatalogService {

    @Inject
    private BasicDocumentInfoDao basicDocumentInfoDao;

    @Inject
    private DocumentDetailsDao documentDetailsDao;

    @Transactional
    public void process(DocumentCreatedEvent event) {
        BasicDocumentInfo basicDocumentInfo = new BasicDocumentInfo();
        basicDocumentInfo.setDocumentId(event.getAggregateId());
        basicDocumentInfo.setStatus(DocumentStatus.DRAFT);

        DocumentDetails details = new DocumentDetails();
        DocumentVersion version = new DocumentVersion();
        version.setNumber(1);
        version.setStatus(DocumentStatus.DRAFT);
        details.setCurrentVersion(version);
        details.setDocumentId(event.getAggregateId());

        basicDocumentInfoDao.save(basicDocumentInfo);
        documentDetailsDao.save(details);
    }

    public void process(DocumentUpdatedEvent event) {

    }

    public void process(DocumentPassedToVerification event) {

    }

    public void process(DocumentVerifiedEvent event) {

    }

    public void process(DocumentRejectedEvent event) {

    }

    public void process(DocumentPublishedEvent event) {

    }

    public void process(DocumentArchivedEvent event) {

    }

}

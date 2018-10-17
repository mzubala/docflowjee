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
import pl.com.bottega.docflowjee.docflow.events.NewDocumentVersionCreatedEvent;

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

        basicDocumentInfo.setAggregateVersion(event.getAggregateVersion());
        details.setAggregateVersion(event.getAggregateVersion());
        basicDocumentInfoDao.save(basicDocumentInfo);
        documentDetailsDao.save(details);
    }

    @Transactional
    public void process(DocumentUpdatedEvent event) {
        BasicDocumentInfo basicDocumentInfo = basicDocumentInfoDao.find(event.getAggregateId());
        basicDocumentInfo.setStatus(DocumentStatus.DRAFT);
        basicDocumentInfo.setTitle(event.getTitle());
        basicDocumentInfo.setContentBrief(contentBrief(event.getContent()));

        DocumentDetails documentDetails = documentDetailsDao.find(event.getAggregateId());
        DocumentVersion currentVersion = documentDetails.getCurrentVersion();
        currentVersion.setStatus(DocumentStatus.DRAFT);
        currentVersion.setContent(event.getContent());
        currentVersion.setTitle(event.getTitle());

        basicDocumentInfo.setAggregateVersion(event.getAggregateVersion());
        documentDetails.setAggregateVersion(event.getAggregateVersion());
    }

    private String contentBrief(String content) {
        if(content != null) {
            return content.substring(0, 250);
        } else {
            return null;
        }
    }

    @Transactional
    public void process(DocumentPassedToVerification event) {
        BasicDocumentInfo basicDocumentInfo = basicDocumentInfoDao.find(event.getAggregateId());
        basicDocumentInfo.setStatus(DocumentStatus.WAITING_VERIFICATION);

        DocumentDetails documentDetails = documentDetailsDao.find(event.getAggregateId());
        DocumentVersion currentVersion = documentDetails.getCurrentVersion();
        currentVersion.setStatus(DocumentStatus.WAITING_VERIFICATION);

        basicDocumentInfo.setAggregateVersion(event.getAggregateVersion());
        documentDetails.setAggregateVersion(event.getAggregateVersion());
    }

    @Transactional
    public void process(DocumentVerifiedEvent event) {
        BasicDocumentInfo basicDocumentInfo = basicDocumentInfoDao.find(event.getAggregateId());
        basicDocumentInfo.setStatus(DocumentStatus.VERIFIED);

        DocumentDetails documentDetails = documentDetailsDao.find(event.getAggregateId());
        DocumentVersion currentVersion = documentDetails.getCurrentVersion();
        currentVersion.setStatus(DocumentStatus.VERIFIED);

        basicDocumentInfo.setAggregateVersion(event.getAggregateVersion());
        documentDetails.setAggregateVersion(event.getAggregateVersion());
    }

    @Transactional
    public void process(DocumentRejectedEvent event) {
        BasicDocumentInfo basicDocumentInfo = basicDocumentInfoDao.find(event.getAggregateId());
        basicDocumentInfo.setStatus(DocumentStatus.DRAFT);

        DocumentDetails documentDetails = documentDetailsDao.find(event.getAggregateId());
        DocumentVersion currentVersion = documentDetails.getCurrentVersion();
        currentVersion.setStatus(DocumentStatus.DRAFT);
        currentVersion.setRejectionReason(event.getReason());

        basicDocumentInfo.setAggregateVersion(event.getAggregateVersion());
        documentDetails.setAggregateVersion(event.getAggregateVersion());
    }

    @Transactional
    public void process(DocumentPublishedEvent event) {
        BasicDocumentInfo basicDocumentInfo = basicDocumentInfoDao.find(event.getAggregateId());
        basicDocumentInfo.setStatus(DocumentStatus.PUBLISHED);

        DocumentDetails documentDetails = documentDetailsDao.find(event.getAggregateId());
        DocumentVersion currentVersion = documentDetails.getCurrentVersion();
        currentVersion.setStatus(DocumentStatus.PUBLISHED);
        currentVersion.getPublishedFor().addAll(event.getDepartmentIds());

        basicDocumentInfo.setAggregateVersion(event.getAggregateVersion());
        documentDetails.setAggregateVersion(event.getAggregateVersion());
    }

    @Transactional
    public void process(DocumentArchivedEvent event) {
        BasicDocumentInfo basicDocumentInfo = basicDocumentInfoDao.find(event.getAggregateId());
        basicDocumentInfo.setStatus(DocumentStatus.ARCHIVED);

        DocumentDetails documentDetails = documentDetailsDao.find(event.getAggregateId());
        DocumentVersion currentVersion = documentDetails.getCurrentVersion();
        currentVersion.setStatus(DocumentStatus.ARCHIVED);

        basicDocumentInfo.setAggregateVersion(event.getAggregateVersion());
        documentDetails.setAggregateVersion(event.getAggregateVersion());
    }

    @Transactional
    public void process(NewDocumentVersionCreatedEvent event) {
        BasicDocumentInfo basicDocumentInfo = basicDocumentInfoDao.find(event.getAggregateId());
        basicDocumentInfo.setStatus(DocumentStatus.DRAFT);

        DocumentDetails documentDetails = documentDetailsDao.find(event.getAggregateId());
        DocumentVersion currentVersion = new DocumentVersion();
        currentVersion.setNumber(event.getVersion());
        currentVersion.setTitle(documentDetails.getCurrentVersion().getTitle());
        currentVersion.setContent(documentDetails.getCurrentVersion().getContent());
        currentVersion.setStatus(DocumentStatus.DRAFT);
        documentDetails.getPreviousVersions().add(documentDetails.getCurrentVersion());
        documentDetails.setCurrentVersion(currentVersion);

        basicDocumentInfo.setAggregateVersion(event.getAggregateVersion());
        documentDetails.setAggregateVersion(event.getAggregateVersion());
    }

}

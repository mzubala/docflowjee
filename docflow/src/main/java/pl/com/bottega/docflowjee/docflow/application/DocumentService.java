package pl.com.bottega.docflowjee.docflow.application;

import pl.com.bottega.docflowjee.docflow.application.commands.DocumentCommand;
import pl.com.bottega.docflowjee.docflow.application.commands.UpdateDocumentCommand;
import pl.com.bottega.docflowjee.docflow.model.Document;
import pl.com.bottega.docflowjee.docflow.model.DocumentDao;
import pl.com.bottega.docflowjee.docflow.model.DocumentStatus;

import javax.inject.Inject;
import javax.transaction.Transactional;

public class DocumentService {

    @Inject
    private DocumentDao documentDao;

    @Transactional
    public void create(DocumentCommand command) {
        if (documentDao.isNumberOccupied(command.number)) {
            throw new DocumentNumberOccupied(command.number);
        }
        Document document = new Document();
        document.number = command.number;
        document.status = DocumentStatus.DRAFT;
        document.creatingEmployee = command.employeeId;
        documentDao.save(document);
    }

    @Transactional
    public void update(UpdateDocumentCommand command) {
        Document document = documentDao.find(command.number);
        if (document.status != DocumentStatus.DRAFT &&
            document.status != DocumentStatus.WAITING_VERIFICATION &&
            document.status != DocumentStatus.VERIFIED
        ) {
            throw new IlleDocumentOperationException("document can't be updated in status " + document.status);
        }
        documentDao.save(document);
        document.status = DocumentStatus.DRAFT;
        document.title = command.title;
        document.content = command.content;
    }

}

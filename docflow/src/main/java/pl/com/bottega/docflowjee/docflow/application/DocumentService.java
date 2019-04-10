package pl.com.bottega.docflowjee.docflow.application;

import pl.com.bottega.docflowjee.docflow.model.commands.DocumentCommand;
import pl.com.bottega.docflowjee.docflow.model.commands.UpdateDocumentCommand;
import pl.com.bottega.docflowjee.docflow.application.validation.ValidateCommand;
import pl.com.bottega.docflowjee.docflow.model.Document;
import pl.com.bottega.docflowjee.docflow.model.DocumentDao;

import javax.inject.Inject;
import javax.transaction.Transactional;

public class DocumentService {

    @Inject
    private DocumentDao documentDao;

    @Transactional
    @ValidateCommand
    public void create(DocumentCommand command) {
        if (documentDao.isNumberOccupied(command.number)) {
            throw new DocumentNumberOccupied(command.number);
        }
        Document document = new Document(command);
        documentDao.save(document);
    }

    @Transactional
    @ValidateCommand
    public void update(UpdateDocumentCommand command) {
        Document document = documentDao.find(command.number);
        document.update(command);
    }

}

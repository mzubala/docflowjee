package pl.com.bottega.docflowjee.docflow;

import pl.com.bottega.docflowjee.docflow.commands.ArchiveDocumentCommand;
import pl.com.bottega.docflowjee.docflow.commands.CreateDocumentCommand;
import pl.com.bottega.docflowjee.docflow.commands.CreateNewDocumentVersionCommand;
import pl.com.bottega.docflowjee.docflow.commands.UpdateDocumentCommand;

import javax.inject.Inject;
import java.time.Clock;

public class DocumentPreparation {
    private final DocumentRepository documentRepository;
    private Clock clock;

    @Inject
    public DocumentPreparation(DocumentRepository documentRepository, Clock clock) {
        this.documentRepository = documentRepository;
        this.clock = clock;
    }

    @ValidateCommand
    public void create(CreateDocumentCommand cmd) {
        Document document = new Document(cmd, clock);
        documentRepository.save(document, -1L);
    }

    public void update(UpdateDocumentCommand cmd) {
        Document document = documentRepository.get(cmd.documentId);
        document.update(cmd);
        documentRepository.save(document, cmd.aggregateVersion);
    }

    public void createNewVersion(CreateNewDocumentVersionCommand cmd) {
        Document document = documentRepository.get(cmd.documentId);
        document.createNewVersion(cmd);
        documentRepository.save(document, cmd.aggregateVersion);
    }

    public void archive(ArchiveDocumentCommand cmd) {
        Document document = documentRepository.get(cmd.documentId);
        document.archive(cmd);
        documentRepository.save(document, cmd.aggregateVersion);
    }
}
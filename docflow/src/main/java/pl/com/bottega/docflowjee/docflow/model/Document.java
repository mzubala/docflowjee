package pl.com.bottega.docflowjee.docflow.model;

import pl.com.bottega.docflowjee.docflow.model.commands.DocumentCommand;
import pl.com.bottega.docflowjee.docflow.model.commands.PublishDocumentCommand;
import pl.com.bottega.docflowjee.docflow.model.commands.RejectDocumentCommand;
import pl.com.bottega.docflowjee.docflow.model.commands.UpdateDocumentCommand;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import static pl.com.bottega.docflowjee.docflow.model.DocumentOperation.ACCEPT;
import static pl.com.bottega.docflowjee.docflow.model.DocumentOperation.ARCHIVE;
import static pl.com.bottega.docflowjee.docflow.model.DocumentOperation.PASS_TO_VERIFICATION;
import static pl.com.bottega.docflowjee.docflow.model.DocumentOperation.PUBLISH;
import static pl.com.bottega.docflowjee.docflow.model.DocumentOperation.REJECT;
import static pl.com.bottega.docflowjee.docflow.model.DocumentOperation.UPDATE;

@Entity
@Table(
    indexes = {
        @Index(columnList = "number", unique = true)
    }
)
@NamedQuery(
    name = "Document.findByNumber",
    query = "FROM Document d WHERE d.number = :number"
)
@NamedQuery(
    name = "Document.countByNumber",
    query = "SELECT count(d) FROM Document d WHERE d.number = :number"
)
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number, title, content;

    @Enumerated(value = EnumType.ORDINAL)
    private DocumentStatus status;

    private Long creatingEmployee;

    public Document(DocumentCommand command) {
        number = command.number;
        status = DocumentStatus.DRAFT;
        creatingEmployee = command.employeeId;
    }

    public void update(UpdateDocumentCommand command) {
        status.checkOperationPermitted(UPDATE);
        if(command.title.equals(title) && command.content.equals(content)) {
            return;
        }
        status = DocumentStatus.DRAFT;
        title = command.title;
        content = command.content;

    }

    public void passToVerify(DocumentCommand cmd) {
        status.checkOperationPermitted(PASS_TO_VERIFICATION);
        status = DocumentStatus.WAITING_VERIFICATION;
    }

    public void accept(DocumentCommand cmd) {
        status.checkOperationPermitted(ACCEPT);
        status = DocumentStatus.VERIFIED;
    }

    public void reject(RejectDocumentCommand cmd) {
        status.checkOperationPermitted(REJECT);
    }

    public void publish(PublishDocumentCommand cmd) {
        status.checkOperationPermitted(PUBLISH);
    }

    public void archive(DocumentCommand cmd) {
        status.checkOperationPermitted(ARCHIVE);
    }

    public DocumentStatus status() {
        return status;
    }
}

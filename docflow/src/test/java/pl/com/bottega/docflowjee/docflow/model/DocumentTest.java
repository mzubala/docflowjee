package pl.com.bottega.docflowjee.docflow.model;

import org.junit.Test;
import pl.com.bottega.docflowjee.docflow.model.commands.DocumentCommand;
import pl.com.bottega.docflowjee.docflow.model.commands.PublishDocumentCommand;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static pl.com.bottega.docflowjee.docflow.model.DocumentStatus.DRAFT;

public class DocumentTest {

    private Long anyEmployeeId = 1L;
    private String anyNumber = "123";

    @Test
    public void newDocumentIsInDraftStatus() {
        // when
        DocumentCommand cmd = new DocumentCommand();
        cmd.employeeId = anyEmployeeId;
        cmd.number = anyNumber;
        Document document = new Document(cmd);

        // then
        assertThat(document.status()).isEqualTo(DRAFT);
    }

    @Test
    public void cantPublishDraftDocuments() {
        // given
        DocumentCommand cmd = new DocumentCommand();
        cmd.employeeId = anyEmployeeId;
        cmd.number = anyNumber;
        Document document = new Document(cmd);

        // then
        PublishDocumentCommand publishCmd = new PublishDocumentCommand();
        assertThatThrownBy(() -> document.publish(publishCmd))
            .isInstanceOf(IllegalDocumentOperationException.class);

    }

}

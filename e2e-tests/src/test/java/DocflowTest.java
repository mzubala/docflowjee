import org.junit.BeforeClass;
import org.junit.Test;
import pl.com.bottega.docflowjee.catalog.model.DocumentDetails;
import pl.com.bottega.docflowjee.docflow.commands.CreateDocumentCommand;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class DocflowTest {

    private static CatalogAPI catalogAPI;
    private static DocflowAPI docflowAPI;


    @BeforeClass
    public static void setup() {
        catalogAPI = APIFactory.catalogAPI();
        docflowAPI = APIFactory.docflowAPI();
    }

    @Test
    public void createsDocument() {
        // given
        Long employeeId = 1L;
        UUID documentId = UUID.randomUUID();

        // when
        docflowAPI.create(new CreateDocumentCommand(documentId, employeeId));

        // then
        DocumentDetails details = catalogAPI.get(documentId.toString());
        assertThat(details).isNotNull();
        assertThat(details.getDocumentId()).isEqualTo(documentId);
        assertThat(details.getAggregateVersion()).isEqualTo(0);
        assertThat(details.getCurrentVersion().getDocumentVersionNumber()).isEqualTo(1);
        assertThat(details.getPreviousVersions()).isEmpty();
        assertThat(details.getCurrentVersion()).isNotNull();
    }

}

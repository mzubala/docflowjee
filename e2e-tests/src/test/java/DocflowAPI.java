import feign.Headers;
import feign.RequestLine;
import pl.com.bottega.docflowjee.docflow.commands.CreateDocumentCommand;

@Headers({"Accept: application/json", "Content-Type: application/json"})
public interface DocflowAPI {

    @RequestLine("PUT /documents")
    void create(CreateDocumentCommand cmd);

}

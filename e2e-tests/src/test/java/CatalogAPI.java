import feign.Headers;
import feign.Param;
import feign.RequestLine;
import pl.com.bottega.docflowjee.catalog.model.DocumentDetails;

import java.util.UUID;

@Headers({"Accept: application/json"})
public interface CatalogAPI {

    @RequestLine("GET /documents/{id}")
    DocumentDetails get(@Param("id") String id);

}

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public class APIFactory {

    private static final String SERVER_URL = "http://localhost:8080";
    private static final String DOCFLOW_URL = SERVER_URL + "/docflow/rest";
    private static final String CATALOG_URL = SERVER_URL + "/catalog/rest";

    static DocflowAPI docflowAPI() {
        return Feign.builder()
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .target(DocflowAPI.class, DOCFLOW_URL);
    }

    static CatalogAPI catalogAPI() {
        return Feign.builder()
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .target(CatalogAPI.class, CATALOG_URL);
    }
}

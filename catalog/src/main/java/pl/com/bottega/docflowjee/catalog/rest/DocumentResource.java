package pl.com.bottega.docflowjee.catalog.rest;

import pl.com.bottega.docflowjee.catalog.model.DocumentStatus;
import pl.com.bottega.docflowjee.catalog.service.SortOrder;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/documents")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DocumentResource {

    public Response search(DocumentStatus status,
                           String phrase,
                           String sortBy,
                           SortOrder sortOrder,
                           Integer page,
                           Integer perPage) {
        return null;
    }

    public Response get(String documentId) {
        return null;
    }

}

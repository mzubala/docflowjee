package pl.com.bottega.docflowjee.catalog.rest;

import pl.com.bottega.docflowjee.catalog.dao.SortOrder;
import pl.com.bottega.docflowjee.catalog.model.DocumentStatus;
import pl.com.bottega.docflowjee.catalog.service.DocumentFinder;
import pl.com.bottega.docflowjee.catalog.service.DocumentQuery;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/documents")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DocumentResource {

    @Inject
    private DocumentFinder finder;

    @GET
    @Path("/search")
    public Response search(@QueryParam("status") DocumentStatus status,
                           String phrase,
                           String sortBy,
                           SortOrder sortOrder,
                           Integer page,
                           Integer perPage) {
        DocumentQuery query = new DocumentQuery();
        query.sortBy = sortBy;
        query.status = status;
        return Response.ok()
            .entity(finder.search(query)).build();
    }

    @GET
    @Path("/{documentId}")
    public Response get(@PathParam("documentId") String documentId) {
        return Response.ok()
            .entity(finder.findDocumendDetails(documentId)).build();
    }

}

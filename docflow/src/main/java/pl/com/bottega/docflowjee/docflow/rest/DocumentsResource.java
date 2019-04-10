package pl.com.bottega.docflowjee.docflow.rest;


import pl.com.bottega.docflowjee.docflow.application.DocumentService;
import pl.com.bottega.docflowjee.docflow.model.commands.DocumentCommand;
import pl.com.bottega.docflowjee.docflow.model.commands.UpdateDocumentCommand;
import pl.com.bottega.docflowjee.docflow.rest.dto.DocumentRequest;
import pl.com.bottega.docflowjee.docflow.rest.dto.PublishDocumentRequest;
import pl.com.bottega.docflowjee.docflow.rest.dto.RejectDocumentRequest;
import pl.com.bottega.docflowjee.docflow.rest.dto.UpdateDocumentRequest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/documents")
public class DocumentsResource {

    @Inject
    private DocumentService documentService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(DocumentRequest request) {
        DocumentCommand documentCommand = new DocumentCommand();
        documentCommand.number = request.number;
        documentCommand.employeeId = 1L;
        documentService.create(documentCommand);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(UpdateDocumentRequest request) {
        UpdateDocumentCommand updateDocumentCommand = new UpdateDocumentCommand();
        updateDocumentCommand.content = request.content;
        updateDocumentCommand.title = request.title;
        updateDocumentCommand.employeeId = 1L;
        updateDocumentCommand.number = request.number;
        documentService.update(updateDocumentCommand);
        return Response.ok().build();
    }

    @PUT
    @Path("/verification")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response verify(DocumentRequest document) {
        return Response.ok().build();
    }

    @PUT
    @Path("/acceptance")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response accept(DocumentRequest document) {
        return Response.ok().build();
    }

    @PUT
    @Path("/rejection")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response reject(RejectDocumentRequest document) {
        return Response.ok().build();
    }

    @PUT
    @Path("/publication")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response publish(PublishDocumentRequest document) {
        return Response.ok().build();
    }

    @PUT
    @Path("/archivisation")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response archive(DocumentRequest document) {
        return Response.ok().build();
    }

    @PUT
    @Path("/new-version")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewVersion(DocumentRequest document) {
        return Response.ok().build();
    }
}

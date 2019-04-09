package pl.com.bottega.docflowjee.docflow.rest;

import pl.com.bottega.docflowjee.docflow.application.DocumentNumberOccupied;
import pl.com.bottega.docflowjee.docflow.application.IllegalDocumentOperationException;
import pl.com.bottega.docflowjee.docflow.model.DocumentNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

public class ExceptionMappers {

    @Provider
    public static class DocumentNotFoundExceptionMapper implements ExceptionMapper<DocumentNotFoundException> {
        @Override
        public Response toResponse(DocumentNotFoundException exception) {
            RequestErrors requestErrors = new RequestErrors().addError("number", exception.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(requestErrors).build();
        }
    }

    @Provider
    public static class DocumentNumberOccupiedMapper implements ExceptionMapper<DocumentNumberOccupied> {
        @Override
        public Response toResponse(DocumentNumberOccupied exception) {
            RequestErrors requestErrors = new RequestErrors().addError("number", exception.getMessage());
            return Response.status(Response.Status.CONFLICT).entity(requestErrors).build();
        }
    }

    @Provider
    public static class IllegalDocumentOperationMapper implements ExceptionMapper<IllegalDocumentOperationException> {
        @Override
        public Response toResponse(IllegalDocumentOperationException exception) {
            RequestErrors requestErrors = new RequestErrors().addError("status", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(requestErrors).build();
        }
    }
}

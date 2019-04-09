package pl.com.bottega.docflowjee.docflow.rest;

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

}

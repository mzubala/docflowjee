package pl.com.bottega.docflowjee.docflow.rest.dto;

import javax.validation.constraints.NotEmpty;

public class UpdateDocumentRequest extends DocumentRequest {

    @NotEmpty
    public String title;

    @NotEmpty
    public String content;
}

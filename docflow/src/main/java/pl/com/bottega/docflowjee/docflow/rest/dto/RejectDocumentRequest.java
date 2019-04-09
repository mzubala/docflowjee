package pl.com.bottega.docflowjee.docflow.rest.dto;

import javax.validation.constraints.NotEmpty;

public class RejectDocumentRequest extends DocumentRequest {
    @NotEmpty
    public String reason;
}

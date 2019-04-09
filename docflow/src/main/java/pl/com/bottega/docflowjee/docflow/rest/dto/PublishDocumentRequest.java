package pl.com.bottega.docflowjee.docflow.rest.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class PublishDocumentRequest extends DocumentRequest {
    @NotNull
    public List<Integer> departmentIds;
}

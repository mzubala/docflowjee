package pl.com.bottega.docflowjee.docflow.model.commands;

import javax.validation.constraints.NotNull;

public class DocumentCommand {

    @NotNull
    public String number;

    @NotNull
    public Long employeeId;

}

package pl.com.bottega.docflowjee.docflow.application.commands;

import javax.validation.constraints.NotNull;

public class DocumentCommand {

    @NotNull
    public String number;

    @NotNull
    public Long employeeId;

}

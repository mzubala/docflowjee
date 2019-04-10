package pl.com.bottega.docflowjee.docflow.model.commands;

import javax.validation.constraints.NotNull;

public class UpdateDocumentCommand extends DocumentCommand {

    @NotNull
    public String title;

    @NotNull
    public String content;

}

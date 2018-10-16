package pl.com.bottega.docflowjee.catalog.model;

import java.util.HashSet;
import java.util.Set;

public class DocumentVersion {

    private Integer number;

    private String title;

    private String content;

    private DocumentStatus status;

    private Set<Long> publishedFor = new HashSet<>();

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DocumentStatus getStatus() {
        return status;
    }

    public void setStatus(DocumentStatus status) {
        this.status = status;
    }
}

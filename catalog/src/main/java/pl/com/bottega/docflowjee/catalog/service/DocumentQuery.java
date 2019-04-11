package pl.com.bottega.docflowjee.catalog.service;

import pl.com.bottega.docflowjee.catalog.dao.SortOrder;
import pl.com.bottega.docflowjee.catalog.model.DocumentStatus;

public class DocumentQuery {

    public DocumentStatus status;
    public String phrase;
    public String sortBy;
    public SortOrder sortOrder;
    public Integer page;
    public Integer perPage;

}

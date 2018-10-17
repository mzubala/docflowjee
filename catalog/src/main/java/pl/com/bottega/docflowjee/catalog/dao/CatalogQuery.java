package pl.com.bottega.docflowjee.catalog.dao;

import pl.com.bottega.docflowjee.catalog.model.DocumentStatus;
import pl.com.bottega.docflowjee.catalog.service.SortOrder;

public class CatalogQuery {

    public DocumentStatus status;

    public String phrase;

    public String sortBy;

    public SortOrder sortOrder;

    public Integer page;

    public Integer perPage;
}
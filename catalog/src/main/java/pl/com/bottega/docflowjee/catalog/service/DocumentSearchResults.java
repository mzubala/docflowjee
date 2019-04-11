package pl.com.bottega.docflowjee.catalog.service;

import pl.com.bottega.docflowjee.catalog.model.BasicDocumentInfo;

import java.util.List;

public class DocumentSearchResults {

    List<BasicDocumentInfo> documents;
    Integer totalCount;
    Integer page;
    Integer perPage;

}

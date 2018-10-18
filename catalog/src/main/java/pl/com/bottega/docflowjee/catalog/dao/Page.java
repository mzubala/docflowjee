package pl.com.bottega.docflowjee.catalog.dao;

import java.util.List;

public class Page<T> {

    public List<T> items;
    public Integer perPage;
    public Integer pagesCount;
    public Integer total;

}

package pl.com.bottega.docflowjee.catalog;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources {

    @Produces
    @PersistenceContext
    private EntityManager entityManager;

    @Produces
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper;
    }

}

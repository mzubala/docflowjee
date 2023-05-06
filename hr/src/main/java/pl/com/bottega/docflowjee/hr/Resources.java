package pl.com.bottega.docflowjee.hr;

import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class Resources {

    @PersistenceContext
    @Produces
    private EntityManager em;

}

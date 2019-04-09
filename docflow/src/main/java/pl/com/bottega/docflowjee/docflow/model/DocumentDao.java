package pl.com.bottega.docflowjee.docflow.model;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class DocumentDao {

    @Inject
    private EntityManager entityManager;

    public void save(Document document) {
        entityManager.persist(document);
    }

    public Document find(String number) {
        try {
            return (Document) entityManager.createNamedQuery("Document.findByNumber")
                .setParameter("number", number).getSingleResult();
        } catch (NoResultException ex) {
            throw new DocumentNotFoundException(number);
        }
    }

    public boolean isNumberOccupied(String number) {
        return ((Long) entityManager.createNamedQuery("Document.countByNumber")
            .setParameter("number", number).getSingleResult()) > 1;
    }

}

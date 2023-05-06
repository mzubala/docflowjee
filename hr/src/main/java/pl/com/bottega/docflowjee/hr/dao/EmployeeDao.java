package pl.com.bottega.docflowjee.hr.dao;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import pl.com.bottega.docflowjee.hr.model.Employee;


public class EmployeeDao {

    @Inject
    private EntityManager entityManager;

    public Employee save(Employee employee) {
        return entityManager.merge(employee);
    }

    public Employee find(Long id) {
        return (Employee) entityManager.createNamedQuery("Employee.findById")
            .setParameter("id", id)
            .getSingleResult();
    }
}

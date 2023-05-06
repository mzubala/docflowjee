package pl.com.bottega.docflowjee.hr.service;

import pl.com.bottega.docflowjee.hr.dao.EmployeeDao;
import pl.com.bottega.docflowjee.hr.model.Employee;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

public class EmployeesService {

    @Inject
    private EmployeeDao employeeDao;

    @Transactional
    public Employee save(Employee employee) {
        return employeeDao.save(employee);
    }

    public Employee find(Long id) {
        return employeeDao.find(id);
    }
}

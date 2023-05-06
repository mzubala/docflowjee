package pl.com.bottega.docflowjee.hr.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@NamedQuery(name = "Employee.findById", query =
    "SELECT e FROM Employee e " +
        "LEFT JOIN FETCH e.salaries " +
        "LEFT JOIN FETCH e.departments ed " +
        "LEFT JOIN FETCH ed.department " +
        "WHERE e.id=:id"
)
public class Employee {

    @Id
    private Long id;
    private String firstName;
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private Set<EmployeeDepartment> departments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="employee_id")
    private Set<Salary> salaries = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<EmployeeDepartment> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<EmployeeDepartment> departments) {
        this.departments = departments;
    }

    public Set<Salary> getSalaries() {
        return salaries;
    }

    public void setSalaries(Set<Salary> salaries) {
        this.salaries = salaries;
    }
}

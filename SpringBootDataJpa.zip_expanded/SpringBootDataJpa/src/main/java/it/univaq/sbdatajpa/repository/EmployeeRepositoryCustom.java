package it.univaq.sbdatajpa.repository;

import it.univaq.sbdatajpa.domain.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Luigi Brandolini
 */
public interface EmployeeRepositoryCustom {
    @Query(value = "FROM Employee e WHERE e.department.id = ?1 ")
    public List<Employee> findByDepartment(int depId);
}

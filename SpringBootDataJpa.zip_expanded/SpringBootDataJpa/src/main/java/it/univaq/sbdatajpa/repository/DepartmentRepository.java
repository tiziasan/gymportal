package it.univaq.sbdatajpa.repository;

import it.univaq.sbdatajpa.domain.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Luigi Brandolini
 */
@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {
}

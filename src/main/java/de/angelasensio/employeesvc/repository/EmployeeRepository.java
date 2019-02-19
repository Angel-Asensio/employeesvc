package de.angelasensio.employeesvc.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.angelasensio.employeesvc.data.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, UUID> {
}

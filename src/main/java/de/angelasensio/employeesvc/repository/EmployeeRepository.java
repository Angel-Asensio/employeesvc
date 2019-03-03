package de.angelasensio.employeesvc.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.angelasensio.employeesvc.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}

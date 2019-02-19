package de.angelasensio.employeesvc.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import de.angelasensio.employeesvc.data.Employee;
import de.angelasensio.employeesvc.repository.EmployeeRepository;
import de.angelasensio.employeesvc.util.EmployeeNotFoundException;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee create(final Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee employeeForUUID(final UUID uuid) {
        Optional<Employee> employee = employeeRepository.findById(uuid);

        if (!employee.isPresent()) {
            throw new EmployeeNotFoundException(uuid);
        }

        return employee.get();
    }

    public void deleteEmployee(UUID uuid) {
        employeeRepository.deleteById(uuid);
    }

    public boolean updateEmployee(Employee employee, final UUID uuid) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(uuid);

        if (!optionalEmployee.isPresent()) {
            return false;
        }

        employee.setId(uuid);
        employeeRepository.save(employee);
        return true;
    }
}

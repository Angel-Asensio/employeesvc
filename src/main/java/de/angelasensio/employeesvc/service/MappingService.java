package de.angelasensio.employeesvc.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import de.angelasensio.employeesvc.dto.EmployeeDto;
import de.angelasensio.employeesvc.model.Employee;

@Service
@RequiredArgsConstructor
public class MappingService {

    private final ModelMapper modelMapper;

    public Employee convertToEntity(EmployeeDto employeeDto) {
        return modelMapper.map(employeeDto, Employee.class);
    }

    public EmployeeDto convertToDto(Employee employee) {
        return modelMapper.map(employee, EmployeeDto.class);
    }
}

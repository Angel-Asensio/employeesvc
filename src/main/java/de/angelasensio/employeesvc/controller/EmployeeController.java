package de.angelasensio.employeesvc.controller;

import static java.util.Objects.requireNonNull;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import de.angelasensio.employeesvc.dto.EmployeeDto;
import de.angelasensio.employeesvc.event.EmployeeEvent;
import de.angelasensio.employeesvc.model.Employee;
import de.angelasensio.employeesvc.service.EmployeeService;
import de.angelasensio.employeesvc.service.MappingService;
import de.angelasensio.employeesvc.service.MessageProducer;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;
    private final MappingService mappingService;
    private final MessageProducer messageProducer;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        requireNonNull(employeeDto, "employeeDto cannot be null");
        LOG.info("createEmployee: {}", employeeDto.toString());
        Employee employee = mappingService.convertToEntity(employeeDto);

        Employee savedEmployee = employeeService.create(employee);

        sendEventToMessageProducer(savedEmployee.getId(), "create");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedEmployee.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> retrieveEmployee(@PathVariable UUID id) {
        requireNonNull(id, "UUID cannot be null");
        LOG.info("retrieveEmployee: {}", id);
        Employee foundEmployee = employeeService.employeeForUUID(id);
        EmployeeDto employeeDto = mappingService.convertToDto(foundEmployee);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable UUID id) {
        requireNonNull(employeeDto, "employeeDto cannot be null");
        requireNonNull(id, "UUID cannot be null");
        LOG.info("updateEmployee: {} with id: {}", employeeDto.toString(), id);
        Employee employee = mappingService.convertToEntity(employeeDto);
        boolean wasUpdated = employeeService.updateEmployee(employee, id);

        if (!wasUpdated) {
            return ResponseEntity.notFound().build();
        }

        sendEventToMessageProducer(id, "update");

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable UUID id) {
        requireNonNull(id, "UUID cannot be null");
        LOG.info("deleteEmployee: {}", id);
        employeeService.deleteEmployee(id);

        sendEventToMessageProducer(id, "delete");
    }

    private void sendEventToMessageProducer(final UUID uuid, final String operation) {
        EmployeeEvent employeeEvent = EmployeeEvent.builder()
                .employeeUuid(uuid)
                .operation(operation)
                .timestamp(System.currentTimeMillis())
                .build();

        messageProducer.sendEvent(employeeEvent);
    }

}

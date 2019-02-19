package de.angelasensio.employeesvc.util;

import java.util.UUID;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(UUID uuid) {
        super("Could not find employee with UUID: " + uuid);
    }
}

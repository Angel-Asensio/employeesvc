package de.angelasensio.employeesvc.dto;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude = "hobbies")
public class EmployeeDto {

    private UUID id;

    private String email;

    private String firstName;

    private String lastName;

    private Date birthday;

    private Set<HobbyDto> hobbies;

}

package de.angelasensio.employeesvc.data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
@ToString
public class Employee {

    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Date birthday;

    @OneToMany(mappedBy = "employee")
    private List<Hobby> hobbies;

    public Employee() {
        // type 4 (pseudo randomly generated) UUID
        id = UUID.randomUUID();
    }
}

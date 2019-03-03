package de.angelasensio.employeesvc.model;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
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
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Hobby> hobbies;

    public Employee() {
        // type 4 (pseudo randomly generated) UUID
        id = UUID.randomUUID();
    }

    public Employee(final String email, final String firstName, final String lastName, final Date birthday, final Set<Hobby> hobbies) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.hobbies = hobbies;
        this.hobbies.forEach(x -> x.setEmployee(this));
    }
}

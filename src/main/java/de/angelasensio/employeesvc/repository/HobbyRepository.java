package de.angelasensio.employeesvc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.angelasensio.employeesvc.data.Hobby;

@Repository
public interface HobbyRepository extends CrudRepository<Hobby, Long> {
}

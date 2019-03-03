package de.angelasensio.employeesvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.angelasensio.employeesvc.model.Hobby;

@Repository
public interface HobbyRepository extends JpaRepository<Hobby, Long> {
}

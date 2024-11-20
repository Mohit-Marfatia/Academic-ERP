package com.mohitmarfatia.academicerp.repo;

import com.mohitmarfatia.academicerp.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employees, Long> {
    Optional<Employees> findByEmail(String email);
}

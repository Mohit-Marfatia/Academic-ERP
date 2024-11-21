package com.mohitmarfatia.academicerp.repo;

import com.mohitmarfatia.academicerp.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employees, Long> {
    Optional<Employees> findByEmail(String email);

    @Query("SELECT e FROM Employees e WHERE e.employeeId != :id")
    List<Employees> getAllExcept(@Param("id") Long id);
}

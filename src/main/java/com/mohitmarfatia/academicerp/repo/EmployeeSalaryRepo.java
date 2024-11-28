package com.mohitmarfatia.academicerp.repo;

import com.mohitmarfatia.academicerp.entity.EmployeeSalary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeSalaryRepo  extends JpaRepository<EmployeeSalary, Long> {
}

package com.mohitmarfatia.academicerp.repo;

import com.mohitmarfatia.academicerp.entity.EmployeeAccounts;
import com.mohitmarfatia.academicerp.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAccountRepo extends JpaRepository<EmployeeAccounts, Long> {
    EmployeeAccounts findByEmployee(Employees employee);
}

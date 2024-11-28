package com.mohitmarfatia.academicerp.mapper;

import com.mohitmarfatia.academicerp.entity.EmployeeSalary;
import com.mohitmarfatia.academicerp.entity.Employees;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmployeeSalaryMapper {
    public EmployeeSalary toEntity(Employees employee) {
        return EmployeeSalary.builder()
                .amount(employee.getSalary())
                .description(LocalDate.now().getMonth() + " Month Salary")
                .paymentDate(LocalDate.now())
                .employee(employee)
                .build();
    }
}

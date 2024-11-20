package com.mohitmarfatia.academicerp.mapper;

import com.mohitmarfatia.academicerp.dto.employee.EmployeeRequest;
import com.mohitmarfatia.academicerp.entity.Employees;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {
    public Employees toEntity(EmployeeRequest request) {
        return Employees.builder().email(request.email()).password(request.password()).build();
    }
}

package com.mohitmarfatia.academicerp.mapper;

import com.mohitmarfatia.academicerp.dto.employee.EmployeeRequest;
import com.mohitmarfatia.academicerp.dto.employee.EmployeeResponse;
import com.mohitmarfatia.academicerp.entity.Employees;
import org.springframework.stereotype.Service;

//@Service
//public class EmployeeMapper {
//    public Employees toEntity(EmployeeRequest request) {
//        return Employees.builder().email(request.email()).password(request.password()).build();
//    }
//
////    public EmployeeResponse toResponse(Employees employees) {
//////        String departmentName = employees.getDepartment() != null ? employees.getDepartment().getName() : "null";
////        return new EmployeeResponse(
////                employees.getEmployeeId(),
////                employees.getFirstName(),
////                employees.getLastName(),
////                employees.getEmail(),
////                employees.getTitle(),
////                employees.getSalary(),
////                employees.getPhotographPath(),
////                employees.getDepartment().getName()
////        );
////    }
//}

@Service
public class EmployeeMapper {
    public Employees toEntity(EmployeeRequest request) {
        return Employees.builder().email(request.email()).password(request.password()).build();
    }

    public EmployeeResponse toResponse(Employees employees) {
        return new EmployeeResponse(
                employees.getEmployeeId(),
                employees.getFirstName(),
                employees.getLastName(),
                employees.getEmail(),
                employees.getTitle(),
                employees.getSalary(),
                employees.getPhotographPath(),
                employees.getDepartment().getName()
        );
    }
}
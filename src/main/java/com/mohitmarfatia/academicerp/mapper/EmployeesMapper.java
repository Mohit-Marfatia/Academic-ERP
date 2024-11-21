package com.mohitmarfatia.academicerp.mapper;

import com.mohitmarfatia.academicerp.dto.employee.EmployeeRequest;
import com.mohitmarfatia.academicerp.dto.employee.EmployeeResponse;
import com.mohitmarfatia.academicerp.entity.EmployeeSalary;
import com.mohitmarfatia.academicerp.entity.Employees;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

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
public class EmployeesMapper {
    public Employees toEntity(EmployeeRequest request) {
        return Employees.builder().email(request.email()).password(request.password()).build();
    }

    public EmployeeResponse toResponse(Employees employees) {
        List<EmployeeSalary> salaries = employees.getSalaries();
        System.out.println(salaries.size());
//        System.out.println(salaries.get(salaries.size()-1));
        LocalDate paymentDate =
                employees.getSalaries().stream()
                .max(Comparator.comparing(EmployeeSalary::getPaymentDate)) // Find the latest payment date
                .map(EmployeeSalary::getPaymentDate) // Extract the date
                .orElse(null);
        return new EmployeeResponse(
                employees.getEmployeeId(),
                employees.getFirstName(),
                employees.getLastName(),
                employees.getEmail(),
                employees.getTitle(),
                employees.getSalary(),
                employees.getPhotographPath(),
                employees.getDepartment().getName(),
                paymentDate
        );
    }
}
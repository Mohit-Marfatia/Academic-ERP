package com.mohitmarfatia.academicerp.service;

import com.mohitmarfatia.academicerp.dto.employee.EmployeeAuthResponse;
import com.mohitmarfatia.academicerp.dto.employee.EmployeeRequest;
import com.mohitmarfatia.academicerp.dto.employee.EmployeeResponse;
import com.mohitmarfatia.academicerp.dto.employee.LoginRequest;
import com.mohitmarfatia.academicerp.helper.EncryptionService;
import com.mohitmarfatia.academicerp.helper.JWTHelper;
import com.mohitmarfatia.academicerp.mapper.EmployeesMapper;
import com.mohitmarfatia.academicerp.repo.DepartmentsRepo;
import com.mohitmarfatia.academicerp.repo.EmployeeRepo;
import com.mohitmarfatia.academicerp.entity.Employees;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final DepartmentsRepo departmentsRepo;
    private final EmployeesMapper employeesMapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String updatePassword(EmployeeRequest request) {
        Optional<Employees> optionalEmployee = employeeRepo.findByEmail(request.email());

        if (optionalEmployee.isPresent()) {
            Employees employee = optionalEmployee.get();
            Employees mapperEntity = employeesMapper.toEntity(request);
            employee.setPassword(encryptionService.encodePassword(mapperEntity.getPassword()));
            employeeRepo.save(employee);
        }
        return "Password updated";
    }

    public EmployeeAuthResponse loginCustomer(LoginRequest request) {
        Optional<Employees> optionalEmployee = employeeRepo.findByEmail(request.email());

        Employees employee;
        if (optionalEmployee.isPresent()) {
            employee = optionalEmployee.get();
            if (encryptionService.verifyPassword(request.password(), employee.getPassword())) {
                if (Objects.equals(employee.getDepartment().getName(), "Accounts")) {
                    return new EmployeeAuthResponse(jwtHelper.generateToken(employee.getEmployeeId()), "Login Successful", 201);
                } else return new EmployeeAuthResponse("null", "Only Accounts Department can access this feature!", 401);
            } else return new EmployeeAuthResponse("null", "Wrong Password!", 401);
        }

        return new EmployeeAuthResponse("null", "Email not found!", 401);
    }

    public List<EmployeeResponse> getAllEmployees(Long id) {
        List<Employees> employeesList = employeeRepo.getAllExcept(id);
        return employeesList.stream()
                .map(employeesMapper::toResponse
                )
                .toList();
    }
}

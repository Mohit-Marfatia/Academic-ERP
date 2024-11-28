package com.mohitmarfatia.academicerp.service;

import com.mohitmarfatia.academicerp.dto.employee.*;
import com.mohitmarfatia.academicerp.entity.EmployeeAccounts;
import com.mohitmarfatia.academicerp.entity.EmployeeSalary;
import com.mohitmarfatia.academicerp.helper.EncryptionService;
import com.mohitmarfatia.academicerp.helper.JWTHelper;
import com.mohitmarfatia.academicerp.mapper.EmployeeSalaryMapper;
import com.mohitmarfatia.academicerp.mapper.EmployeesMapper;
import com.mohitmarfatia.academicerp.repo.DepartmentsRepo;
import com.mohitmarfatia.academicerp.repo.EmployeeAccountRepo;
import com.mohitmarfatia.academicerp.repo.EmployeeRepo;
import com.mohitmarfatia.academicerp.entity.Employees;
import com.mohitmarfatia.academicerp.repo.EmployeeSalaryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final DepartmentsRepo departmentsRepo;
    private final EmployeesMapper employeesMapper;
    private final EmployeeSalaryMapper employeeSalaryMapper;
    private final EncryptionService encryptionService;
    private final EmployeeSalaryRepo employeeSalaryRepo;
    private final EmployeeAccountRepo employeeAccountRepo;
    private final JWTHelper jwtHelper;

    public String updatePassword(EmployeeRequest request) {
        Optional<Employees> optionalEmployee = employeeRepo.findByEmail(request.email());

        if (optionalEmployee.isPresent()) {
            Employees employee = optionalEmployee.get();
            Employees mapperEntity = employeesMapper.toAuthEntity(request);
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
                } else
                    return new EmployeeAuthResponse("null", "Only Accounts Department can access this feature!", 401);
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

    public String updateEmployee(EmployeeResponse request) {
        Optional<Employees> optionalEmployee = employeeRepo.findById(request.employeeId());
        if (optionalEmployee.isPresent()) {
            Employees employee = optionalEmployee.get();
            employee.setSalary(request.salary());
            employeeRepo.save(employee);
        }
        return "Updated";
    }

    public String disburseSalary(EmployeeSalaryRequest request) {
        for (Long id : request.empIds()) {
            Employees employee = employeeRepo.findById(id).orElse(null);

            if (employee != null) {
                EmployeeSalary employeeSalary = employeeSalaryMapper.toEntity(employee);

                employeeSalaryRepo.save(employeeSalary);

                EmployeeAccounts employeeAccount = employeeAccountRepo.findByEmployee(employee);

                if (employeeAccount == null) {
                    EmployeeAccounts newEmployeeAccount = new EmployeeAccounts();
                    newEmployeeAccount.setEmployee(employee);
                    newEmployeeAccount.setEmployeeBalance(0.0);

                    newEmployeeAccount.setEmployeeBalance(newEmployeeAccount.getEmployeeBalance() + employeeSalary.getAmount());

                    employeeAccountRepo.save(newEmployeeAccount);
                } else {
                    employeeAccount.setEmployeeBalance(employeeAccount.getEmployeeBalance() + employeeSalary.getAmount());

                    employeeAccountRepo.save(employeeAccount);
                }

            } else {
                System.out.println("Employee with ID " + id + " not found.");
            }
        }

        return "Salary disbursement completed for selected employees.";
    }
}

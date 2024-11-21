package com.mohitmarfatia.academicerp.service;

import com.mohitmarfatia.academicerp.dto.employee.EmployeeRequest;
import com.mohitmarfatia.academicerp.dto.employee.EmployeeResponse;
import com.mohitmarfatia.academicerp.dto.employee.LoginRequest;
import com.mohitmarfatia.academicerp.helper.EncryptionService;
import com.mohitmarfatia.academicerp.helper.JWTHelper;
import com.mohitmarfatia.academicerp.mapper.EmployeeMapper;
import com.mohitmarfatia.academicerp.repo.DepartmentsRepo;
import com.mohitmarfatia.academicerp.repo.EmployeeRepo;
import com.mohitmarfatia.academicerp.entity.Employees;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo employeeRepo;
    private final DepartmentsRepo departmentsRepo;
    private final EmployeeMapper employeeMapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String updatePassword(EmployeeRequest request){
        Optional<Employees> optionalEmployee = employeeRepo.findByEmail(request.email());

        if (optionalEmployee.isPresent()) {
            Employees employee = optionalEmployee.get();
            Employees mapperEntity = employeeMapper.toEntity(request);
            employee.setPassword(encryptionService.encodePassword(mapperEntity.getPassword()));
            employeeRepo.save(employee);
        }
        return "Password updated";
    }

    public String loginCustomer(LoginRequest request) {
        Optional<Employees> optionalEmployee = employeeRepo.findByEmail(request.email());

        Employees employee;
        if (optionalEmployee.isPresent()) {
            employee = optionalEmployee.get();
            return encryptionService.verifyPassword(request.password(), employee.getPassword()) ? jwtHelper.generateToken(employee.getEmployeeId()) : "Wrong password";
        }

        return "Email not found";
    }

    public List<EmployeeResponse> getAllEmployees(Long id) {
        Optional<Employees> optionalEmployee = employeeRepo.findById(id);

        Employees employee;
        if(optionalEmployee.isPresent()) {
            employee = optionalEmployee.get();
//            Departments department = employee.getDepartment();
            List<Employees> employeesList = employeeRepo.getAllExcept(id);
            return employeesList.stream()
                    .map(employeeMapper::toResponse
                    )
                    .toList();
        }
        return null;
    }
}

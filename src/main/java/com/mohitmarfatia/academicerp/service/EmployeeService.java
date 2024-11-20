package com.mohitmarfatia.academicerp.service;

import com.mohitmarfatia.academicerp.dto.employee.EmployeeRequest;
import com.mohitmarfatia.academicerp.dto.employee.LoginRequest;
import com.mohitmarfatia.academicerp.helper.EncryptionService;
import com.mohitmarfatia.academicerp.helper.JWTHelper;
import com.mohitmarfatia.academicerp.mapper.EmployeeMapper;
import com.mohitmarfatia.academicerp.repo.EmployeeRepo;
import com.mohitmarfatia.academicerp.entity.Employees;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepo repo;
    private final EmployeeMapper mapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String updatePassword(EmployeeRequest request){
        Optional<Employees> optionalEmployee = repo.findByEmail(request.email());

        if (optionalEmployee.isPresent()) {
            Employees employee = optionalEmployee.get();
            Employees mapperEntity = mapper.toEntity(request);
            employee.setPassword(encryptionService.encodePassword(mapperEntity.getPassword()));
            repo.save(employee);
        }
        return "Password updated";
    }

    public String loginCustomer(LoginRequest request) {
        Optional<Employees> optionalEmployee = repo.findByEmail(request.email());

        Employees employee;
        if (optionalEmployee.isPresent()) {
            employee = optionalEmployee.get();
            return encryptionService.verifyPassword(request.password(), employee.getPassword()) ? jwtHelper.generateToken(employee.getEmployeeId()) : "Wrong password";
        }

        return "Email not found";
    }
}

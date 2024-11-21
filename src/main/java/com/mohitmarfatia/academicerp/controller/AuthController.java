package com.mohitmarfatia.academicerp.controller;

import com.mohitmarfatia.academicerp.dto.employee.EmployeeAuthResponse;
import com.mohitmarfatia.academicerp.dto.employee.LoginRequest;
import com.mohitmarfatia.academicerp.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final EmployeeService employeeService;

    @PostMapping()
    public ResponseEntity<EmployeeAuthResponse> loginEmployee(@RequestBody @Valid LoginRequest loginRequest) {
        EmployeeAuthResponse employeeAuthResponse = employeeService.loginCustomer(loginRequest);
        if (employeeAuthResponse.statusCode() == 201) {
            return ResponseEntity.ok(employeeService.loginCustomer(loginRequest));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(employeeAuthResponse);
        }

    }
}

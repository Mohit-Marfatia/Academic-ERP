package com.mohitmarfatia.academicerp.controller;

import com.mohitmarfatia.academicerp.dto.employee.EmployeeRequest;
import com.mohitmarfatia.academicerp.dto.employee.LoginRequest;
import com.mohitmarfatia.academicerp.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PatchMapping()
    public ResponseEntity<String> updatePassword(@RequestBody @Valid EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.updatePassword(employeeRequest));
    }

    @PostMapping()
    public ResponseEntity<String> loginEmployee(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(employeeService.loginCustomer(loginRequest));
    }
}

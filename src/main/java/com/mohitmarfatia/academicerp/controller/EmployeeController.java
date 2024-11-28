package com.mohitmarfatia.academicerp.controller;

import com.mohitmarfatia.academicerp.dto.employee.*;
import com.mohitmarfatia.academicerp.helper.JWTHelper;
import com.mohitmarfatia.academicerp.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final JWTHelper jwtHelper;

    @PatchMapping()
    public ResponseEntity<String> updatePassword(@RequestBody @Valid EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.updatePassword(employeeRequest));
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeResponse>> getEmployees(@RequestHeader(name="Authorization") String authToken) {
        String token = authToken.split(" ")[1].trim();
        Long id = jwtHelper.extractUserId(token);
        return ResponseEntity.ok(employeeService.getAllEmployees(id));
    }

    @PutMapping()
    public ResponseEntity<String> updateEmployee(@RequestBody @Valid EmployeeResponse request) {
        return ResponseEntity.ok(employeeService.updateEmployee(request));
    }

    @PostMapping("/disburse")
    public ResponseEntity<String> disburseSalary(@RequestBody @Valid EmployeeSalaryRequest empIds) {
        System.out.println(empIds);
        return ResponseEntity.ok(employeeService.disburseSalary(empIds));
    }
}

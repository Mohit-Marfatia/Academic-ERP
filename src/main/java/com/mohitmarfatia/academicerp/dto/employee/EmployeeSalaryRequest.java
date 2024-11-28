package com.mohitmarfatia.academicerp.dto.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record EmployeeSalaryRequest(
        @JsonProperty("empIds")
        List<Long> empIds
) {
}

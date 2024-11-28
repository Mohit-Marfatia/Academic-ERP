package com.mohitmarfatia.academicerp.dto.employee;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmployeeUpdateRequest(
        @JsonProperty("employee_id")
        Long employeeId,

        @JsonProperty("salary")
        Double salary
) {
}

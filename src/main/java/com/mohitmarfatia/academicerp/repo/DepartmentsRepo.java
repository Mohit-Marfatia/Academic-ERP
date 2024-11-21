package com.mohitmarfatia.academicerp.repo;

import com.mohitmarfatia.academicerp.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentsRepo extends JpaRepository<Departments, Long> {
}

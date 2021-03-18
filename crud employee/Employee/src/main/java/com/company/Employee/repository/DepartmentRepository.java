package com.company.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.Employee.model.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {

}

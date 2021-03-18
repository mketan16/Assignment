package com.company.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.Employee.model.EmployeeEntity;


public interface EmployeeRepository  extends JpaRepository<EmployeeEntity, Integer>{

}

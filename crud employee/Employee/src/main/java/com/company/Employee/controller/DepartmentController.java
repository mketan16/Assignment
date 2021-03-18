package com.company.Employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.Employee.service.DepartmentService;
import com.company.Employee.util.ServiceResponse;


@RestController
@RequestMapping(path="/api")
public class DepartmentController {
	@Autowired
	DepartmentService departmentService;
	@RequestMapping(value="/retriveAllDept", method=RequestMethod.GET)
    public ServiceResponse retriveAllDept() {
    	 ServiceResponse serviceResponse = new ServiceResponse();
    	 serviceResponse = departmentService.retriveAllEmp();
		 return serviceResponse;
    }
}

package com.company.Employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.Employee.model.EmployeeEntity;
import com.company.Employee.service.EmployeeService;
import com.company.Employee.util.ServiceResponse;

@RestController
@RequestMapping(path="/api")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@RequestMapping(value="/retriveAllEmp", method=RequestMethod.GET)
    public ServiceResponse retriveAllEmp() {
    	 ServiceResponse serviceResponse = new ServiceResponse();
    	 serviceResponse = employeeService.retriveAllEmp();
		 return serviceResponse;
    }
	
	@RequestMapping(value="/deleteEmp", method=RequestMethod.DELETE)
    public ServiceResponse deleteEmp(@RequestParam ("empId") int empId) {
    	 ServiceResponse serviceResponse = new ServiceResponse();
    	 serviceResponse = employeeService.deleteEmp(empId);
		 return serviceResponse;
    }
	
	@RequestMapping(value="/saveEmp", method=RequestMethod.POST)
    public ServiceResponse saveEmp(@RequestBody EmployeeEntity emp) {
    	 ServiceResponse serviceResponse = new ServiceResponse();
    	 serviceResponse = employeeService.saveEmp(emp);
		 return serviceResponse;
    }
	
	@RequestMapping(value="/editEmp", method=RequestMethod.POST)
    public ServiceResponse editEmp(@RequestBody EmployeeEntity emp) {
    	 ServiceResponse serviceResponse = new ServiceResponse();
    	 serviceResponse = employeeService.saveEmp(emp);
		 return serviceResponse;
    }
}

package com.company.Employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Employee.model.EmployeeEntity;
import com.company.Employee.repository.EmployeeRepository;
import com.company.Employee.util.ServiceResponse;


@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	public ServiceResponse retriveAllEmp() {
		ServiceResponse serviceResponse = new ServiceResponse();
		List<EmployeeEntity> empList= new ArrayList<EmployeeEntity>();

		try {
			empList =  employeeRepository.findAll();
			if(empList.size() > 0 || empList != null) {
				serviceResponse.setServiceError("");
				serviceResponse.setServiceResponse(empList);
				serviceResponse.setServiceStatus(ServiceResponse.STATUS_SUCCESS);
			} else {
				serviceResponse.setServiceError("Data not available");
				serviceResponse.setServiceResponse("Data not available");
				serviceResponse.setServiceStatus(ServiceResponse.STATUS_FAIL);
			}
			return serviceResponse;
		}catch (Exception e) {
			e.printStackTrace();
			serviceResponse.setServiceError(e.toString());
			serviceResponse.setServiceResponse("Something went wrong");
			serviceResponse.setServiceStatus(ServiceResponse.STATUS_FAIL);
			return serviceResponse;
		}
	}

	@Transactional
	public ServiceResponse deleteEmp(int empId) {
		ServiceResponse serviceResponse = new ServiceResponse();

		try {
			Optional<EmployeeEntity> empEntity  =  employeeRepository.findById(empId);
			if(empEntity.isPresent()) {
				employeeRepository.deleteById(empId);
				serviceResponse.setServiceError("");
				serviceResponse.setServiceResponse("Record deleted Successfully");
				serviceResponse.setServiceStatus(ServiceResponse.STATUS_SUCCESS);
			} else {
				serviceResponse.setServiceError("Record not found");
				serviceResponse.setServiceResponse("Record not found");
				serviceResponse.setServiceStatus(ServiceResponse.STATUS_FAIL);
			}
			return serviceResponse;
		}catch (Exception e) {
			e.printStackTrace();
			serviceResponse.setServiceError(e.toString());
			serviceResponse.setServiceResponse("Something went wrong");
			serviceResponse.setServiceStatus(ServiceResponse.STATUS_FAIL);
			return serviceResponse;
		}
	}
	@Transactional
	public ServiceResponse saveEmp(EmployeeEntity emp) {
		ServiceResponse serviceResponse = new ServiceResponse();

		try {
			employeeRepository.save(emp);
			serviceResponse.setServiceError("");
			serviceResponse.setServiceResponse("Record inserted Successfully");
			serviceResponse.setServiceStatus(ServiceResponse.STATUS_SUCCESS);
			return serviceResponse;
		}catch (Exception e) {
			e.printStackTrace();
			serviceResponse.setServiceError(e.toString());
			serviceResponse.setServiceResponse("Something went wrong");
			serviceResponse.setServiceStatus(ServiceResponse.STATUS_FAIL);
			return serviceResponse;
		}
	}
}

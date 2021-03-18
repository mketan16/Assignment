package com.company.Employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Employee.model.DepartmentEntity;
import com.company.Employee.repository.DepartmentRepository;
import com.company.Employee.util.ServiceResponse;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public ServiceResponse retriveAllEmp() {
		ServiceResponse serviceResponse = new ServiceResponse();
		List<DepartmentEntity> deptList= new ArrayList<DepartmentEntity>();
		
		try {
			deptList =  departmentRepository.findAll();
			if(deptList.size() > 0 || deptList != null) {
				serviceResponse.setServiceError("");
				serviceResponse.setServiceResponse(deptList);
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

}
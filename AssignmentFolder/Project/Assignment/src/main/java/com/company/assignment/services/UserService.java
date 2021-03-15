package com.company.assignment.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.assignment.models.UserEntity;
import com.company.assignment.repository.UserRepository;
import com.company.util.ServiceResponse;
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	Logger log = Logger.getLogger(UserService.class.getName());
	
	public ServiceResponse authontication(UserEntity user) {
		log.info("UserService, method = authontication");
		ServiceResponse serviceResponse = new ServiceResponse();
		Object userloggedin = new Object();
		try {
			userloggedin = userRepository.authontication(user.getUserName(),user.getPassword());
			if(userloggedin != null) {
				serviceResponse.setServiceError("");
				serviceResponse.setServiceResponse("Login Successfully");
				serviceResponse.setServiceStatus(ServiceResponse.STATUS_SUCCESS);
				return serviceResponse;
			}
			else {
				serviceResponse.setServiceError("");
				serviceResponse.setServiceResponse("Login fail");
				serviceResponse.setServiceStatus(ServiceResponse.STATUS_FAIL);
				return serviceResponse;
			}

		} catch (Exception e) {
			serviceResponse.setServiceError(e.toString());
			serviceResponse.setServiceResponse("Something went wrong");
			serviceResponse.setServiceStatus(ServiceResponse.STATUS_FAIL);
			log.info("Error--"+e.toString());
			return serviceResponse;
		} 
		

	}
}

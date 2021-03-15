package com.company.assignment.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.assignment.models.EventEntity;
import com.company.assignment.models.UserEntity;
import com.company.assignment.services.EventService;
import com.company.assignment.services.UserService;
import com.company.util.ServiceResponse;

@RestController
@RequestMapping(path="/api")
public class UserController {
	@Autowired
	private UserService userservice;
	Logger log = Logger.getLogger(UserController.class.getName());

	@RequestMapping(value="/authontication", method=RequestMethod.POST)
	public ServiceResponse authontication(@RequestBody UserEntity user) {
		log.info("UserController, method = authontication");
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse = userservice.authontication(user);
		return serviceResponse;
	}
}

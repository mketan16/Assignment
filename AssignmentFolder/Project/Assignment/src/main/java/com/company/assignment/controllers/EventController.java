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
import com.company.util.ServiceResponse;

@RestController
@RequestMapping(path="/api")
public class EventController {
	@Autowired
	private EventService eventservice;
	Logger log = Logger.getLogger(EventController.class.getName());
	
	@RequestMapping(value="/retriveAllEvent", method=RequestMethod.POST)
    public ServiceResponse retriveAllEvent() {
		log.info("EventController, method = retriveAllEvent");
    	 ServiceResponse serviceResponse = new ServiceResponse();
    	 serviceResponse = eventservice.retriveAllEvent();
		 return serviceResponse;
    }
	
	@RequestMapping(value="/saveEvent", method=RequestMethod.POST)
    public ServiceResponse saveEvent(@RequestBody EventEntity event) {
		log.info("EventController, method = saveEvent");
    	 ServiceResponse serviceResponse = new ServiceResponse();
    	 serviceResponse = eventservice.saveEvent(event);
		 return serviceResponse;
    }
	
	@RequestMapping(value="/deleteEvent", method=RequestMethod.POST)
    public ServiceResponse deleteEvent(@RequestParam("eventId") int eventId) {
		log.info("EventController, method = deleteEvent");
    	 ServiceResponse serviceResponse = new ServiceResponse();
    	 serviceResponse = eventservice.deleteEvent(eventId);
		 return serviceResponse;
    }
}

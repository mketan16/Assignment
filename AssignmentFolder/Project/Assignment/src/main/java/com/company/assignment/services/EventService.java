package com.company.assignment.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.assignment.models.EventEntity;
import com.company.assignment.models.UserEntity;
import com.company.assignment.repository.EventRepository;
import com.company.util.ServiceResponse;

@Service
public class EventService {
	@Autowired
	private EventRepository eventRepository;

	Logger log = Logger.getLogger(EventService.class.getName());

	String[] days = {"S","M", "T", "W", "T", "F", "S"};

	public ServiceResponse retriveAllEvent() {
		ServiceResponse serviceResponse = new ServiceResponse();
		List eventEntityList= new ArrayList<>();
		log.info("EventService, method = retriveAllEvent");
		try {
			eventEntityList =  eventRepository.retriveAllEvent();
			if(eventEntityList.size() > 0) {
				serviceResponse.setServiceError("");
				serviceResponse.setServiceResponse(eventEntityList);
				serviceResponse.setServiceStatus(ServiceResponse.STATUS_SUCCESS);
				
			} else {
				serviceResponse.setServiceError("");
				serviceResponse.setServiceResponse("");
				serviceResponse.setServiceStatus(ServiceResponse.STATUS_SUCCESS);
			}
			return serviceResponse;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Error in EventService, method = retriveAllEvent " + e.toString());
			serviceResponse.setServiceError(e.toString());
			serviceResponse.setServiceResponse("Something went wrong");
			serviceResponse.setServiceStatus(ServiceResponse.STATUS_FAIL);
			return serviceResponse;
		}

	}

	public ServiceResponse saveEvent(EventEntity event) {
		ServiceResponse serviceResponse = new ServiceResponse();
		log.info("EventService, method = submitEvent");
		try {
			if(event.getDowType() == 5) {
				serviceResponse = saveWeekdaysEvent(event);
			} else if (event.getDowType() == 2) {
				serviceResponse = saveWeekendEvent(event);
			} else if (event.getDowType() == 7) {
				serviceResponse = saveAllDaysEvent(event);
			}
			return serviceResponse;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Error in EventService, method = saveEvent " + e.toString());
			serviceResponse.setServiceError(e.toString());
			serviceResponse.setServiceResponse("Something went wrong");
			serviceResponse.setServiceStatus(ServiceResponse.STATUS_FAIL);
			return serviceResponse;
		}

	}
	public ServiceResponse saveAllDaysEvent(EventEntity event) {
		ServiceResponse serviceResponse = new ServiceResponse();
		log.info("EventService, method = saveAllDaysEvent");
		try {
			Calendar start = Calendar.getInstance();
			start.setTime(event.getStartDate());
			Calendar end = Calendar.getInstance();
			end.setTime(event.getEndDate());
			int allDays = 0;
			EventEntity eventDetails = new EventEntity();
			String dow = "";
			boolean firstWeekEvent = false;
			while( !start.after(end)){
				if(allDays == 0) {
					event.setStartDate(start.getTime());
				}

				if (allDays < 7) {
					dow = dow + " " + days[start.getTime().getDay()];
					if(start.getTime().getDay() == 0 && firstWeekEvent == false) {
						eventDetails = new EventEntity();
						eventDetails.setEventName(event.getEventName());
						eventDetails.setStartDate(event.getStartDate());
						eventDetails.setEndDate(start.getTime());
						eventDetails.setStartTime(event.getStartTime());
						eventDetails.setEndTime(event.getEndTime());
						eventDetails.setDow(dow);
						eventRepository.save(eventDetails);
						dow = "";
						allDays = 0;
						firstWeekEvent = true;
					}else {
						allDays++;
					}
					start.add(Calendar.DATE, 1);

				} else if(allDays == 7){
					eventDetails = new EventEntity();
					eventDetails.setEventName(event.getEventName());
					eventDetails.setStartDate(event.getStartDate());
					Calendar temp = Calendar.getInstance();
					temp.setTime(start.getTime());
					temp.add(Calendar.DATE, -1);
					eventDetails.setEndDate(temp.getTime());
					eventDetails.setStartTime(event.getStartTime());
					eventDetails.setEndTime(event.getEndTime());
					eventDetails.setDow(dow);
					eventRepository.save(eventDetails);
					dow = "";
					allDays = 0;
				} else {
					start.add(Calendar.DATE, 1);
				}
			}
			if(!dow.isEmpty()) {
				eventDetails = new EventEntity();
				eventDetails.setEventName(event.getEventName());
				eventDetails.setStartDate(event.getStartDate());
				Calendar temp = Calendar.getInstance();
				temp.setTime(start.getTime());
				temp.add(Calendar.DATE, -1);
				eventDetails.setEndDate(temp.getTime());
				eventDetails.setStartTime(event.getStartTime());
				eventDetails.setEndTime(event.getEndTime());
				eventDetails.setDow(dow);
				eventRepository.save(eventDetails);
			}
			serviceResponse.setServiceError("");
			serviceResponse.setServiceResponse("Event inseted Successfully!!!");
			serviceResponse.setServiceStatus(ServiceResponse.STATUS_SUCCESS);
			return serviceResponse;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Error in EventService, method = saveAllDaysEvent " + e.toString());
			serviceResponse.setServiceError(e.toString());
			serviceResponse.setServiceResponse("Something went wrong");
			serviceResponse.setServiceStatus(ServiceResponse.STATUS_FAIL);
			return serviceResponse;
		}

	}
	public ServiceResponse saveWeekendEvent(EventEntity event) {

		ServiceResponse serviceResponse = new ServiceResponse();
		log.info("EventService, method = saveWeekendEvent");
		try {
			Calendar start = Calendar.getInstance();
			start.setTime(event.getStartDate());
			Calendar end = Calendar.getInstance();
			end.setTime(event.getEndDate());
			int weekend = 0;
			EventEntity eventDetails = new EventEntity();
			String dow = "";
			boolean firstWeekEvent = false;
			while( !start.after(end)){ 
				if(weekend == 0) {
					event.setStartDate(start.getTime());
				}
				if(start.getTime().getDay() == 6 && firstWeekEvent == false && !dow.isEmpty()) {
					eventDetails = new EventEntity();
					eventDetails.setEventName(event.getEventName());
					eventDetails.setStartDate(event.getStartDate());
					Calendar temp = Calendar.getInstance();
					temp.setTime(start.getTime());
					temp.add(Calendar.DATE, -1);
					eventDetails.setEndDate(temp.getTime());
					eventDetails.setStartTime(event.getStartTime());
					eventDetails.setEndTime(event.getEndTime());
					eventDetails.setDow(dow);
					eventRepository.save(eventDetails);
					dow = "";
					weekend = 0;
					firstWeekEvent = true;
				} else if ((start.getTime().getDay() == 6 || start.getTime().getDay() == 0) && weekend < 2) {
					dow = dow + " " + days[start.getTime().getDay()];
					weekend++;
					start.add(Calendar.DATE, 1);
				} else if(weekend == 2){
					eventDetails = new EventEntity();
					eventDetails.setEventName(event.getEventName());
					eventDetails.setStartDate(event.getStartDate());
					eventDetails.setStartTime(event.getStartTime());
					eventDetails.setEndTime(event.getEndTime());
					Calendar temp = Calendar.getInstance();
					temp.setTime(start.getTime());
					temp.add(Calendar.DATE, -1);
					eventDetails.setEndDate(temp.getTime());
					eventDetails.setDow(dow);
					eventRepository.save(eventDetails);
					dow = "";
					weekend = 0;
				} else {
					start.add(Calendar.DATE, 1);
				}
			}
			if(!dow.isEmpty()) {
				eventDetails = new EventEntity();
				eventDetails.setEventName(event.getEventName());
				eventDetails.setStartDate(event.getStartDate());
				Calendar temp = Calendar.getInstance();
				temp.setTime(start.getTime());
				temp.add(Calendar.DATE, -1);
				eventDetails.setEndDate(temp.getTime());
				eventDetails.setStartTime(event.getStartTime());
				eventDetails.setEndTime(event.getEndTime());
				eventDetails.setDow(dow);
				eventRepository.save(eventDetails);
			}
			serviceResponse.setServiceError("");
			serviceResponse.setServiceResponse("Event inseted Successfully!!!");
			serviceResponse.setServiceStatus(ServiceResponse.STATUS_SUCCESS);
			return serviceResponse;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Error in EventService, method = saveWeekdaysEvent " + e.toString());
			serviceResponse.setServiceError(e.toString());
			serviceResponse.setServiceResponse("Something went wrong");
			serviceResponse.setServiceStatus(ServiceResponse.STATUS_FAIL);
			return serviceResponse;
		}

	}
	public ServiceResponse saveWeekdaysEvent(EventEntity event) {
		ServiceResponse serviceResponse = new ServiceResponse();
		log.info("EventService, method = submitEvent");
		try {
			Calendar start = Calendar.getInstance();
			start.setTime(event.getStartDate());
			Calendar end = Calendar.getInstance();
			end.setTime(event.getEndDate());
			int weekdays = 0;
			EventEntity eventDetails = new EventEntity();
			String dow = "";
			boolean firstWeekEvent = false;
			while( !start.after(end)){ 
				if(weekdays == 0) {
					event.setStartDate(start.getTime());
				}
				if(start.getTime().getDay() == 0 && firstWeekEvent == false && !dow.isEmpty()) {
					eventDetails = new EventEntity();
					eventDetails.setEventName(event.getEventName());
					eventDetails.setStartDate(event.getStartDate());
					Calendar temp = Calendar.getInstance();
					temp.setTime(start.getTime());
					temp.add(Calendar.DATE, -2);
					eventDetails.setEndDate(temp.getTime());
					eventDetails.setStartTime(event.getStartTime());
					eventDetails.setEndTime(event.getEndTime());
					eventDetails.setDow(dow);
					eventRepository.save(eventDetails);
					dow = "";
					weekdays = 0;
					firstWeekEvent = true;
				} else if (start.getTime().getDay() <= 5 && start.getTime().getDay() >= 1 && weekdays < 5 ) {
					dow = dow + " " + days[start.getTime().getDay()];
					weekdays++;
					start.add(Calendar.DATE, 1);
				} else if(weekdays == 5){
					eventDetails = new EventEntity();
					eventDetails.setEventName(event.getEventName());
					eventDetails.setStartDate(event.getStartDate());
					Calendar temp = Calendar.getInstance();
					temp.setTime(start.getTime());
					temp.add(Calendar.DATE, - 1);
					eventDetails.setEndDate(temp.getTime());
					eventDetails.setStartTime(event.getStartTime());
					eventDetails.setEndTime(event.getEndTime());
					eventDetails.setDow(dow);
					eventRepository.save(eventDetails);
					dow = "";
					weekdays = 0;
				} else {
					start.add(Calendar.DATE, 1);
				}

			}
			if(!dow.isEmpty()) {
				eventDetails = new EventEntity();
				eventDetails.setEventName(event.getEventName());
				eventDetails.setStartDate(event.getStartDate());
				Calendar temp = Calendar.getInstance();
				temp.setTime(start.getTime());
				if(start.getTime().getDay() == 0) {
					temp.add(Calendar.DATE, -2);
				} else {
					temp.add(Calendar.DATE, -1);
				}
				eventDetails.setEndDate(temp.getTime());
				eventDetails.setStartTime(event.getStartTime());
				eventDetails.setEndTime(event.getEndTime());
				eventDetails.setDow(dow);
				eventRepository.save(eventDetails);
			}

			serviceResponse.setServiceError("");
			serviceResponse.setServiceResponse("Event inseted Successfully!!!");
			serviceResponse.setServiceStatus(ServiceResponse.STATUS_SUCCESS);
			return serviceResponse;
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Error in EventService, method = saveWeekdaysEvent " + e.toString());
			serviceResponse.setServiceError(e.toString());
			serviceResponse.setServiceResponse("Something went wrong");
			serviceResponse.setServiceStatus(ServiceResponse.STATUS_FAIL);
			return serviceResponse;
		}

	}
	
	public ServiceResponse deleteEvent(int eventId) {
		ServiceResponse serviceResponse = new ServiceResponse();
		EventEntity event = new EventEntity();
		try {
			event = eventRepository.retriveEventById(eventId);
			eventRepository.delete(event);
			serviceResponse.setServiceError("");
			serviceResponse.setServiceResponse("Event Deleted successfully");
			serviceResponse.setServiceStatus(ServiceResponse.STATUS_SUCCESS);
			return serviceResponse;

		} catch (Exception e) {
			serviceResponse.setServiceError(e.toString());
			serviceResponse.setServiceResponse("Something went wrong");
			serviceResponse.setServiceStatus(ServiceResponse.STATUS_FAIL);
			log.error("Error in EventService, method = deleteEvent " + e.toString());
			return serviceResponse;
		} 

	}
	
}

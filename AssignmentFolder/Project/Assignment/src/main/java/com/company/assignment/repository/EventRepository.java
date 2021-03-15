package com.company.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.company.assignment.models.EventEntity;

public interface EventRepository  extends JpaRepository<EventEntity, Integer>{
	@Query(value="select id, eventName, startDate, endDate, startTime, endTime, dow from event ",nativeQuery=true)
	List retriveAllEvent();
	
	@Query(value="From EventEntity where Id=:eventId")
	EventEntity retriveEventById(@Param("eventId") int eventId);
}

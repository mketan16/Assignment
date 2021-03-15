package com.company.assignment.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="event")
public class EventEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;
	
	@Column(name ="eventName")
	private String eventName;
	
	@Temporal(TemporalType.DATE) 
	@Column(name="startDate")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date startDate;
	
	@Temporal(TemporalType.DATE) 
	@Column(name="endDate")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date endDate;
	
	@Column(name="startTime")
	private String startTime;
	
	@Column(name="endTime")
	private String endTime;
	
	@Column(name ="dow")
	private String dow;
	
	@Transient
	private int dowType;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDow() {
		return dow;
	}

	public void setDow(String dow) {
		this.dow = dow;
	}

	public int getDowType() {
		return dowType;
	}

	public void setDowType(int dowType) {
		this.dowType = dowType;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "EventEntity [Id=" + Id + ", eventName=" + eventName + ", startDate=" + startDate + ", endDate="
				+ endDate + ", startTime=" + startTime + ", endTime=" + endTime + ", dow=" + dow + ", dowType="
				+ dowType + "]";
	}

}

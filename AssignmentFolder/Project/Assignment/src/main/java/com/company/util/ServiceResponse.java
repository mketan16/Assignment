package com.company.util;
public class ServiceResponse {

	private Object serviceResponse;
	private String serviceStatus;
	private Object serviceError;
	
	public static final String STATUS_FAIL="Fail";
	public static final String STATUS_SUCCESS="Success";

	
	public Object getServiceResponse() {
		return serviceResponse;
	}
	public void setServiceResponse(Object serviceResponse) {
		this.serviceResponse = serviceResponse;
	}
	public String getServiceStatus() {
		if(serviceStatus==null)
			return "";
		return serviceStatus;
	}
	public void setServiceStatus(String serviceStatus) {
	 
		this.serviceStatus = serviceStatus;
	}
	public Object getServiceError() {
		if(serviceError==null)
			return "";
		return serviceError;
	}
	public void setServiceError(Object serviceError) {
		this.serviceError = serviceError;
	}
}

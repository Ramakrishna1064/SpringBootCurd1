package com.example.demo.model;

import java.util.List;

public class EmployeeParentObject {
	
	private boolean status;
	private String message;
	private List<EmployeeChildObject>employeeChild;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<EmployeeChildObject> getEmployeeChild() {
		return employeeChild;
	}
	public void setEmployeeChild(List<EmployeeChildObject> employeeChild) {
		this.employeeChild = employeeChild;
	}

}

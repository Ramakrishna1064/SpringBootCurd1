package com.example.demo.model;
import java.util.List;

import com.example.demo.dto.EmployeeRecord;

public class EmployeeResponseObject {

	private boolean status;
	private List<EmployeeRecord>employeeList;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<EmployeeRecord> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<EmployeeRecord> employeeList) {
		this.employeeList = employeeList;
	}

}

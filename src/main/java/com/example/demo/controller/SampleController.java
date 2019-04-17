package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.EmployeeRecord;
import com.example.demo.dto.StatesDTO;
import com.example.demo.dto.UsersDTO;
import com.example.demo.model.EmployeeChildObject;
import com.example.demo.model.EmployeeParentObject;
import com.example.demo.model.EmployeeResponseObject;
import com.example.demo.model.States;
import com.example.demo.model.StatusObject;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.RegistrationRepository;
import com.example.demo.repository.StatesRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.util.Conversions;

@RestController
public class SampleController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	StatesRepository statesRepository;
	@Autowired
	RegistrationRepository registrationRepository;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/allUsers")
	@ResponseBody
	public EmployeeResponseObject getAllUsers(){
		EmployeeResponseObject employeeResponseObject = new EmployeeResponseObject();
		List<EmployeeRecord>employeeList = employeeRepository.findAll();
		employeeResponseObject.setStatus(true);
		employeeResponseObject.setEmployeeList(employeeList);
		return employeeResponseObject;
	}
	

	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addUSer", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public StatusObject addUSer(@RequestBody EmployeeRecord employeeRecord) {
		System.out.println("Request object is---->"+Conversions.ObjectToJson(employeeRecord));
		StatusObject statusObject = new StatusObject();
		EmployeeRecord record = employeeRepository.save(employeeRecord);
		if(record.getId()!=-1) {
			statusObject.setStatus(true);
			statusObject.setMessage("User add Successfully");
		}else {
			statusObject.setStatus(false);
			statusObject.setMessage("Fail to add user");
		}
		return statusObject;
	}
	

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUserEmail", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public EmployeeRecord getEmployeeById(@RequestBody EmployeeRecord employeeRecord) {
		System.out.println("Request object is---->"+Conversions.ObjectToJson(employeeRecord));
		EmployeeRecord record = employeeRepository.findByEmail(employeeRecord.getEmail());
		return record;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public StatusObject updateEmployee(@RequestBody EmployeeRecord employeeRecord) {
		System.out.println("Request object is---->"+Conversions.ObjectToJson(employeeRecord));
		StatusObject statusObject = new StatusObject();
		EmployeeRecord record = employeeRepository.findOne(employeeRecord.getId());
		if(record!=null) {
			record.setName(employeeRecord.getName());
			record.setEmail(employeeRecord.getEmail());
			employeeRepository.save(record);
			statusObject.setStatus(true);
			statusObject.setMessage("User updated Successfully");
		}else {
			statusObject.setStatus(false);
			statusObject.setMessage("Fail to update user");
		}
		return statusObject;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deleteUserId", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public StatusObject deleteEmployeeById(@RequestBody EmployeeRecord employeeRecord) {
		StatusObject statusObject = new StatusObject();
		EmployeeRecord record = employeeRepository.findOne(employeeRecord.getId());
		if(record!=null) {
			employeeRepository.delete(employeeRecord.getId());
			statusObject.setStatus(true);
			statusObject.setMessage("User delete Successfully");
		}else {
			statusObject.setStatus(false);
			statusObject.setMessage("Fail to delete user");
		}
		return statusObject;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/allUsersWithStates")
	@ResponseBody
	public EmployeeParentObject getAllUsersAithStates(){
				
		EmployeeParentObject employeeParentObject = new EmployeeParentObject();
		  //Get List of Employees 
		List<EmployeeRecord>employeeList = employeeRepository.findAll();
		List<EmployeeChildObject>employeeChild = new ArrayList<>();
		for(EmployeeRecord employee:employeeList) {
			EmployeeChildObject employeeChildObject = new EmployeeChildObject();
			employeeChildObject.setId(employee.getId());
			employeeChildObject.setName(employee.getName());
			employeeChildObject.setEmail(employee.getEmail());
			
			//Get List of States with Employee ID
			List<StatesDTO>statesList = statesRepository.findByUserid(employee.getId());
			List<States>stateslist = new ArrayList<>();
			for(StatesDTO statesDTO:statesList) {
				States states = new States();
				states.setStateid(statesDTO.getStateid());
				states.setStateName(statesDTO.getStatename());
				stateslist.add(states);
			}
			employeeChildObject.setStates(stateslist);
			employeeChild.add(employeeChildObject);
		}
		employeeParentObject.setStatus(true);
		employeeParentObject.setMessage("User list");
		employeeParentObject.setEmployeeChild(employeeChild);
		return employeeParentObject;
	}
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public StatusObject registerUSer(@RequestBody UsersDTO usersDTO) {
		UsersDTO user = new UsersDTO();
		user.setUsername(usersDTO.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(usersDTO.getPassword()));
		StatusObject statusObject = new StatusObject();
		UsersDTO record = registrationRepository.save(user);
		if(record.getId()!=-1) {
			statusObject.setStatus(true);
			statusObject.setMessage("User register Successfully");
		}else {
			statusObject.setStatus(false);
			statusObject.setMessage("Fail to register user");
		}
		return statusObject;
	}
	
//	/**
//	 * 
//	 * @return
//	 */
//	@RequestMapping("/allUsersWithStates")
//	@ResponseBody
//	public void getAllUsersAithStates(){
//		employeeService.getAllUsersWithStates();
//	}
	
}

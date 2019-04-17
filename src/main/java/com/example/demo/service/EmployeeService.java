package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.dto.UsersDTO;
import com.example.demo.repository.LoginRepository;
import com.example.demo.util.Conversions;

@Service(value = "employeeService")
public class EmployeeService implements UserDetailsService {

	@Autowired
	EmployeeDao employeeDao;
	
	@Autowired
	private LoginRepository loginRepository;


//	/**
//	 * 
//	 * @return
//	 */
//	@Transactional
//	public List<?> getAllUsersWithStates(){
//		 List<?>statesList1 = employeeDao.getListOfStates(1);
//		 System.out.println("States list---->"+Conversions.getGson().toJson(statesList1));
//		 return statesList1;
//	}
	
	
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UsersDTO user = loginRepository.findByUsername(userId);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

}

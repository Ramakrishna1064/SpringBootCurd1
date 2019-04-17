package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.dto.UsersDTO;

public interface RegistrationRepository extends JpaRepository<UsersDTO, Integer>{
	  
}

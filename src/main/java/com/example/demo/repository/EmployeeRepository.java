package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.dto.EmployeeRecord;

public interface EmployeeRepository extends JpaRepository<EmployeeRecord, Integer> {  
	EmployeeRecord findByEmail(String email);
}  

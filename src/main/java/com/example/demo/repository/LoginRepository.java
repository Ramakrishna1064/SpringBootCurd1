package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.dto.UsersDTO;

public interface LoginRepository extends JpaRepository<UsersDTO, Integer>{
   UsersDTO findByUsername(String username);
}

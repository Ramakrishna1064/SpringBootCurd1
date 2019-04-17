package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.dto.StatesDTO;

public interface StatesRepository extends JpaRepository<StatesDTO, Integer>{
   List<StatesDTO>findByUserid(int id);
}
package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootCurd1Application {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCurd1Application.class, args);
	}

//	@Bean
//	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
//		return hemf.getSessionFactory();
//	}
}

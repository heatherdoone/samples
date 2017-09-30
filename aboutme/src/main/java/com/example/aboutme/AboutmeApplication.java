package com.example.aboutme;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AboutmeApplication {
	static final Logger logger = LoggerFactory.getLogger( AboutmeApplication.class );
	
	public static void main(String[] args) {
		logger.info("Getting ready to start About Me!");
		
		ApplicationContext context = SpringApplication.run(AboutmeApplication.class, args);
		//System.out.println("Let's inspect the beans provided by Spring Boot:");
        
//        String[] beanNames = context.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
//		
	}
}

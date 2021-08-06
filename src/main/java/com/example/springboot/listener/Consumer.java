package com.example.springboot.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import com.example.springboot.resource.Student;
import com.example.springboot.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Consumer {

	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	@Autowired
	private StudentService studentService;
	
	@SqsListener("second_sqs_queue")
	public void receiveMessage(String stringJson) {

		logger.info("Message Received using SQS Listner start: " + stringJson);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Student student = mapper.readValue(stringJson, Student.class);
			studentService.saveStudent(student);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("Message Received using SQS Listner end: " + stringJson);
	}
}

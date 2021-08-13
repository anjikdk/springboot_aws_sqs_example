package com.example.springboot.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import com.example.springboot.resource.Student;
import com.example.springboot.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Consumer {

	@Autowired
	private StudentService studentService;
	
	@SqsListener("second_sqs_queue")
	public void receiveMessage(String stringJson) {
		

		log.info("Message Received using SQS Listner start: " + stringJson);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Student student = mapper.readValue(stringJson, Student.class);
			//Student student = stringJson.getPayload();
			studentService.saveStudent(student);
			log.info("Data inserted/updated successfully....");
		} catch (Exception e) {
			log.info("While converting from json to Object throwing exception....");
			e.printStackTrace();
		}
		
		log.info("Message Received using SQS Listner end: " + stringJson);
	}
}

package com.example.springboot.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entity.StudentEntity;
import com.example.springboot.repository.StudentRepository;
import com.example.springboot.resource.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Student saveStudent(Student stdResuorce) {

		log.info("Entered into saveEmp() method");
		StudentEntity studentEntity = new StudentEntity();
		BeanUtils.copyProperties(stdResuorce, studentEntity);

		studentRepository.save(studentEntity);
		
		return stdResuorce;
	}
}

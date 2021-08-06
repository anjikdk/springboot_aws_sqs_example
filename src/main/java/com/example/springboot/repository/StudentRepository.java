package com.example.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.entity.StudentEntity;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long>
{

}

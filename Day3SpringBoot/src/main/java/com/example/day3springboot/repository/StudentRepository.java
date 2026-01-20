package com.example.day3springboot.repository;

import com.example.day3springboot.model.StudentModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository <StudentModel,String>{

}
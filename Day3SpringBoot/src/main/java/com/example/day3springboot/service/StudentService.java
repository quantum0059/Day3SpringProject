package com.example.day3springboot.service;

import com.example.day3springboot.exception.StudentNotFoundException;
import com.example.day3springboot.model.StudentModel;
import com.example.day3springboot.dto.*;
import com.example.day3springboot.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService{
  private StudentRepository repository;
  public StudentService(StudentRepository repository){

    this.repository = repository;
  }
//
//  public StudentModel addStudent(StudentModel student){
//    return repository.save(student);
//  }

  public StudentResponseDto addStudent(StudentRequestDto dto){
    StudentModel student = new StudentModel();

    student.setName(dto.getName());
    student.setAge(dto.getAge());
    student.setEmail(dto.getEmail());

    StudentModel saved = repository.save(student);

    return new StudentResponseDto(
            saved.getId(),
            saved.getName(),
            saved.getAge(),
            saved.getEmail()
    );

  }


  //Display all student

  public List<StudentResponseDto> getStudents(){

    return repository.findAll()
            .stream()
            .map( s -> new StudentResponseDto(
            s.getId(),
            s.getName(),
            s.getAge(),
            s.getEmail()
    )).toList();
  }

  //update students

  public StudentResponseDto updateStudent(String id, StudentRequestDto student){
    StudentModel existingStudent = repository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(
                    "Student not found with id: " + id
            ));


    existingStudent.setName(student.getName());
    existingStudent.setAge(student.getAge());
    existingStudent.setEmail(student.getEmail());

    StudentModel saved = repository.save(existingStudent);
     return  new StudentResponseDto(
             saved.getId(),
             saved.getName(),
             saved.getAge(),
             saved.getEmail()
     );
  }

  public void deleteStudent(String id){
    StudentModel existingStudent = repository.findById(id).orElseThrow(() -> new StudentNotFoundException("No Student found"));
    repository.deleteById(id);
  }
}

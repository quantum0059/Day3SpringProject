package com.example.day3springboot.service;

import com.example.day3springboot.model.StudentModel;
import com.example.day3springboot.dto.*;
import com.example.day3springboot.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public List<StudentModel> getStudents(){
    return repository.findAll();
  }

  //update students

  public StudentModel updateStudent(String id, StudentModel student){
    StudentModel existingStudent = repository.findById(id).orElseThrow(() -> new RuntimeException("No Student found"));

    existingStudent.setName(student.getName());
    existingStudent.setAge(student.getAge());
    existingStudent.setEmail(student.getEmail());

    return repository.save(existingStudent);


  }

  public void deleteStudent(String id){
    StudentModel existingStudent = repository.findById(id).orElseThrow(() -> new RuntimeException("No Student found"));
    repository.deleteById(id);
  }
}

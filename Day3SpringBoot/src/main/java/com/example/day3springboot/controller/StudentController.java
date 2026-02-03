package com.example.day3springboot.controller;

import com.example.day3springboot.model.StudentModel;
import com.example.day3springboot.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.example.day3springboot.dto.*;

import java.util.List;


@RestController
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service){
        this.service = service;
    }

    //create api

    @PostMapping("/add-student")
    public StudentResponseDto addStudent(@Valid @RequestBody StudentRequestDto dto){
        return service.addStudent(dto);
    }

    @GetMapping("/get-students")
    public List<StudentResponseDto> getStudents(){

        return service.getStudents();
    }

    @PutMapping("/update/{id}")
    public StudentResponseDto updateStudent(@PathVariable String id, @Valid @RequestBody StudentRequestDto student){
        return service.updateStudent(id, student);
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudent(@PathVariable String id){
        service.deleteStudent(id);
        return "student deleted successfully";
    }
}
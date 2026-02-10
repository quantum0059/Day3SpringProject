package com.example.day3springboot.controller;

import com.example.day3springboot.model.StudentModel;
import com.example.day3springboot.service.StudentService;
import jakarta.validation.Valid;
import com.example.day3springboot.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;
import com.example.day3springboot.dto.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class StudentController {

    private final StudentService service;
    private final JwtUtil jwtUtil;

    public StudentController(StudentService service,JwtUtil jwtUtil){
        this.service=service;
        this.jwtUtil=jwtUtil;
    }

    private void checkToken(String authHeader){
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            throw new RuntimeException("Invalid token");
        }
        String token=authHeader.substring(7);
        jwtUtil.validateTokenAndGetEmail(token);
    }

    //create api

    @PostMapping("/add-student")
    public StudentResponseDto addStudent(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody StudentRequestDto dto){
        checkToken(authHeader);
        return service.addStudent(dto);
    }

    @GetMapping("/get-students")
    public List<StudentResponseDto> getStudents(@RequestHeader("Authorization") String authHeader){
        checkToken(authHeader);
        return service.getStudents();
    }

    @PutMapping("/update/{id}")
    public StudentResponseDto updateStudent(@RequestHeader("Authorization") String authHeader , @PathVariable String id, @Valid @RequestBody StudentRequestDto student){
        checkToken(authHeader);
        return service.updateStudent(id, student);
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudent(@RequestHeader("Authorization") String authHeader,@PathVariable String id){
        checkToken(authHeader);
        service.deleteStudent(id);
        return "student deleted successfully";
    }

   @PatchMapping("/studentPatch/{id}")
    public StudentResponseDto updateStudentField(
            @PathVariable String id,
            @RequestBody Map<String, Object> updates) {

        return service.patchStudent(id, updates);
    }

}
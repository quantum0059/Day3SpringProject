package com.example.day3springboot.repository;

import com.example.day3springboot.model.UserModel;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository <UserModel, String>{

     Optional<UserModel> findByEmail(String email);
}

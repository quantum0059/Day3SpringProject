package com.example.day3springboot.dto;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StudentRequestDto {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Min(value = 5, message="Age cannot be less than 5")
    @Max(value = 90, message="Age cannot be greater than 90")
    private int age;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;

}

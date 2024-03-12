package com.shuaibu.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Model
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    
    private Integer id;

    @NotEmpty(message = "Name is required")
    private String name;
    
    @Email(message = "Invalid email address")
    private String email;
    
    @Min(value = 18, message = "Age must be at least 18")
    private Integer age;

}
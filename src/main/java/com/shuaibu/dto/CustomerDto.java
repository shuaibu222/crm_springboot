package com.shuaibu.dto;

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
    private String name;
    private String email;
    private Integer age;

}
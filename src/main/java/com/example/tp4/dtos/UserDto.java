package com.example.tp4.dtos;

import com.example.tp4.utils.UserType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String name;
    private String address;
    private UserType userType;
}

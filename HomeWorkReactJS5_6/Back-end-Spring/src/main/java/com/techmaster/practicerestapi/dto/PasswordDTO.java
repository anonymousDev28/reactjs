package com.techmaster.practicerestapi.dto;

import lombok.Data;

@Data
public class PasswordDTO {
//    private int id;
    private String oldPassword;
    private String newPassword;
}

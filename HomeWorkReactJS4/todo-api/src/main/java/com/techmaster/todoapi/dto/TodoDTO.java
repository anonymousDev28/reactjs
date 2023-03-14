package com.techmaster.todoapi.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
    private int id;
    @NotEmpty(message = "title is required")
    private String title;
    private Boolean status;
}

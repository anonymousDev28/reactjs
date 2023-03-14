package com.techmaster.todoapi.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {
    private Integer id;
    private String title;
    private Boolean status;
//    private LocalDateTime createdAt;
//    private String level;
}

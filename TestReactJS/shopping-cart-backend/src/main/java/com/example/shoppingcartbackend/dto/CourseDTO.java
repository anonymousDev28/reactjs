package com.example.shoppingcartbackend.dto;

import com.example.shoppingcartbackend.model.Category;

import java.util.List;

public class CourseDTO {
    private Integer id;
    private String name;
    private String description;
    private String type;
    private List<Category> categories;
    private String thumbnail;
    private Integer price;
    private Double rating;
    private Integer userId;
}

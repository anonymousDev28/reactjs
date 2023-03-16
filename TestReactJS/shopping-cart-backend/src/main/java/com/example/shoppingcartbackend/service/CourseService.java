package com.example.shoppingcartbackend.service;

import com.example.shoppingcartbackend.dto.CourseDTO;
import com.example.shoppingcartbackend.model.Course;
import com.example.shoppingcartbackend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    public Course getCourseById(int id){
        return courseRepository.findById(id);
    }
}

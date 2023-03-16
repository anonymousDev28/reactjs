package com.example.shoppingcartbackend.repository;

import com.example.shoppingcartbackend.db.CourseDB;
import com.example.shoppingcartbackend.model.Course;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository {
    public Course findById(int id) {
        return CourseDB.courses.stream().filter(course -> course.getId() == id).findFirst().orElse(null);
    }
}

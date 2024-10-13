package com.sample.genzschool.repository;

import com.sample.genzschool.Model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursesRepository extends JpaRepository<Courses,Integer> {
    List<Courses> findByOrderByNameDesc(); //derived Query method
    List<Courses> findByOrderByName();
}

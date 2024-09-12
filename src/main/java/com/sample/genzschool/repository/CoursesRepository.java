package com.sample.genzschool.repository;

import com.sample.genzschool.Model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursesRepository extends JpaRepository<Courses,Integer> {
}

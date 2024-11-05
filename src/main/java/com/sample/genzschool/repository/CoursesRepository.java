package com.sample.genzschool.repository;

import com.sample.genzschool.Model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RepositoryRestResource(path = "courses")
public interface CoursesRepository extends JpaRepository<Courses,Integer> {
    List<Courses> findByOrderByNameDesc(); //derived Query method
    List<Courses> findByOrderByName();
}

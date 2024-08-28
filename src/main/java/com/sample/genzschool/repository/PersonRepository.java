package com.sample.genzschool.repository;

import com.sample.genzschool.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    /* Derived Query Method */
    Person findByEmail(String email);
}

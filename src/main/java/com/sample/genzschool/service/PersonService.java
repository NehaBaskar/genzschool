package com.sample.genzschool.service;

import com.sample.genzschool.Constants.GenZSchoolConstants;
import com.sample.genzschool.Model.Person;
import com.sample.genzschool.Model.Roles;
import com.sample.genzschool.repository.PersonRepository;
import com.sample.genzschool.repository.RolesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(Person person) {

        boolean isSaved = false;
        Roles role = rolesRepository.getByRoleName(GenZSchoolConstants.STUDENT_ROLE);
        person.setRoles(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        if(null != person && person.getPersonId() > 0){
            isSaved = true;
        }else{
            isSaved = false;
        }
        return isSaved;
    }
}

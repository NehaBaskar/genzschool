package com.sample.genzschool.controllers;

import com.sample.genzschool.Model.Courses;
import com.sample.genzschool.Model.GenZClass;
import com.sample.genzschool.Model.Person;
import com.sample.genzschool.repository.CoursesRepository;
import com.sample.genzschool.repository.GenZClassRepository;
import com.sample.genzschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    GenZClassRepository genZClassRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CoursesRepository coursesRepository;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model){
        List<GenZClass> genZClasses = genZClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("genZClasses", genZClasses);
        modelAndView.addObject("genZClass", new GenZClass());
        return modelAndView;
    }


    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("genZClass") GenZClass genZClass){
        genZClassRepository.save(genZClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model,@RequestParam int id){
        Optional<GenZClass> optionalGenZClass = genZClassRepository.findById(id);
        for(Person person : optionalGenZClass.get().getPersons() ){
            person.setGenZClass(null);
            personRepository.save(person);
        }
        genZClassRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error",required = false)String error){
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("students.html");
        Optional<GenZClass> genZClass = genZClassRepository.findById(classId);
        modelAndView.addObject("genZClass", genZClass.get());
        modelAndView.addObject("person", new Person());
        if(error != null){
            errorMessage = "Invalid email id !!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        session.setAttribute("genZClass", genZClass.get());
        return  modelAndView;
    }


    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        GenZClass genZClass = (GenZClass) session.getAttribute("genZClass");
        Person personEntity = personRepository.findByEmail(person.getEmail());
        if(personEntity == null || !(personEntity.getPersonId()>0)){
            modelAndView.setViewName("redirect:/admin/displayStudents?classId="+genZClass.getClassId()+
                    "&error=true");
            return modelAndView;
        }
        personEntity.setGenZClass(genZClass);
        personRepository.save(personEntity);
        genZClass.getPersons().add(personEntity);
        genZClassRepository.save(genZClass);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId="+genZClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session){
        GenZClass genZClass = (GenZClass) session.getAttribute("genZClass");
        Optional<Person> person = personRepository.findById(personId);
        person.get().setGenZClass(null);
        genZClass.getPersons().remove(person.get());
        GenZClass newGenZClass = genZClassRepository.save(genZClass);
        session.setAttribute("genZClass", newGenZClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId="+genZClass.getClassId());
        return modelAndView;
    }


    @RequestMapping("/displayCourses")
    public ModelAndView displayCourses(Model model){
        List<Courses> courses = coursesRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("courses_secure.html");
        modelAndView.addObject("courses", courses);
        modelAndView.addObject("course", new Courses());
        return modelAndView;
    }

    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse(Model model, @ModelAttribute("course")Courses course){
        ModelAndView modelAndView = new ModelAndView();
        coursesRepository.save(course);
        modelAndView.setViewName("redirect:/admin/displayCourses");
        return  modelAndView;
    }

    @GetMapping("/viewStudents")
    public ModelAndView viewStudents(Model model, @RequestParam int id){
        ModelAndView modelAndView = new ModelAndView("courses_students.html");
        Optional<Courses> courses = coursesRepository.findById(id);
        modelAndView.addObject("courses", courses.get());
        modelAndView.addObject("person", new Person());
        return modelAndView;
    }

}

package com.sample.genzschool.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Slf4j
@Entity
@Table(name = "class")
@Getter
@Setter
public class GenZClass extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name ="native", strategy = "native" )
    private int classId;
    
    @NotBlank(message = "class name should not be blank")
    @Size(min = 3, message = "class name should be atleast 3 chars long")
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "genZClass",cascade = CascadeType.PERSIST, targetEntity = Person.class)
    private Set<Person> persons;
}

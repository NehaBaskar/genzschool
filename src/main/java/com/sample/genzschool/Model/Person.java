package com.sample.genzschool.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sample.genzschool.customAnnotations.FieldsValueMatch;
import com.sample.genzschool.customAnnotations.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "Passwords dont Match !!"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Emails dont match!!"
        )
})
public class Person extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    private int personId;

    @NotBlank(message = "Name should not be blank")
    @Size(min = 3, message = "Name must be atleast three characters long")
    private String name;

    @NotBlank(message = "Mobile number should not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits long")
    private String mobileNumber;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Please provide valid email address")
    private String email;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Please provide valid email address")
    @Transient
    @JsonIgnore
    private String confirmEmail;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 5, message = "Password must be atleast five characters long")
    @PasswordValidator
    @JsonIgnore
    private String pwd;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 5, message = "Password must be atleast five characters long")
    @Transient
    @JsonIgnore
    private String confirmPwd;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private Roles roles;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
    private Address address;


    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = true)
    private GenZClass genZClass;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "person_courses",
            joinColumns = {
            @JoinColumn ( name = "person_id", referencedColumnName = "personId")},
            inverseJoinColumns = {
            @JoinColumn ( name = "course_id", referencedColumnName = "courseId")}
    )
    private Set<Courses> courses = new HashSet<>();

}

package com.sample.genzschool.Model;

import com.sample.genzschool.customAnnotations.FieldsValueMatch;
import com.sample.genzschool.customAnnotations.PasswordValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
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
    private String confirmEmail;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 5, message = "Password must be atleast five characters long")
    @PasswordValidator
    private String pwd;

    @NotBlank(message = "Password should not be blank")
    @Size(min = 5, message = "Password must be atleast five characters long")
    @Transient
    private String confirmPwd;

}

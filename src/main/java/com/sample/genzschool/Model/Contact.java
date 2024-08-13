package com.sample.genzschool.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name="contact_msg")
public class Contact extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name="contact_id")
    private int contactId;

    @NotBlank(message = "Name should not be blank")
    @Size(min = 3, message = "Name must be atleast 3 characters long")
    private String name;

    @NotBlank(message = "Mail should not be blank")
    @Email(message = "Email address should be valid")
    private String email;

    @NotBlank(message = "subject should not be blank")
    @Size(min = 5, message = "Subject must be atleast 5 characters long")
    private String subject;

    @NotBlank(message = "Mobile number should not be blank")
    @Pattern(regexp = "^$|[0-9]{10}", message="Mobile number must be 10 digits")
    @Column(name="mobile_num")
    private String mobileNo;

    @Size(max = 1000, message = "Message should not exceed 1000 chars")
    private String message;

    private String status;

}

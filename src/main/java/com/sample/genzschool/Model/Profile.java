package com.sample.genzschool.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Profile {

    @NotBlank(message = "Name should not be blank")
    @Size(min = 3, message = "Name must be atleast three characters long")
    private String name;

    @NotBlank(message = "Mobile number should not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits long")
    private String mobileNumber;

    @NotBlank(message = "Email should not be blank")
    @Email(message = "Please provide valid email address")
    private String email;

    @NotBlank(message = "Address should not be blank")
    @Size(min = 5, message = "Address must be atleast five characters long")
    private String address1;

    private String address2;

    @NotBlank(message = "City should not be blank")
    @Size(min = 3, message = "City must be atleast three characters long")
    private String city;

    @NotBlank(message = "State should not be blank")
    @Size(min = 2, message = "State must be atleast two characters long")
    private String state;

    @NotBlank(message = "Zip Code should not be blank")
    @Pattern(regexp = "(^$|[0-9]{5})",message = "zipcode must be 5 digits long")
    private String zipCode;
}

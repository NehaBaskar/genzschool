package com.sample.genzschool.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class Address extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int addressId;

    @NotBlank(message = "address should not be blank")
    @Size(min = 5, message = "address must atleast be 5 chracters long")
    private String address1;

    private String address2;

    @NotBlank(message = "city should not be blank")
    @Size(min = 3, message = "city should be atleast 3 characters long")
    private String city;

    @NotBlank(message = "state should not be blank")
    @Size(min = 5, message = "state should atleast be 5 characters long")
    private String state;

    @NotBlank(message = "zip code is mandatory")
    @Pattern(regexp = "(^$|[0-9]{5})", message = "zip code should be 5 digits long")
    private String zipCode;
}

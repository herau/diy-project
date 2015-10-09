package com.ds.ce.diy.dto;

import com.ds.ce.diy.domain.Company;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class UserDTO {

    @NotNull
    @NotEmpty
    private String firstname;

    @NotNull
    @NotEmpty
    private String lastname;

    @Email
    @NotNull
    private String email;

    private Company company = Company.DS;

    @NotNull
    private String personalNumber;

    @Email
    @NotNull
    private String personalEmail;

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Company getCompany() {
        return company;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }
}

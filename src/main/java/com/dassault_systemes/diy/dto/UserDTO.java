package com.dassault_systemes.diy.dto;

import org.hibernate.validator.constraints.Email;

public class UserDTO {

    private String personalNumber;

    @Email
    private String personalEmail;

    private String password;

    private String oldPassword;

    public String getPersonalNumber() {
        return personalNumber;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public String getPassword() {
        return password;
    }

    public String getOldPassword() {
        return oldPassword;
    }
}

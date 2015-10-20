package com.ds.ce.diy.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class UserPasswordDTO {

    @NotEmpty
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

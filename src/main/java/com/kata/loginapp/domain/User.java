package com.kata.loginapp.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @Size(min=5, message="Must contain at least 5 characters")
    @Pattern(regexp="([A-Za-z0-9])*", message="must contain alpha-numeric characters only")
    private String name;

    @Size(min=8, message="Must contain at least 8 characters")
    @Pattern(regexp="^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).+$", message="Must contain at least 1 number, 1 uppercase, and 1 lowercase character")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

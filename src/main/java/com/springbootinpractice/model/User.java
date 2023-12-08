package com.springbootinpractice.model;

import com.springbootinpractice.validation.Password;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class User {

    private String username;

    @Password
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}

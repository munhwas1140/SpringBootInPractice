package com.springbootinpractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:dbConfig.properties")
public class DbConfiguration {

    @Autowired
    public Environment env;

    @Override
    public String toString() {
        return "Username: " + env.getProperty("username") +
                ", Password: "  + env.getProperty("password");
    }
}

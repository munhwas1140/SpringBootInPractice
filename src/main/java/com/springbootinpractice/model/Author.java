package com.springbootinpractice.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "AUTHOR")
@Table(name = "AUTHORS")
@Getter
@ToString
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String bio;

    @ManyToMany
    @JoinTable(name = "authors_courses",
            joinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false, updatable = false)})
    private Set<Course> courses = new HashSet<>();

    public Author() {}

    public Author(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }
}

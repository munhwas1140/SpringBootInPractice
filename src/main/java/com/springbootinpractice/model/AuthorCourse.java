package com.springbootinpractice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "AUTHORS_COURSES")
@Table(name = "AUTHORS_COURSES")
public class AuthorCourse {
    @Id
    @Column(name = "author_id")
    private long authorId;
    @Column(name = "course_id")
    private long courseId;

    public AuthorCourse() {
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "AuthorCourse{" +
                "authorId=" + authorId +
                ", courseId=" + courseId +
                '}';
    }
}
package com.springbootinpractice.database;

import com.springbootinpractice.repository.AuthorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProjectionQueryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void whenCountAllCoursesThenExpectThreeCourses() {
        assertThat(authorRepository.getAuthorCourseInfo(2L)).hasSize(3);
    }


}

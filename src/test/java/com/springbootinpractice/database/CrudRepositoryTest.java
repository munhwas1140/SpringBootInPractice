package com.springbootinpractice.database;

import com.springbootinpractice.model.Course;
import com.springbootinpractice.repository.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class CrudRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void givenCreateCourseWhenLoadTheCourseThenExpectSameCourse() {
        Course course = Course.builder()
                .name("Rapid Spring Boot Application Development")
                .category("Spring")
                .rating(4)
                .description("Spring Boot gives all the power of the Spring Framework without all of the complexities")
                .build();

        Course savedCourse = courseRepository.save(course);

        assertThat(courseRepository.findById(savedCourse.getId()).get()).isEqualTo(course);
    }

    @Test
    public void givenUpdateCourseWhenLoadTheCourseThenExpectUpdatedCourse() {
        Course course = Course.builder()
                .name("Rapid Spring Boot Application Development")
                .category("Spring")
                .rating(4)
                .description("Spring Boot gives all the power of the Spring Framework without all of the complexities")
                .build();
        courseRepository.save(course);
        course.setRating(5);
        Course savedCourse = courseRepository.save(course);

        assertThat(courseRepository.findById(course.getId()).get().getRating()).isEqualTo(5);
    }

    @Test
    public void givenDeleteCourseWhenLoadTheCourseThenExpectNoCourse() {
        Course course = Course.builder()
                .name("Rapid Spring Boot Application Development")
                .category("Spring")
                .rating(4)
                .description("Spring Boot gives all the power of the Spring Framework without all of the complexities")
                .build();
        Course savedCourse = courseRepository.save(course);

        assertThat(courseRepository.findById(savedCourse.getId()).get()).isEqualTo(course);
        courseRepository.delete(course);
        assertThat(courseRepository.findById(savedCourse.getId()).isPresent()).isFalse();
    }


}

package com.springbootinpractice.database;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.springbootinpractice.model.Course;
import com.springbootinpractice.model.QCourse;
import com.springbootinpractice.projection.DescriptionOnly;
import com.springbootinpractice.repository.CourseRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class QueryDSLTest {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void cleanAll() {
        courseRepository.deleteAll();
    }

    @Test
    public void givenCoursesCreatedWhenLoadCoursesWithQueryThenExpectCorrectCourseDetails() {
        courseRepository.saveAll(getCourseList());


        QCourse course = QCourse.course;
        JPAQuery query1 = new JPAQuery(entityManager);
        query1.from(course).where(course.category.eq("Spring"));

        assertThat(query1.fetch().size()).isEqualTo(3);

        JPAQuery query2 = new JPAQuery(entityManager);
        query2.from(course).where(course.category.eq("Spring").and(course.rating.gt(3)));
        assertThat(query2.fetch().size()).isEqualTo(2);

        OrderSpecifier<Integer> descOrderSpecifier = course.rating.desc();
        assertThat(Lists.newArrayList(courseRepository.findAll(descOrderSpecifier)).get(0).getName())
                .isEqualTo("Getting Started with Spring Security DSL");
    }

    @Test
    public void givenACourseAvailableWhenGetCourseByNameThenCourseDescription() {
        courseRepository.saveAll(getCourseList());
        Iterable<DescriptionOnly> result = courseRepository.getCourseByName("Rapid Spring Boot Application Development");
        assertThat(result).extracting("description").contains("Spring Boot gives all the power of the Spring Framework without all of the complexity");
    }

    private List<Course> getCourseList() {
        Course rapidSpringBootCourse = new Course("Rapid Spring Boot Application Development", "Spring", 4,"Spring Boot gives all the power of the Spring Framework without all of the complexity");
        Course springSecurityDslCourse = new Course("Getting Started with Spring Security DSL", "Spring", 5, "Learn Spring Security DSL in easy steps");
        Course springCloudKubernetesCourse = new Course("Getting Started with Spring Cloud Kubernetes", "Spring", 3, "Master Spring Boot application deployment with Kubernetes");
        Course rapidPythonCourse = new Course("Getting Started with Python", "Python", 5, "Learn Python concepts in easy steps");
        Course gameDevelopmentWithPython = new Course("Game Development with Python", "Python", 3, "Learn Python by developing 10 wonderful games");
        Course javaScriptForAll = new Course("JavaScript for All", "JavaScript", 4, "Learn basic JavaScript syntax that can apply to anywhere");
        Course javaScriptCompleteGuide = new Course("JavaScript Complete Guide", "JavaScript", 5, "Master JavaScript with Core Concepts and Web Development");

        return Arrays.asList(rapidSpringBootCourse, springSecurityDslCourse, springCloudKubernetesCourse, rapidPythonCourse, gameDevelopmentWithPython, javaScriptForAll, javaScriptCompleteGuide);
    }
}

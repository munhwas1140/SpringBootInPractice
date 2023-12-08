package com.springbootinpractice.database;

import com.springbootinpractice.model.Course;
import com.springbootinpractice.repository.CustomizedCourseRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@DataJpaTest
// 전체 IoC컨테이너를 기동해야 할 필요 없이 DAO 게층과 관련된 빈만 있으면 충분하기 떄문에 테스트를 경량화할 수 있다.
public class CustomizedCourseRepositoryTest {

    @Autowired
    private CustomizedCourseRepository customizedCourseRepository;

    @Test
    public void givenCreateCourseWhenFindAllCoursesThenExpectOnCourse() {
        Course course = Course.builder()
                .name("Rapid Spring Boot Application Development")
                .category("Spring")
                .rating(4)
                .description("Spring Boot gives all the power of the Spring Framework without all of the complexities")
                .build();
        customizedCourseRepository.save(course);

        Assertions.assertThat(Arrays.asList(customizedCourseRepository.findAll()).size()).isEqualTo(1);
    }
}

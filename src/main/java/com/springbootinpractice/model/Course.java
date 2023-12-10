package com.springbootinpractice.model;

import io.micrometer.core.annotation.Counted;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "COURSES")
@EqualsAndHashCode
@NamedQueries({
        @NamedQuery(name = "Course.findAllByCategoryAndRating",
        query = "select c from Course c where c.category=?1 and c.rating=?2"),
        @NamedQuery(name = "Course.findAllByRating",
        query = "select c from Course c where c.rating=?1")
})
// 네임드쿼리는 비즈니스 도메인에 클래스에 본직적으로 필요하지 않은 저장/조회 관련 정보를 추가한다는 단점이 있다.
// 비즈니스 도메인 클래스와 데이터 저장/조회가 강하게 결합한다.
public class Course {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CATEGORY")
    private String category;


    @Min(value = 1, message = "A course should have a minimum of 1 rating")
    @Max(value = 5, message = "A course should have a maximum of 5 rating")
    @Column(name = "RATING")
    private int rating;

    @Column(name = "DESCRIPTION")
    private String description;

    public Course(String name, String category, int rating, String description) {
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.description = description;
    }
}

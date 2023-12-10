package com.springbootinpractice.repository;

import com.springbootinpractice.model.Course;
import com.springbootinpractice.projection.DescriptionOnly;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>, QuerydslPredicateExecutor<Course> {
    Iterable<Course> findAllByCategoryOrderByName(String category);
    boolean existsByName(String name);
    long countByCategory(String category);
    Iterable<Course> findByNameOrCategory(String name, String category);
    Iterable<Course> findByNameStartsWith(String name);
    Stream<Course> streamAllByCategory(String category);


    // NamedQuery---------------------------------------------------------------
    Iterable<Course> findAllByCategoryAndRating(String category, int rating);


    // @Query-------------------------------------------------------------------
    @Query("select c from Course c where c.category=?1")
    Iterable<Course> findAllByCategory(String category);

    @Query("select c from Course c where c.category=:category and c.rating > :rating")
    Iterable<Course> findAllByCategoryAndRatingGreaterThan(@Param("category") String category,
                                                           @Param("rating") int rating);

    @Query(value = "select * from COURSES where rating =?1", nativeQuery = true)
    Iterable<Course> findAllByRating(int rating);

    // @Query 가 조회가 아닌 수정 작업을 한다는 것을 알려준다. @Query 와 함꼐 사용될 때만 효력을 발휘한다.
    // Update 뿐만 아니라 Insert, Delete 및 다른 DDL 문도 사용가능하다.
    // @Modifying을 붙이지 않고 데이터 변경이 수반되는 쿼리를 사용하면 Exception이 발생한다.
    // 반환 타입은 int나 void여야 한다. 변환 행의 개수를 반환한다.
    @Modifying
    @Transactional // 데이터베이스에 저장된 데이터의 변경이 발생하므로 @Transactional 애너테이션을 붙여서 하나의 트랜잭션 내에서 변경 작업이 완료되도록 한다.
    @Query("update Course c set c.rating=:rating where c.name=:name")
    int updateCourseRatingByName(@Param("rating") int rating,
                                 @Param("name") String name);


    // projection
    Iterable<DescriptionOnly> getCourseByName(String name);
}

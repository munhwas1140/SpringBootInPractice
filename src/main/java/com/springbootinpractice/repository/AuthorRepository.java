package com.springbootinpractice.repository;


import com.springbootinpractice.dto.AuthorCourseDto;
import com.springbootinpractice.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long>, QuerydslPredicateExecutor<Author> {

    @Query("SELECT new com.springbootinpractice.dto.AuthorCourseDto(c.id, a.name, c.name, c.description) from AUTHOR a, COURSES c, AUTHORS_COURSES ac where a.id = ac.authorId and c.id=ac.courseId and ac.authorId=?1")
    Iterable<AuthorCourseDto> getAuthorCourseInfo(long authorId);
}

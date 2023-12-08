package com.springbootinpractice.repository;

import com.springbootinpractice.model.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizedCourseRepository extends BaseRepository<Course, Long>{
}

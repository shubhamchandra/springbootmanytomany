package com.manytomany.dao;

import com.manytomany.models.Course;
import com.manytomany.models.Student;

import java.util.List;
import java.util.Set;

public interface CourseDao {

    List <Course> getAllCourses();

    Course getCourseById(int id);

    Set<Student> getAllStudents(int id);

    void deleteCourse(int id);

    void saveCourse(Course course);

}

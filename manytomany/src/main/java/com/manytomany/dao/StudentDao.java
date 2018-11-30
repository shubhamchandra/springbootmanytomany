package com.manytomany.dao;

import com.manytomany.models.Course;
import com.manytomany.models.Student;

import java.util.List;
import java.util.Set;

public interface StudentDao {

    List <Student> getAllStudents();

    Student getStudentById(int id);

    Set<Course> getStudentCourses(int id);

    void deleteStudent(int id);

    void saveStudent(Student student);

    void addCourse(int student_id, int course_id);
}

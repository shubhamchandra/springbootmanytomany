package com.manytomany;

import com.manytomany.config.AppConfig;
import com.manytomany.dao.StudentDao;
import com.manytomany.models.Course;
import com.manytomany.models.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

public class GetStudentsCourses {
    public static void main(String ... args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        StudentDao studentDao = (StudentDao) context.getBean("studentRepository");
        Set<Course> courses = studentDao.getStudentCourses(1);
        System.out.println("Courses are : " + courses);
        Student student = studentDao.getStudentById(1);
        System.out.println(student);

        /* Can use JOIN FETCH to prevent lazyinitializaiton */
    }
}

package com.manytomany;

import com.manytomany.config.AppConfig;
import com.manytomany.dao.CourseDao;
import com.manytomany.dao.StudentDao;
import com.manytomany.models.Course;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StudentsTakeCourses {
    public static void main(String ... args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        CourseDao courseDao = (CourseDao) context.getBean("courseRepository");
        StudentDao studentDao = (StudentDao) context.getBean("studentRepository");

        // these objects are useless dead proxy objects
        // cannot be used again to change in database
        // they are called detached objects
        Course c1 = courseDao.getCourseById(1);
        Course c2 = courseDao.getCourseById(2);
        Course c3 = courseDao.getCourseById(3);
        Course c4 = courseDao.getCourseById(4);

        studentDao.addCourse(1, 1);
        studentDao.addCourse(1, 2);
        studentDao.addCourse(1, 3);
        studentDao.addCourse(2, 2);
        studentDao.addCourse(2, 3);
        studentDao.addCourse(2, 4);
        studentDao.addCourse(3, 3);
        studentDao.addCourse(3, 4);
        studentDao.addCourse(4, 4);
        studentDao.addCourse(4, 1);
    }


}

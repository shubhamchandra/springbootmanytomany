package com.manytomany;

import com.manytomany.config.AppConfig;
import com.manytomany.dao.StudentDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DeleteStudent {
    public static void main(String ... args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
        StudentDao studentDao = (StudentDao) context.getBean("studentRepository");
        studentDao.deleteStudent(4);
        // must remove the student from all the courses
        // must remove from course_student join table since it is a foreign key there
    }
}

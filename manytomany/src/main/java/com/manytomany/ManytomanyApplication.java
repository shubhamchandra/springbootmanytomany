package com.manytomany;

import com.manytomany.config.AppConfig;
import com.manytomany.dao.CourseDao;
import com.manytomany.dao.StudentDao;
import com.manytomany.models.Course;
import com.manytomany.models.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootApplication
public class ManytomanyApplication {

	public static void main(String[] args) {

		//SpringApplication.run(ManytomanyApplication.class, args);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class);
		context.refresh();

		Student s1 = new Student("Shubham", "Chandra", "shubham.chandra01@gmail.com");
		Student s2 = new Student("Satish", "Kumar", "nitssats@gmail.com");
		Student s3 = new Student("Sachin", "Sharma", "3june1994@gmail.com");
		Student s4 = new Student("Aayush", "Tripathi", "aayusht@gmail.com");

		Course c1 = new Course("Algorithms");
		Course c2 = new Course("Theory of Computations");
		Course c3 = new Course("Maths");
		Course c4 = new Course("DBMS");
		Course c5 = new Course("Data Communications");

		// use it through interface otherwise proxy error
		StudentDao studentDao = (StudentDao) context.getBean("studentRepository");
		CourseDao courseDao = (CourseDao) context.getBean("courseRepository");

		studentDao.saveStudent(s1);
		studentDao.saveStudent(s2);
		studentDao.saveStudent(s3);
		studentDao.saveStudent(s4);
		courseDao.saveCourse(c1);
		courseDao.saveCourse(c2);
		courseDao.saveCourse(c3);
		courseDao.saveCourse(c4);
	}
}

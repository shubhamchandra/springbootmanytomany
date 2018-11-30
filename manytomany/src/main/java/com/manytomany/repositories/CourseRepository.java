package com.manytomany.repositories;

import com.manytomany.dao.CourseDao;
import com.manytomany.models.Course;
import com.manytomany.models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class CourseRepository implements CourseDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Course> getAllCourses() {
        Session session = sessionFactory.getCurrentSession();
        List<Course> courses = session.createQuery("from Course").getResultList();
        return courses;
    }

    @Override
    public Course getCourseById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Course course = session.get(Course.class, id);
        course.getId();
        course.getTitle();
        // to load the hibernate proxy object to memory to become a real object
        return course;
    }

    @Override
    public Set<Student> getAllStudents(int id) {
        Session session = sessionFactory.getCurrentSession();
        Course course = session.get(Course.class, id);
        Set<Student> students = course.getStudents();
        students.size(); // to load the proxy to memory or to unproxy it
        return students;
    }

    @Override
    public void deleteCourse(int id) {
        Session session = sessionFactory.getCurrentSession();
        Course course = session.get(Course.class, id);
        // may raise an exception
        session.delete(course);
    }

    @Override
    public void saveCourse(Course course) {
        Session session = sessionFactory.getCurrentSession();
        session.save(course);
    }
}

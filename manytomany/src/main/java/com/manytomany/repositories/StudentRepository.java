package com.manytomany.repositories;

import com.manytomany.dao.StudentDao;
import com.manytomany.models.Course;
import com.manytomany.models.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class StudentRepository implements StudentDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Student> getAllStudents() {
        Session session = sessionFactory.getCurrentSession();
        List <Student> students = session.createQuery("from Student").getResultList();
        return students;
    }

    @Override
    public Student getStudentById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, id);
        //System.out.println("===================================\n=============\n============================");
        //System.out.println(student); // will also load the object to the memory and lazy exception will be avoided
        //System.out.println("=========================\n=======================\n=============");

        /* Make call for getters to load from proxy to memory, to avoid lazyinitializationexception */
        student.getLastName();
        student.getEmail();
        student.getFirstName();
        student.getId();
        return student;
    }

    @Override
    public Set<Course> getStudentCourses(int id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, id);
        Set <Course> courses = student.getCourses();
        System.out.println("==============\n=============================\n======================\n");
        //System.out.println(courses);
        // To load the proxy to memory or to unproxy it
        courses.size();
        return courses;
    }

    @Override
    public void deleteStudent(int id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, id);
        // remove the student from all his courses
        for(Course course : student.getCourses()) {
            course.getStudents().remove(student);
        }
        // this will be reflected in the JoinTable
        // trace the corresponding queries that hibernate runs
        session.delete(student);
    }

    @Override
    public void saveStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.save(student);
    }

    /* Convenience method to add course to student and student to course */
    @Override
    public void addCourse(int student_id, int course_id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, student_id);
        Course course = session.get(Course.class, course_id); // must be an alive proxy object
        // cannot be a dead proxy from previous session
        student.addCourse(course);
        /* Actions done on the entity objects will be carried out on the database by Hibernate */
    }
}

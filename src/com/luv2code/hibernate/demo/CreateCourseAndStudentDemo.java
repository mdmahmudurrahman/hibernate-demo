package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentDemo {

	public static void main(String[] args) {
		// Create SessionFactory
		SessionFactory sessionFactory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Instructor.class)
			.addAnnotatedClass(InstructorDetail.class)
			.addAnnotatedClass(Course.class)
			.addAnnotatedClass(Review.class)
			.addAnnotatedClass(Student.class)
			.buildSessionFactory();
		// Create Session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			// start a transaction
			session.beginTransaction();
			Course course = new Course("Course 11");
			
			session.save(course);
			
			Student student1 = new Student("Student 1", "Last name 1", "e1@e.e");
			Student student2 = new Student("Student 2", "Last name 2", "e2@e.e");
			Student student3 = new Student("Student 3", "Last name 3", "e3@e.e");
			Student student4 = new Student("Student 4", "Last name 4", "e4@e.e");
			
			course.addStudents(student1);
			course.addStudents(student2);
			course.addStudents(student3);
			course.addStudents(student4);
			
			session.save(student1);
			session.save(student2);
			session.save(student3);
			session.save(student4);
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}

package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCoursesDemo {

	public static void main(String[] args) {
		// Create SessionFactory
		SessionFactory sessionFactory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Instructor.class)
			.addAnnotatedClass(InstructorDetail.class)
			.addAnnotatedClass(Course.class)
			.buildSessionFactory();
		// Create Session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			// start a transaction
			session.beginTransaction();
			
			int instructorId = 1;
			Instructor instructor = session.get(Instructor.class, instructorId);
			Course course1 = new Course("Course 4");
			Course course2 = new Course("Course 5");
			Course course3 = new Course("Course 6");
			
			//Add courses to an instructor
			instructor.add(course1);
			instructor.add(course2);
			instructor.add(course3);
			//Save the course
			session.save(course1);
			session.save(course2);
			session.save(course3);
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}

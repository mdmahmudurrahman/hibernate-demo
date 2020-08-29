package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class GetInstructorCoursesDemo {

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
            System.out.println("Instructor is : ");
            System.out.println(instructor);
            System.out.println("Courses of this instructor are : ");
            System.out.println(instructor.getCourses());
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}

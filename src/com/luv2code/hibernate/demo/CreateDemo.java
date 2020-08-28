package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {
		// Create SessionFactory
		SessionFactory sessionFactory = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Instructor.class)
			.addAnnotatedClass(InstructorDetail.class)
			.buildSessionFactory();
		// Create Session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			// create object
			Instructor instructor = new Instructor("AAA", "BBB", "e@e.e");
			InstructorDetail instructorDetail = new InstructorDetail("ChannelABC", "Running");
			// associate object
			instructor.setInstructorDetail(instructorDetail);
			// start a transaction
			session.beginTransaction();
			session.save(instructor);
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		} finally {
			sessionFactory.close();
		}
	}
}

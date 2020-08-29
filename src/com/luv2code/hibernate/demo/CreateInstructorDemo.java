package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateInstructorDemo {

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
			// create object
			Instructor instructor = new Instructor("CC", "DD", "a@a.a");
			InstructorDetail instructorDetail = new InstructorDetail("ChannelCCDD", "Gaiming");
			// associate object
			instructor.setInstructorDetail(instructorDetail);
			// start a transaction
			session.beginTransaction();
			session.save(instructor);
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}

package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

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
			// start a transaction
			session.beginTransaction();
			// get instructor detail by id
			int theId = 6;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);
			// print instructor detail
			System.out.println(instructorDetail);
			// print associated instructor
			System.out.println(instructorDetail.getInstructor());
			
			// Remove the bi-directional associated reference
			instructorDetail.getInstructor().setInstructorDetail(null);
			session.delete(instructorDetail);
			
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		} 
		catch (Exception exception) {
			exception.printStackTrace();
			// TODO: handle exception
		}
		finally {
			session.close();
			sessionFactory.close();
		}
	}
}

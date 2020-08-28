package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		// Create SessionFactory
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Student.class).buildSessionFactory();
		
		try {
			int studentId = 13;
			
			// start a transaction
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Primary key is : "+studentId);
			Student student = session.get(Student.class, studentId);
			System.out.println("Updating....");
			student.setEmail("updated@email.email");
			session.getTransaction().commit();
			
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("update Student set email='e@e.e'").executeUpdate();					
			session.getTransaction().commit();
			System.out.println("Done!!");
		} finally {
			sessionFactory.close();
		}
	}
}

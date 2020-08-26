package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// Create SessionFactory
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Student.class).buildSessionFactory();
		// Create Session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			System.out.println("Creating 3 student object...");
			// create 3 student object
			Student student1 = new Student("AAA1", "LLLL1", "email1@email.email");
			Student student2 = new Student("AAA2", "LLLL2", "email2@email.email");
			Student student3 = new Student("AAA3", "LLLL3", "email3@email.email");
			// start a transaction
			session.beginTransaction();
			System.out.println("Saving the students object...");
			session.save(student1);
			session.save(student2);
			session.save(student3);
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		} finally {
			sessionFactory.close();
		}
	}

}

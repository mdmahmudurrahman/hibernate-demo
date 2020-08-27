package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		// Create SessionFactory
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Student.class).buildSessionFactory();
		// Create Session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			System.out.println("Creating new student object...");
			// create a student object
			Student student = new Student("Reading", "Student", "email@email.email");
			// start a transaction
			session.beginTransaction();
			System.out.println("Saving the student object...");
			session.save(student);
			System.out.println(student);
			
			// commit the transaction
			session.getTransaction().commit();
			
			//student's primary key
			System.out.println("Primary key is : "+student.getId());
			
			// Get a new session and start a transaction
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			// retrieve the student using the PK
			Student retrievedStudent = session.get(Student.class, student.getId());
			System.out.println("Get complete"+retrievedStudent);
			// Commit the transaction
			session.getTransaction().commit();
//			System.out.println("Done!!");
		} finally {
			sessionFactory.close();
		}
	}
}

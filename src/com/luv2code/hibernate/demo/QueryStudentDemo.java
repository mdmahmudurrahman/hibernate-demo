package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

import com.luv2code.hibernate.demo.entity.Student;


public class QueryStudentDemo {

	public static void main(String[] args) {
		// Create SessionFactory
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Student.class).buildSessionFactory();
		// Create Session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			// start a transaction
			session.beginTransaction();
			List<Student> students = session.createQuery("from Student").getResultList();
			
			displayStudents(students);
			
			students = session.createQuery("from Student s where" 
					+ " s.firstName = 'Reading'").getResultList();
			
			System.out.println("Students has last name Reading");
			displayStudents(students);
			
			students = session.createQuery("from Student s where" 
					+ " s.firstName = 'Reading' or s.lastName='Student'").getResultList();
			
			System.out.println("Students has last name Reading or first name Student");
			displayStudents(students);
			
			students = session.createQuery("from Student s where" 
					+ " s.email like '%@email.email'").getResultList();
			
			System.out.println("Students has email ends with @email");
			displayStudents(students);
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!!");
		} finally {
			sessionFactory.close();
		}
	}

	private static void displayStudents(List<Student> students) {
		for (Student student : students) {
			System.out.println(student);
		}
	}
}

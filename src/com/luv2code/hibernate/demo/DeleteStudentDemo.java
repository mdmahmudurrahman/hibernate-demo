package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		// Create SessionFactory
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Student.class).buildSessionFactory();
		
		try {
			int studentId = 13;
			
			// start a transaction
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			// System.out.println("Primary key is : "+studentId);
			// Student student = session.get(Student.class, studentId);
			System.out.println("Deleting....");
			// Approach 1
            // session.delete(student);
            // Approach 2
			session.createQuery("delete from Student where id=14").executeUpdate();
			session.getTransaction().commit();
			System.out.println("Done!!");
		} finally {
			sessionFactory.close();
		}
	}
}

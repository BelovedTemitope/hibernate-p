package com.shark.hibernate.d;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shark.hibernate.d.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session 
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudent = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudent);
			
			// query students: lastName= 'Doe'
			theStudent = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents who have last name of Doe");
			displayStudents(theStudent);
			
			// query students: lastName="Doe" OR firstName="Daffy"
			theStudent = session.createQuery("from Student s where"+
					" s.lastName='Doe' OR s.firstName='Daffy'").getResultList();
			System.out.println("\n\nStudents who have last name of Doe OR first name Daffy");
			displayStudents(theStudent);
			
			// query students where email LIKE '%gmail.com'
			theStudent = session.createQuery("from Student s where"
					+ " s.email LIKE '%gmail.com'").getResultList();
			System.out.println("\n\nStudents whose email ends with \'%gmail.com\'");
			displayStudents(theStudent);
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudent) {
		for (Student tempStudent : theStudent) {
			System.out.println(tempStudent);
		}
	}

}

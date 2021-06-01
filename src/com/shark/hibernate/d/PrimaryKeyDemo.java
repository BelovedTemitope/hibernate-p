package com.shark.hibernate.d;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shark.hibernate.d.entity.Student;

public class PrimaryKeyDemo {
	
	public static void main(String [] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create session 
		Session session = factory.getCurrentSession();
		
		try {
			// create the student Java object
			System.out.println("Creating 3 students object...");
			Student tempStudent1 = new Student("John", "Doe", "john@shark.com");
			Student tempStudent2 = new Student("Mary", "Public", "mary@shark.com");
			Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@shark.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}finally {
			factory.close();
		}
	}

}

package com.shark.hibernate.d;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shark.hibernate.d.entity.Course;
import com.shark.hibernate.d.entity.Instructor;
import com.shark.hibernate.d.entity.InstructorDetail;
import com.shark.hibernate.d.entity.Student;

public class DeleteCourseDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		//create session 
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// get a course
			int theId = 10;
			Course theCourse = session.get(Course.class, theId);
			
			// delete course
			System.out.println("Deleting course: " + theCourse);
			
			session.delete(theCourse);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}finally {
			
			// add clean up code
			session.close();
			
			factory.close();
		}
	}

}

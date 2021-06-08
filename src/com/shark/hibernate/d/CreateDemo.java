package com.shark.hibernate.d;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shark.hibernate.d.entity.Instructor;
import com.shark.hibernate.d.entity.InstructorDetail;
import com.shark.hibernate.d.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		//create session 
		Session session = factory.getCurrentSession();
		
		try {
			
			// create the objects 
			Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail(
					"http://www.youtube.com", 
					"Guitar");
			
//			Instructor tempInstructor = new Instructor("Chad", "Darby", "beloved@luv2code.com");
//			
//			InstructorDetail tempInstructorDetail = new InstructorDetail(
//					"http://www.luv2code.com/youtube", 
//					"Luv 2 code!");
			
			// associate the objects 
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start a transaction
			session.beginTransaction();
			
			// save the instructor
			//
			// Note: this will ALSO save details object
			// because of CascadseType.ALL
			//
			System.out.println("Saving instructor: "+tempInstructor);
			session.save(tempInstructor);
			
			// commit transaction
			session.getTransaction().commit();
			;
			System.out.println("Done!");
		}finally {
			factory.close();
		}
	}

}

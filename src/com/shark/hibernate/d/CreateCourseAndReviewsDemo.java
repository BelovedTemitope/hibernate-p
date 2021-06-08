package com.shark.hibernate.d;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shark.hibernate.d.entity.Course;
import com.shark.hibernate.d.entity.Instructor;
import com.shark.hibernate.d.entity.InstructorDetail;
import com.shark.hibernate.d.entity.Review;
import com.shark.hibernate.d.entity.Student;

public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		//create session 
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			
			// create a course
			Course tempCourse = new Course("Pacman - How to Score One Million Points");
			
			// add some reviews
			tempCourse.add(new Review("Great course... Loved it!"));
			tempCourse.add(new Review("Cool course, job well done"));
			tempCourse.add(new Review("What a dumb course, you're an idiot"));
			
			// save the course 
			System.out.println("Saving the course");
			System.out.println(tempCourse);
			System.out.println(tempCourse.getReviews());
			
			session.save(tempCourse);
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

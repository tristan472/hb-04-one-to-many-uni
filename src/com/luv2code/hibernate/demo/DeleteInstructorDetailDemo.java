package com.luv2code.hibernate.demo;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction
			session.beginTransaction();			
			
			// get the instructor detail object
			int theId = 3;
			InstructorDetail tempInstrucorDetail = session.get(InstructorDetail.class, theId);
			
			// print the instructor detail
			System.out.println("tempInstructor: " + tempInstrucorDetail);
			
			// print the associated instructor
			System.out.println("the associated instructor: " + tempInstrucorDetail.getInstructor());
			
			// now let's delete the instructor detail
			System.out.println("Deleting tempInstrucorDetail: " + tempInstrucorDetail);
			
			// remove the associated object reference
			// break bi-directional link
			tempInstrucorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(tempInstrucorDetail);
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done !");
			
			}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			// handle connection leak issue
			session.close();
			
			factory.close();
		}
	}
}
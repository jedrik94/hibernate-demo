package pl.jedrik94;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.jedrik94.model.Instructor;
import pl.jedrik94.model.InstructorDetail;

public class App {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory()) {

            Instructor instructor = new Instructor();

            instructor.setFirstName("Milosz");
            instructor.setLastName("Wojtkowiak");
            instructor.setEmail("milosz04@gmail.com");

            InstructorDetail instructorDetail = new InstructorDetail();

            instructorDetail.setYoutubeChannel("www.youtube.com/milosz04");
            instructorDetail.setHobby("Gaming");

            instructor.setInstructorDetail(instructorDetail);

            Session session = factory.getCurrentSession();
            session.beginTransaction();

            session.save(instructor);

            session.getTransaction().commit();
        }
    }
}

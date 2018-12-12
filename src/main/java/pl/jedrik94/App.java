package pl.jedrik94;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.jedrik94.model.Course;
import pl.jedrik94.model.Instructor;
import pl.jedrik94.model.InstructorDetail;

public class App {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory()) {

            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Instructor instructorJedrzej = new Instructor();

            instructorJedrzej.setFirstName("Jedrzej");
            instructorJedrzej.setLastName("Wojtkowiak");
            instructorJedrzej.setEmail("jedrik94@gmail.com");

            InstructorDetail instructorDetailJedrzej = new InstructorDetail();

            instructorDetailJedrzej.setYoutubeChannel("www.youtube.com/jedirk94");
            instructorDetailJedrzej.setHobby("CrossFit");

            instructorJedrzej.setInstructorDetail(instructorDetailJedrzej);

            session.save(instructorJedrzej);

            session.getTransaction().commit();
        }
    }
}

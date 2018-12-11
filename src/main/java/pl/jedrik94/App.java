package pl.jedrik94;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.jedrik94.model.Instructor;
import pl.jedrik94.model.InstructorDetail;

import java.util.Optional;

public class App {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory()) {

            Session session = factory.getCurrentSession();
            session.beginTransaction();

            Optional<InstructorDetail> tmpInstructor = Optional.ofNullable(session.get(InstructorDetail.class, 101));

            tmpInstructor.ifPresent(instructorDetail -> {
                System.out.println(instructorDetail);
                System.out.println(instructorDetail.getInstructor());
            });

            session.getTransaction().commit();
        }
    }
}

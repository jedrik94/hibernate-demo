package pl.jedrik94;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
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

            int instructorId = 1;

            Query<Instructor> query = session.createQuery("select i from Instructor i " +
                    "JOIN FETCH i.courses " +
                    "where i.id = :instructorId");
            query.setParameter("instructorId", instructorId);

            Instructor instructorTmp = query.getSingleResult();

            session.getTransaction().commit();

            System.out.println(instructorTmp);
        }
    }
}

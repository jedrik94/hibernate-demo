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

            int instructorId = 1;

            Instructor instructorTmp = session.get(Instructor.class, instructorId);

            Course courseJava = new Course();
            courseJava.setTitle("Java Course");

            Course courseCpp = new Course();
            courseCpp.setTitle("Cpp Course");

            instructorTmp.addCourse(courseJava);
            instructorTmp.addCourse(courseCpp);

            session.save(courseJava);
            session.save(courseCpp);

            session.getTransaction().commit();
        }
    }
}

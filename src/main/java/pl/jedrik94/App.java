package pl.jedrik94;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.jedrik94.model.Student;

public class App {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        Student student = new Student();
        student.setFirstName("Zbyszek");
        student.setLastName("Kowal");
        student.setEmail("zbycho_69@gmail.com");

        session.beginTransaction();

        session.save(student);

        session.getTransaction().commit();

        factory.close();
    }
}

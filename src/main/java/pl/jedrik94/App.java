package pl.jedrik94;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.jedrik94.model.Student;

public class App {
    public static void main(String[] args) {

        Student student = new Student();
        student.setFirstName("Zbyszek");
        student.setLastName("Kowal");
        student.setEmail("zbycho_69@gmail.com");

        System.out.println(student);

        try (SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory()) {

            Session session = factory.getCurrentSession();

            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();

            System.out.println("Saved student id: " + student.getId());

            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Retrieving student with id: " + student.getId());

            Student sameStudent = session.get(Student.class, student.getId());
            session.getTransaction().commit();

            System.out.println(sameStudent);
        }
    }
}

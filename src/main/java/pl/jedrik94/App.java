package pl.jedrik94;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.jedrik94.model.Student;

import java.util.List;

public class App {
    public static void main(String[] args) {

        List<Student> studentList;

        try (SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory()) {

            Session session = factory.getCurrentSession();

            session.beginTransaction();

            studentList = getKowalskiStudentList(session);

            session.getTransaction().commit();
        }

        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    private static List<Student> getWholeStudentList(Session session) {
        return session.createQuery("from Student").getResultList();
    }

    private static List<Student> getGmailStudentList(Session session) {
        return session.createQuery("from Student s where s.email like '%gmail.com'").getResultList();
    }

    private static List<Student> getKowalskiStudentList(Session session) {
        return session.createQuery("from Student s where s.lastName = 'Kowalski'").getResultList();
    }
}

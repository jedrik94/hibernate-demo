package pl.jedrik94;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.jedrik94.model.Student;

import java.util.List;

public class App {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory()) {

            Session session = factory.getCurrentSession();
            session.beginTransaction();

            int entityId = 6;

            Student student = session.get(Student.class, entityId);

            student.setEmail("zupa123@apud.moc");

            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();

            session.createQuery("update Student set email = 'foo@bar.test'")
                    .executeUpdate();

            session.getTransaction().commit();
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

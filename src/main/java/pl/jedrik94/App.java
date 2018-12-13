package pl.jedrik94;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.jedrik94.model.Course;
import pl.jedrik94.model.Instructor;
import pl.jedrik94.model.InstructorDetail;
import pl.jedrik94.model.Review;

import java.util.Arrays;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory()) {

            Session session = factory.getCurrentSession();
            session.beginTransaction();

            int courseId = 202;

            Optional<Course> courseTmp = Optional.ofNullable(session.get(Course.class, courseId));

            Review reviewDope = new Review();
            reviewDope.setComment("Yea.. Ez n dope course. Peace!!");
            Review reviewOp = new Review();
            reviewOp.setComment("this is OP!!!11 DELETe IIT");

            courseTmp.ifPresent(course -> course.addReviews(Arrays.asList(reviewOp, reviewDope)));

            session.getTransaction().commit();
        }
    }
}

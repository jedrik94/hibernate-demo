package pl.jedrik94;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.jedrik94.model.Employee;

import java.util.List;

public class App {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory()) {

            Session session;

            {
                session = factory.getCurrentSession();
                session.beginTransaction();

                Employee employeeAndrzej = new Employee();
                employeeAndrzej.setFirstName("Andrzej");
                employeeAndrzej.setLastName("Nowak");
                employeeAndrzej.setCompany("BckPuddingCompany");

                Employee employeeTomek = new Employee();

                employeeTomek.setFirstName("Tomek");
                employeeTomek.setLastName("Kotek");
                employeeTomek.setCompany("GreenPuddingCompany");

                session.save(employeeAndrzej);
                session.save(employeeTomek);

                session.getTransaction().commit();
            }

            {
                session = factory.getCurrentSession();
                session.beginTransaction();

                int entityId = 2;

                Employee newEmployee = session.get(Employee.class, entityId);

                newEmployee.setCompany("Bck&WhtPuddingCompany");

                session.getTransaction().commit();
            }

            {
                session = factory.getCurrentSession();
                session.beginTransaction();

                List<Employee> employeeList = session.createQuery("from Employee e where e.company like '%PuddingCompany'")
                        .getResultList();

                printOutEmployees(employeeList);

                session.getTransaction().commit();
            }

            {
                session = factory.getCurrentSession();
                session.beginTransaction();

                int entityId = 1;

                Employee newEmployee = session.get(Employee.class, entityId);

                session.delete(newEmployee);

                session.getTransaction().commit();
            }
        }
    }

    private static void printOutEmployees(List<Employee> employeeList) {
        for (Employee e : employeeList) {
            System.out.println(e);
        }
    }

}

import model.HibernateUtil;
import model.Person;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            Person person = new Person();
            person.setName("Vasya");
            session.save(person);

            person = new Person();
            person.setName("Ivan");
            session.save(person);

            session.getTransaction().commit();
        } catch (Throwable cause) {
            cause.printStackTrace();
        }

        List<Person> list = null;

        try(Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery("FROM Person");
            list = (List<Person>) query.list();

            session.getTransaction().commit();
        } catch (Throwable cause) {
            cause.printStackTrace();
        }

        if (list != null && !list.isEmpty()) {
            for (Person p : list) {
                System.out.println(p);
            }
        }

        System.exit(0);

    }

}

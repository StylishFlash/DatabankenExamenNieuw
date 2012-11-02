package HQL;

import model.Bioscoopholding;
import model.Film;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.HibernateUtil;

/**
 * Created with IntelliJ IDEA.
 * User: Joachim
 * Date: 2/11/12
 * Time: 18:41
 * To change this template use File | Settings | File Templates.
 */
public class OpslaanFilm {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        Bioscoopholding bioscoopholding = new Bioscoopholding("Kinepolis", "Gent", "BE001");
        session.saveOrUpdate(bioscoopholding);

        Film film  = new Film("The Muppets", "Komedie", "De muppets film", 103, "USA", 1, true);
        film.setBioscoopholding(bioscoopholding);



        tx.commit();

    }

}

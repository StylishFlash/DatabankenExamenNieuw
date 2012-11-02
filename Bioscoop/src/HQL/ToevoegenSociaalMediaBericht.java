package HQL;

import model.Film;
import model.Socialmedia;
import model.SocialmediaBericht;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.HibernateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Joachim
 * Date: 2/11/12
 * Time: 22:44
 * To change this template use File | Settings | File Templates.
 */
public class ToevoegenSociaalMediaBericht {

    public static void main(String[] args) throws ParseException {

        final String filmTitel = "The Muppets";

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from model.Film where titel = :titel");
        query.setString("titel", filmTitel);

        try {

            Film film = (Film) query.list().get(0);

            Socialmedia socialmedia = new Socialmedia("Twitter");
            session.saveOrUpdate(socialmedia);

            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
            Date tijdstip = dateFormat.parse("01/01/2012 01:20:22");
            SocialmediaBericht socialmediaBericht = new SocialmediaBericht(tijdstip, "The Muppets is een leuke film!", socialmedia);
            socialmediaBericht.setSocialmedia(socialmedia);
            socialmediaBericht.setFilm(film);
            session.saveOrUpdate(socialmediaBericht);

            socialmedia.addSocialmediaBericht(socialmediaBericht);
            session.saveOrUpdate(socialmedia);

            film.addSocialmediaBericht(socialmediaBericht);
            session.saveOrUpdate(film);

        }  catch (Exception e) {

           System.out.println("Er zijn geen films met als titel " + filmTitel);

        }

        transaction.commit();

    }

}

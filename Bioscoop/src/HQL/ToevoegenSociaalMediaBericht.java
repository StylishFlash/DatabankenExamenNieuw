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
        final String socialeMedia = "Twitter";
        final String berichtTijdstip = "01/01/2012 01:20:22";
        final String berichtInhoud = filmTitel + " is een leuke film";

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        // Query voor film met een bepaalde titel in te lezen

        Query query = session.createQuery("from model.Film where titel = :titel");
        query.setString("titel", filmTitel);

        try {

            // Eerste film met deze titel inlezen

            Film film = (Film) query.list().get(0);

            // Nieuwe sociale media aanmaken

            Socialmedia socialmedia = new Socialmedia(socialeMedia);
            session.saveOrUpdate(socialmedia);

            // Sociaal media bericht aanmaken

            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
            Date tijdstip = dateFormat.parse(berichtTijdstip);
            SocialmediaBericht socialmediaBericht = new SocialmediaBericht(tijdstip, berichtInhoud, socialmedia);
            socialmediaBericht.setSocialmedia(socialmedia);
            socialmediaBericht.setFilm(film);
            session.saveOrUpdate(socialmediaBericht);

            // Sociaal media bericht toevoegen aan de sociale media

            socialmedia.addSocialmediaBericht(socialmediaBericht);
            session.saveOrUpdate(socialmedia);

            // Sociaal media bericht toevoegen aan de film

           toevoegenSociaalMediaBericht(session, film, socialmediaBericht);

        } catch (Exception e) {

            System.out.println("Er zijn geen films met als titel " + filmTitel);

        }

        transaction.commit();

    }

    public static void toevoegenSociaalMediaBericht(Session session, Film film, SocialmediaBericht socialmediaBericht) {

        // Sociaal media bericht toevoegen aan de film

        film.addSocialmediaBericht(socialmediaBericht);
        session.saveOrUpdate(film);

    }

}

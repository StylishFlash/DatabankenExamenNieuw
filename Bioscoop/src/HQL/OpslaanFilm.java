package HQL;

import model.Bioscoopholding;
import model.Film;
import model.Medewerker;
import model.Rol;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.HibernateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created with IntelliJ IDEA.
 * User: Joachim
 * Date: 2/11/12
 * Time: 18:41
 * To change this template use File | Settings | File Templates.
 */
public class OpslaanFilm {

    public static void main(String[] args) throws ParseException {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        // Bioscoopholding aanmaken

        Bioscoopholding bioscoopholding = new Bioscoopholding("Kinepolis", "Gent", "BE001");
        session.saveOrUpdate(bioscoopholding);

        // Film aanmaken en bioscoopholding toekennen

        Film film = new Film("The Muppets", "Komedie", "De muppets film", 103, "USA", 1, true);
        film.setBioscoopholding(bioscoopholding);
        session.saveOrUpdate(film);

        // Rol aanmaken

        Rol rol = new Rol("Rol van Kermit de kikker", film);
        session.saveOrUpdate(rol);

        // Rol aan film toevoegen

        film.addRol(rol);
        session.saveOrUpdate(rol);

        // Medewerker aanmaken en rol toekennnen

        Medewerker medewerker = new Medewerker("Jan", new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1980"), "Belgie");
        medewerker.addRol(rol);
        session.saveOrUpdate(medewerker);

        // Medewerker toewijzen aan rol

        rol.setMedewerker(medewerker);
        session.saveOrUpdate(rol);

        transaction.commit();

    }

}

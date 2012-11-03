package HQL;

import model.Vertoning;
import model.Zetel;
import model.Zone;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.HibernateUtil;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Joachim
 * Date: 3/11/12
 * Time: 16:54
 * To change this template use File | Settings | File Templates.
 */
public class OpzoekenBeschikbarePlaatsen {

    public static void main(String[] args) {

        final String filmTitel = "The Muppets";

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        // Query voor een vertoning met een bepaalde film titel in te lezen

        Query query = session.createQuery("from model.Vertoning vertoning join fetch vertoning.film film where film.titel = :titel");
        query.setString("titel", filmTitel);

        try {

            // Eerste vertoning met deze film titel inlezen

            Vertoning vertoning = (Vertoning) query.list().get(0);

            // Zetelreservatie aanzetten

            vertoning.setZetelReservatie(true);
            session.saveOrUpdate(vertoning);

            // Beschikbare zetels tonen

            System.out.println(toonBeschikbarePlaatsen(vertoning));

        } catch (Exception e) {

            System.out.println("Er is geen vertoning met als titel " + filmTitel);

        }

        transaction.commit();

    }

    public static String toonBeschikbarePlaatsen(Vertoning vertoning) {

        Collection<Zone> zones = vertoning.getZaal().getZones();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Beschikbare zetels voor de vertoning van %s %s in zaal %d: \n", vertoning.getFilm().getTitel(), vertoning.getTijdstip(), vertoning.getZaal().getNummer()));

        for (Zone zone : zones) {

            Collection<Zetel> zetels = zone.getZetels();

            for (Zetel zetel : zetels) {

                if (!zetel.isGereserveerd()) {

                    stringBuilder.append(String.format("Zetel %d \n", zetel.getNummer()));

                }

            }

        }

        return stringBuilder.toString();

    }

}



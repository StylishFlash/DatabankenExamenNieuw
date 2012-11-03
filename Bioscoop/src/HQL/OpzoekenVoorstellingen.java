package HQL;

import model.Medewerker;
import model.Rol;
import model.Vertoning;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.HibernateUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Joachim
 * Date: 3/11/12
 * Time: 0:20
 * To change this template use File | Settings | File Templates.
 */
public class OpzoekenVoorstellingen {

    public static void main(String[] args) throws ParseException {

        final String acteurNaam = "Jan";
        final String startTijd = "01/12/2011 20:00:00";
        final String eindTijd = "01/12/2011 21:00:00";

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
        Date startTijdstip = dateFormat.parse(startTijd);
        Date eindTijdstip = dateFormat.parse(eindTijd);

        System.out.println(toonVertoningen(session, acteurNaam, startTijdstip, eindTijdstip));

        transaction.commit();

    }

    @SuppressWarnings("unchecked")
    public static String toonVertoningen(Session session, String acteurNaam, Date startTijdstip, Date eindTijdstip) {

        // Query om een vertoning tussen start en eind tijdstip in te lezen

        Query query = session.createQuery("from model.Vertoning vertoning where vertoning.tijdstip between :startTijdstip and :eindTijdstip");
        query.setTimestamp("startTijdstip", startTijdstip);
        query.setTimestamp("eindTijdstip", eindTijdstip);

        // Vertoningen tussen start en eind tijdstip inlezen

        Collection<Vertoning> vertoningenTijdstip = query.list();

        // Verzameling om de vertoningen waar de acteur in meespeelt in te steken

        Collection<Vertoning> vertoningen = new ArrayList<Vertoning>();

        // Elke vertoning tussen start en eind tijdstip controleren

        for (Vertoning vertoning : vertoningenTijdstip) {

            // Alle rollen in deze vertoning in een verzameling stoppen

            Collection<Rol> rollen = vertoning.getFilm().getRollen();

            // Voor elke rol van de voorstelling controleren of deze door de acteur wordt gespeeld

            for (Rol rol : rollen) {

                try {

                    // Medewerker inlezen

                    Medewerker medewerker = rol.getMedewerker();

                    // Controleren of de acteur meespeelt in deze vertoning

                    if (medewerker.getNaam().equalsIgnoreCase(acteurNaam)) {

                        vertoningen.add(vertoning);

                    }


                } catch (NullPointerException ex) {

                    // Doe niets

                }

            }

        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Titel | Tijdstip | Taal | 3D | Zaal | Zetelreservatie \n");

        // Elke vertoning waar de acteur in meespeeld overlopen

        for (Vertoning vertoning : vertoningen) {

            // Informatie over de vertoning toevoegen aan de StringBuilder

            stringBuilder.append(String.format("%s | %s | %s | %s | %d | %s \n", vertoning.getFilm().getTitel(), vertoning.getTijdstip(), vertoning.getTaal(), vertoning.getIs3D(), vertoning.getZaal().getNummer(), vertoning.getZetelReservatie()));

        }

        return stringBuilder.toString();

    }

}

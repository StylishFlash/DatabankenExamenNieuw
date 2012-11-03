package HQL;

import model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.HibernateUtil;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Joachim
 * Date: 2/11/12
 * Time: 23:14
 * To change this template use File | Settings | File Templates.
 */
public class RegistratieTicketVerkoop {

    public static void main(String[] args) {

        final String filmTitel = "The Muppets";
        final String bioscoopholdingNaam = "Kinepolis";
        final String cinemacomplexNaam = "Kinepolis Antwerpen";
        final String vertoningTijdstip = "01/12/2011 20:00:00";

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        // Query voor film met een bepaalde titel in te lezen

        Query query = session.createQuery("from model.Film where titel = :titel");
        query.setString("titel", filmTitel);

        try {

            // Eerste film met deze titel inlezen

            Film film = (Film) query.list().get(0);

            try {

                // Bioscoopholding inlezen

                query = session.createQuery("from model.Bioscoopholding where naam = :naam");
                query.setString("naam", bioscoopholdingNaam);
                Bioscoopholding bioscoopholding = (Bioscoopholding) query.list().get(0);

                // Cinemacomplex aanmaken voor de bioscoopholding

                Cinemacomplex cinemacomplex = new Cinemacomplex(cinemacomplexNaam, "Antwerpen", 123);
                cinemacomplex.setBioscoopholding(bioscoopholding);
                session.saveOrUpdate(cinemacomplex);

                // Zaal aanmaken

                Zaal zaal = new Zaal(1, cinemacomplex);
                session.saveOrUpdate(zaal);

                // Zaal toevoegen aan cinemacomplex

                cinemacomplex.addZaal(zaal);
                session.saveOrUpdate(cinemacomplex);

                // ZoneType aanmaken

                ZoneType zoneType = new ZoneType("Love", 10.00);
                session.saveOrUpdate(zoneType);

                // Zone aanmaken

                Zone zone = new Zone(zoneType, zaal);
                session.saveOrUpdate(zone);

                // zone toevoegen aan zaal

                zaal.addZone(zone);
                session.saveOrUpdate(zaal);

                // Zone toevoegen aan zoneType

                zoneType.addZone(zone);
                session.saveOrUpdate(zoneType);

                // Zetel aanmaken en zone toekennen

                Zetel zetel = new Zetel(1, false, zone);
                zetel.setZone(zone);
                session.saveOrUpdate(zetel);

                // Vertoning aanmaken

                DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
                Date tijdstip = dateFormat.parse(vertoningTijdstip);
                Vertoning vertoning = new Vertoning(false, false, "Engels", tijdstip, zaal, film);
                vertoning.setZaal(zaal);
                session.saveOrUpdate(vertoning);

                // Vertoning toewijzen aan zaal

                zaal.addVertooning(vertoning);
                session.saveOrUpdate(zaal);

                // Ticket verkoop registreren

                registreerTicketVerkoop(session, cinemacomplex, vertoning);

            } catch (Exception e) {

                System.out.println("Er zijn geen biscoopholdings met de naam " + bioscoopholdingNaam);

            }

        } catch (Exception e) {

            System.out.println("Er zijn geen films met als titel " + filmTitel);

        }

        transaction.commit();

    }

    public static void registreerTicketVerkoop(Session session, Cinemacomplex cinemacomplex, Vertoning vertoning) {

        // Nieuwe verkoop aanmaken

        Verkoop verkoop = new Verkoop("Kassa", cinemacomplex);
        session.saveOrUpdate(verkoop);

        // Verkoop toekennen aan cinemacomplex

        cinemacomplex.addVerkoop(verkoop);
        session.saveOrUpdate(cinemacomplex);

        // Nieuw ticket aanmaken

        Ticket ticket = new Ticket(11123, vertoning.getTijdstip(), "Normaal", verkoop, vertoning);
        session.saveOrUpdate(ticket);

        // Ticket aan verkoop hangen

        verkoop.addTicket(ticket);
        session.saveOrUpdate(verkoop);

        // Ticket toewijzen aan vertoning

        vertoning.addTicket(ticket);
        session.saveOrUpdate(vertoning);

    }

}

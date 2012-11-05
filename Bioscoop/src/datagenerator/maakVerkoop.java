package datagenerator;

import model.Cinemacomplex;

import model.Klant;
import model.Tienbeurtenkaart;
import model.Verkoop;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 4/11/12
 * Time: 19:24
 * To change this template use File | Settings | File Templates.
 */
public class maakVerkoop {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        final int aantalVerkopen = 20;

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        Query query = session.createQuery("from Cinemacomplex");
        List<Cinemacomplex> cinemacomplexes = (List<Cinemacomplex>) query.list();

        query = session.createQuery("from Klant ");
        List<Klant> klanten = (List<Klant>) query.list();

        List<String> verkooppunten = new ArrayList<String>();
        verkooppunten.add("Online");
        verkooppunten.add("Kassa");

        Random rand = new Random();

        for (int i = 0; i < aantalVerkopen; i++) {

            Klant klant = klanten.get(rand.nextInt(klanten.size()));
            Cinemacomplex cinemacomplex = cinemacomplexes.get(rand.nextInt(cinemacomplexes.size()));
            String verkooppunt = verkooppunten.get(rand.nextInt(2));
            Verkoop verkoop = new Verkoop(verkooppunt, cinemacomplex);
            verkoop.setKlant(klant);
            session.saveOrUpdate(verkoop);

            int isTienbeurtenkaart = rand.nextInt(1);

            if (isTienbeurtenkaart == 0) {

                int nummer = rand.nextInt(2);
                double prijs;

                if (nummer == 0) {

                    prijs = 14.99;

                } else {

                    prijs = 24.99;

                }

                Tienbeurtenkaart tienbeurtenkaart = new Tienbeurtenkaart(prijs, verkoop);
                session.saveOrUpdate(tienbeurtenkaart);

            } else {

            }

        }

        tx.commit();

        /*     Session s  = HibernateUtil.getSessionFactory().getCurrentSession();
             Transaction tx = s.beginTransaction();
             Calendar date = Calendar.getInstance(); // the current date/time
             Query query = s.createQuery("from VerkoopKanaal ");
             List<VerkoopKanaal> verkoopKanaals = (List<VerkoopKanaal>) query.list();
             query = s.createQuery("from Klant ");
             List<Klant> klants = (List<Klant>) query.list();
             Random rand = new Random();
             for (int i = 0; i < AANTAL_TIENBEURTENKAARTEN; i++)
             {
                 int datum = rand.nextInt(150);
                 date.setTime(new Date());
                 date.add(Calendar.DAY_OF_YEAR, -datum);
                 VerkoopKanaal verk = verkoopKanaals.get(rand.nextInt(verkoopKanaals.size()));
                 Klant k = null;
                 int number = rand.nextInt(5);
                 if (verk.getManier() == VerkoopKanaal.verkoopManier.Online || number < 2){
                     k = klants.get(rand.nextInt(klants.size()));
                 }
                 Verkoop v = new Verkoop(date.getTime(), verk, k);
                 s.saveOrUpdate(v);
                 Tienbeurtenkaart t = new Tienbeurtenkaart(v);
                 s.saveOrUpdate(t);
             }
             tx.commit();
        */
    }


}

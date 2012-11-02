package datagenerator;

import model.Tienbeurtenkaart;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.HibernateUtil;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Joris
 * Date: 2/11/12
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */
public class Maak10BeurtenKaarten {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Random random = new Random();
        for(int i = 0; i<50; i++){
            int nummer = random.nextInt(2);
            double prijs = 0;
            if(nummer == 0){
                 prijs = 14.99;
            }else{
                prijs = 24.99;
            }
            Tienbeurtenkaart tienbeurtenkaart = new Tienbeurtenkaart(prijs, null);
            session.saveOrUpdate(tienbeurtenkaart);
        }
        transaction.commit();

      /*      Session s  = HibernateUtil.getSessionFactory().getCurrentSession();
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

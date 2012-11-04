package Middleware.Twitter;

import model.Cinemacomplex;
import model.Film;
import model.Socialmedia;
import model.SocialmediaBericht;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import persistence.HibernateUtil;

import javax.jms.*;
import java.io.StringReader;

/**
 * Created with IntelliJ IDEA.
 * User: Joachim
 * Date: 4/11/12
 * Time: 15:32
 * To change this template use File | Settings | File Templates.
 */
public class Receiver {

    private static int berichtenTeller = 0;

    public static void main(String[] args) throws JMSException, InterruptedException {

        final String queueNaam = "Bioscoop Twitter";
        final int aantalBerichten = 10;

        // Create a ConnectionFactory

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover://(tcp://localhost:61616,tcp://localhost:61617)?randomize=false");

        // Create a Connection to ActiceMQ

        final Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create a Session that allows you to work with activeMQ

        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination queue (or retrieve it, if it already exists)

        Queue destination = session.createQueue(queueNaam);

        // Create a MessageConsumer for the Destination

        final MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {

            public void onMessage(Message message) {

                TextMessage textMessage = (TextMessage) message;

                try {

                    // XML bericht omzetten naar een Socialmediabericht POJO object

                    String boodschap = textMessage.getText();
                    StringReader stringReader = new StringReader(boodschap);
                    Socialmediabericht socialmediabericht = (Socialmediabericht) Unmarshaller.unmarshal(Socialmediabericht.class, stringReader);

                    //Source source = new StreamSource(stringReader);

                    // Transactie beginnen

                    org.hibernate.Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
                    Transaction transaction = hibernateSession.beginTransaction();

                    // Query voor een socialmedia met een bepaalde naam in te lezen

                    Query query = hibernateSession.createQuery("from model.Socialmedia where naam = :naam");
                    query.setString("naam", socialmediabericht.getSociaalnetwerk());

                    try {

                        // De eerste socialmedia met die naam inlezen

                        Socialmedia socialmedia = (Socialmedia) query.list().get(0);

                        // SocialmediaBericht aanmaken aan de hand van de ontvangen Socialmediabericht POJO

                        SocialmediaBericht socialmediaBericht = new SocialmediaBericht(socialmediabericht.getTijdstip(), socialmediabericht.getInhoud(), socialmedia);
                        hibernateSession.saveOrUpdate(socialmediaBericht);

                        // SocialmediaBericht toevoegen aan Socialmedia

                        socialmedia.addSocialmediaBericht(socialmediaBericht);
                        hibernateSession.saveOrUpdate(socialmedia);

                        // Controleer of het Twitter bericht over een film ging

                        if (socialmediabericht.getFilm() != null) {

                            // Query voor film met een bepaalde titel in te lezen

                            query = hibernateSession.createQuery("from model.Film where titel = :titel");
                            query.setString("titel", socialmediabericht.getFilm());

                            try {

                                // Eerste film met deze titel inlezen

                                Film film = (Film) query.list().get(0);

                                // SocialmediaBericht toevoegen aan film

                                film.addSocialmediaBericht(socialmediaBericht);
                                hibernateSession.saveOrUpdate(film);

                                // Film toevoegen aan het SocialmediaBericht

                                socialmediaBericht.setFilm(film);
                                hibernateSession.saveOrUpdate(socialmediaBericht);

                            } catch (Exception e) {

                                System.out.println("Er is geen film gevonden met de naam " + socialmediabericht.getSociaalnetwerk());

                            }

                        }

                         // Controleer of het Twitter bericht over een cinemacomplex ging

                        if (socialmediabericht.getCinemacomplex() != null) {

                               // Query voor een cinemacomplex met een bepaalde naam in te lezen

                               query = hibernateSession.createQuery("from model.Cinemacomplex where naam = :naam");
                               query.setString("naam", socialmediabericht.getCinemacomplex());

                               try {

                                   // Eerste cinemacomplex met deze naam inlezen

                                   Cinemacomplex cinemacomplex = (Cinemacomplex) query.list().get(0);

                                   // SocialmediaBericht toevoegen aan cinemacomplex

                                   cinemacomplex.addSocialmediaBericht(socialmediaBericht);
                                   hibernateSession.saveOrUpdate(cinemacomplex);

                                   // Cinemacomplex toevoegen aan het SocialmediaBericht

                                   socialmediaBericht.setCinemacomplex(cinemacomplex);
                                   hibernateSession.saveOrUpdate(socialmediaBericht);

                               } catch (Exception e) {

                                   System.out.println("Er is geen cinemacomplex gevonden met de naam " + socialmediabericht.getCinemacomplex());

                               }

                           }

                    } catch (Exception e) {

                        System.out.println("Er is geen Socialmedia gevonden met de naam " + socialmediabericht.getSociaalnetwerk());

                    }

                    transaction.commit();

                    berichtenTeller++;

                    if (berichtenTeller == aantalBerichten) {

                        consumer.close();
                        session.close();
                        connection.close();

                    }

                } catch (JMSException e) {

                    e.printStackTrace();

                } catch (MarshalException e) {

                    e.printStackTrace();

                } catch (ValidationException e) {

                    e.printStackTrace();

                }

            }

        });

    }

}

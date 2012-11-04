package Middleware.Twitter;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;

import javax.jms.*;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * Created with IntelliJ IDEA.
 * User: Joachim
 * Date: 3/11/12
 * Time: 21:15
 * To change this template use File | Settings | File Templates.
 */
public class Sender {

    public static void main(String[] args) throws JMSException, IOException, MarshalException, ValidationException, ParseException {

        final int aantalBerichten = 10;
        final List<String> films = Arrays.asList("The Muppets");
        final List<String> cinemacomplexen = Arrays.asList("Kinepolis Antwerpen");

        // Create a ConnectionFactory

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("failover://(tcp://localhost:61616,tcp://localhost:61617)?randomize=false");

        // Create a Connection to ActiceMQ

        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create a Session that allows you to work with activeMQ

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination queue (or retrieve it, if it already exists)

        Queue destination = session.createQueue("Bioscoop Twitter");

        // Create a MessageProducer for the Destination

        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // Random Twitter berichten aanmaken

        Writer writer;
        Random random = new Random();

        for (int i = 0; i < aantalBerichten; i++) {

            // Nieuw Twitter bericht aanmaken

            Socialmediabericht socialmediabericht = new Socialmediabericht();
            socialmediabericht.setSociaalnetwerk("Twitter");

            // Random tijdstip bepalen

            long offset = Timestamp.valueOf("2011-11-01 00:00:00").getTime();
            long end = Timestamp.valueOf("2011-12-01 00:00:00").getTime();
            long diff = end - offset + 1;
            Date tijdstip = new Date(offset + (long) (Math.random() * diff));
            socialmediabericht.setTijdstip(tijdstip);

            // Random kiezen voor een bericht over een film of over een cinemacomplex

            int filmOfCinemacomplex = random.nextInt(2);

            if (filmOfCinemacomplex == 0) { // Film

                // Random film uit de lijst nemen

                int film = random.nextInt(films.size());
                String filmNaam = films.get(film);

                // Film toevoegen aan bericht

                socialmediabericht.setFilm(filmNaam);
                socialmediabericht.setInhoud(String.format("%s is een leuke film!", filmNaam));

            } else if (filmOfCinemacomplex == 1) { // Cinemacomplex

                // Random cinemacomplex uit de lijst nemen

                int cinemacomplex = random.nextInt(cinemacomplexen.size());
                String cinemacomplexNaam = cinemacomplexen.get(cinemacomplex);

                // Cinemacomplex toevoegen aan bericht

                socialmediabericht.setCinemacomplex(cinemacomplexNaam);
                socialmediabericht.setInhoud(String.format("%s is gezellig!", cinemacomplexNaam));

            }

            // Bericht omzetten naar XML

            writer = new StringWriter();
            Marshaller.marshal(socialmediabericht, writer);

            // Bericht op de queue plaatsen

            TextMessage textMessage = session.createTextMessage(writer.toString());
            producer.send(textMessage);

        }

        // Alles afsluiten

        producer.close();
        session.close();
        connection.close();

    }

}

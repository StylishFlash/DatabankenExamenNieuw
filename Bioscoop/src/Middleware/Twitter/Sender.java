package Middleware.Twitter;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.*;


/**
 * Created with IntelliJ IDEA.
 * User: Joachim
 * Date: 3/11/12
 * Time: 21:15
 * To change this template use File | Settings | File Templates.
 */
public class Sender {

    public static void main(String[] args) throws JMSException, IOException {

        final String bestandsnaam = "TwitterBericht";
        final int aantalBerichten = 10;

        // Create a ConnectionFactory

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

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

        // Twitter berichten genereren

        //TODO: Twitter berichten genereren

        // Alle twitter berichten inlezen

        for (int i = 0; i < aantalBerichten; i++) {

            // XML file inlezen

            File file = new File(bestandsnaam + i + ".xml");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String lijn;

            while ((lijn = bufferedReader.readLine()) != null) {

                stringBuilder.append(lijn);

            }

            // Bericht op de queue plaatsen

            TextMessage textMessage = session.createTextMessage(stringBuilder.toString());
            producer.send(textMessage);

        }

        // Alles afsluiten

        producer.close();
        session.close();
        connection.close();

    }

}

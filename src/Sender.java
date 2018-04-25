//class for sending a certain message, now set in setMessage(), should come from the outside

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {

    public enum EntityType {visitor, admin, responsible};
    public enum SourceType {Front_End, Planning, Monitoring, Kassa, CRM, Facturatie};

    private final static String TASK_QUEUE_NAME = "planning-queue";
    private final static String EXCHANGE_NAME = "rabbitexchange";
    private static String classMessage = "";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        //factory.setHost("localhost");

        String username="test";
        String password="test";
        String virtualHost="/";
        String hostName="0:0:0:0:0:ffff:a03:3226";
        int portNumber=5672;

        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        factory.setHost(hostName);
        factory.setPort(portNumber);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //channel.exchangeDeclare(EXCHANGE_NAME, "fanout"); // other options: direct, topic, headers and fanout

        //channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);
        channel.queueBind(TASK_QUEUE_NAME, EXCHANGE_NAME, "");

        String message = setMessage();
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());


        System.out.println(" [x] Sending to exchange:   '" + EXCHANGE_NAME + "' message: '" + message + "'");

        channel.close();
        connection.close();
    }

    private static String ourHttpPostRequestMethod(UUID_Request this_UUID_Request)
    {

        //make new response object and use request properties
        UUID_Response dummy_UUID_Response;
        dummy_UUID_Response = new UUID_Response(this_UUID_Request);

        //save UUID to your local database

        //....


        //transform this_UUID_Request to String => XML!!

        String responseToString = dummy_UUID_Response.toString();

        return responseToString;


    }

    private static String setMessage() {
        //message is set up here

        //make new object for UUID_Request(int source_id, EntityType thisEntityType, MessageSource thisMessageSource)
        UUID_Request myLocalUUID_RequestObject = new UUID_Request(42, Sender.EntityType.admin,Sender.SourceType.Planning);

        //post request
        String myLocalUUID_Response_Object = ourHttpPostRequestMethod(myLocalUUID_RequestObject);

        //(handle request)



        return myLocalUUID_Response_Object;

    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}
import com.rabbitmq.client.*;

public class Sender {

    //private final static String QUEUE_NAME = "UUID";
    private final static String EXCHANGE_NAME = "rabbitexchange";
    private static String classMessage = "";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout"); // other options: direct, topic, headers and fanout

        //channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //channel.queueBind(TASK_QUEUE_NAME, EXCHANGE_NAME, "");

        String message = setMessage();
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        //channel.basicPublish("", "UUID", null, message.getBytes());
        System.out.println(" [x] Sending to exchange:   '" + EXCHANGE_NAME + "' message: '" + message + "'");

        channel.close();
        connection.close();
    }

    private static String ourHttpPostRequestMethod(UUID_Request this_UUID_Request)
    {
        //process link here effectively\



        //dummy return value

        //UUID_Response dummy_UUID_Response = new UUID_Response(UUID_Response.EntityType.this_UUID_Request.getSource_id(),this_UUID_Request.getEntity_type(),this_UUID_Request.getSource());



        return "Dummy response";


    }

    private static String setMessage() {
        //message is set up here

        //int source_id, EntityType thisEntityType, MessageSource thisMessageSource
        UUID_Request myLocalUUID_RequestObject = new UUID_Request(42, UUID_Request.EntityType.admin,UUID_Request.MessageSource.Planning);

        //post request

        String myLocalUUID_Response_Object = ourHttpPostRequestMethod(myLocalUUID_RequestObject);

        //handle request



        return myLocalUUID_Response_Object;

/*
        return "<SENDER:>Create event will come here!\r\n";
        return "<SENDER:>Update event will come here!";
        return "<SENDER:>Delete event will come here!";
        return "<SENDER:>No event will be created";

        if (strings.length() < 1)
            return "... Awesome Integration event string...";

        return "Something went wrong!";

        */
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
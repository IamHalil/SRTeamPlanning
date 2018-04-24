import com.rabbitmq.client.*;

public class Sender {

    private final static String QUEUE_NAME = "UUID";
    private final static String EXCHANGE_NAME = "rabbitexchange";



    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout"); // other options: direct, topic, headers and fanout

        //String queueName =
        //channel.queueBind(,"","");

        String message = getMessage(argv);
        channel.basicPublish("rabbitexchange", "", null, message.getBytes());
        System.out.println(" [x] Sending to exchange:  '" + message +"' message: '"  + message + "'");

        channel.close();
        connection.close();
    }

    private static String getMessage(String[] strings){
        //message is set up here

        if (strings.length < 1)
            return "... Awesome Integration event string...";
        return joinStrings(strings, " ");
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
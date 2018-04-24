import com.rabbitmq.client.*;

import java.io.IOException;
public class ReceiverPlanning {
    //    private final static String TASK1UUID_QUEUE_NAME = "UUID";


    private final static String TASK_QUEUE_NAME = "Planning";
    private final static String EXCHANGE_NAME = "rabbitexchange";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);
        channel.queueBind(TASK_QUEUE_NAME, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting with exchange for messages. To exit press CTRL+C");

        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                System.out.println(" [x] Received '" + message + "'");
                try {
                    doWork(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(" [x] Done");
                }
            }
        };
        boolean autoAck = true; // acknowledgment is covered below
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, consumer);
//        channel.basicConsume(TASK1UUID_QUEUE_NAME, autoAck, consumer);
    }
    private static void doWork(String task) throws InterruptedException {
        //Do work to do here

        System.out.println("<RECEIVER:>STARTING WORK\r\n");
        //What task?


        if(task.toLowerCase().contains("create event"))
        {
            System.out.println("<RECEIVER:>CREATE EVENT: fullmessage: "+task);
            System.out.println("WORKING HARD... For 5 seconds...\r\n");
            Thread.sleep(5000);
        }else if(task.toLowerCase().contains("update event")) {

            System.out.println("<RECEIVER:>UPDATE EVENT: fullmessage: " + task);
            System.out.println("WORKING HARD... For 5 seconds...\r\n");
            Thread.sleep(5000);
        }else if(task.toLowerCase().contains("dummy response")) {
            System.out.println("<RECEIVER:> This is a dummy message well received!... Chilling for 4.2 seconds...\r\n");
            Thread.sleep(4200);

        }else{

            System.out.println("<RECEIVER:> Nothing to do... Chilling for 10 seconds...\r\n");
            Thread.sleep(10000);
        }

        //Switch task

        //Create new event

        //End task

        System.out.println("END\r\n");
    }

}

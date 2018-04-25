import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiverCRM {

    //change task queue name to crm-queue, facturatie-queue, frontend-queue, kassa-queue, monitor-queue, planning-queue

    private final static String TASK_QUEUE_NAME = "crm-queue";
    private final static String EXCHANGE_NAME = "rabbitexchange";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();

        //factory.setHost("localhost");

        //https://www.rabbitmq.com/api-guide.html
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

        //ONLY DECLARE WHEN exchange/queue DOESN'T EXIST YET

        //channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //channel.queueDeclare(TASK_QUEUE_NAME, false, false, false, null);

        channel.queueBind(TASK_QUEUE_NAME, EXCHANGE_NAME, "");

        System.out.println(" [*] Waiting with exchange for messages. To exit press CTRL+C");

        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                System.out.println(" [x] Received a message @ '"+UUID_Response.getCurrentDateTimeStamp()+"'" + message + "'");
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

    }
    private static void doWork(String task) throws InterruptedException {

        System.out.println("<RECEIVER-CRM:>STARTING WORK\r\n");
        //What task?

        String lowerCaseTask = task.toLowerCase();

        if(lowerCaseTask.contains("create event"))
        {
            System.out.println("<RECEIVER-CRM:>CREATE EVENT: fullmessage: "+task);
            System.out.println("WORKING HARD... For 5 seconds...\n");
            Thread.sleep(5000);

        }else if(lowerCaseTask.contains("update event")) {

            System.out.println("<RECEIVER-CRM:>UPDATE EVENT: fullmessage: " + task);
            System.out.println("WORKING HARD... For 5 seconds...\n");
            Thread.sleep(5000);

        }else if(lowerCaseTask.contains("dummy response")) {

            System.out.println("<RECEIVER-CRM:> This is a dummy message well received!... Chilling for 4.2 seconds...\n");
            Thread.sleep(4200);

        }else if(task.toUpperCase().contains("UUID_RESPONSE")) {

            System.out.println("<RECEIVER-CRM:> NEW UUID RESPONSE!...");

            System.out.println("<RECEIVER-CRM:> This was the message: "+task+ " //END\n");

            System.out.println("<RECEIVER-CRM:> Chilling for 4.2 seconds...\n");
            Thread.sleep(4200);

        }else{

            System.out.println("<RECEIVER:> Nothing to do... Chilling for 10 seconds...\n");
            System.out.println("<RECEIVER:> This was the message: "+task+ " //END\n");
            Thread.sleep(10000);

        }

    }
}
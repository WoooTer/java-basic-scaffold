package wooter.rabbitmq.tutorials;

import com.rabbitmq.client.ConnectionFactory;

/**
 * https://www.rabbitmq.com/api-guide.html
 */
public class RabbitConfig {

    public static final String HOST = "192.168.1.157";
    public static final String USERNAME = "wooter";
    public static final String PASSWORD = "Wt123123123.";

    public static final ConnectionFactory factory = new ConnectionFactory();

    static {
        factory.setHost(RabbitConfig.HOST);
        factory.setUsername(RabbitConfig.USERNAME);
        factory.setPassword(RabbitConfig.PASSWORD);
    }

}


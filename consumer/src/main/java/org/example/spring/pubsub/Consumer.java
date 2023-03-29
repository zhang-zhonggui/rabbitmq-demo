package org.example.spring.pubsub;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhang
 */
public class Consumer {
    public static final String EXCHANGE_PUBSUB = "pubsub";
    private static final String PUBSUB_QUEUE = "pubsub-one";

    public static void main(String[] args) throws IOException, TimeoutException {
        //  1 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //  2 rabbitmq 的IP地址
        connectionFactory.setHost("192.168.110.110");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        //  3 创建connection 对象
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //  5 绑定交换机
        channel.exchangeDeclare(EXCHANGE_PUBSUB, "fanout");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, EXCHANGE_PUBSUB, "");
        channel.basicConsume(queue, true, (s, delivery) -> {
            System.out.println("消费者1消费的消息是：" + new String(delivery.getBody()));
        }, (CancelCallback) s -> {
            System.out.println("消费了");
        });
    }
}

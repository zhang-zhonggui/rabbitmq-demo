package org.example.spring.topice;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhang
 */
public class Consumer1 {
    public static final String TOPICS_EXCHANGE = "topics";

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
        channel.exchangeDeclare(TOPICS_EXCHANGE, "topic");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, TOPICS_EXCHANGE, "two.*");
        channel.basicConsume(queue, true, (s, delivery) -> {
            System.out.println("消费者1消费的消息是：" + new String(delivery.getBody()));
        }, (CancelCallback) s -> {
            System.out.println("消费了");
        });
    }
}

package org.example.spring.topics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhang
 */
public class Producer {
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
        //  4 创建chanel
        Channel channel = connection.createChannel();
        //  5 设置对象属性
        /**
         * 1.交换机名字
         * 2.交换机类型
         */
        channel.exchangeDeclare(TOPICS_EXCHANGE, "topic");
        //  6 发送消息
        /*
            1.交换机参数
            2.路由器 key
            3.消息属性
            4.消息内容
         */
        channel.basicPublish(TOPICS_EXCHANGE, "two.123", MessageProperties.PERSISTENT_TEXT_PLAIN, "Hello RabbitMQ".getBytes());
        //7 关闭资源
        channel.close();
        connection.close();


    }

}

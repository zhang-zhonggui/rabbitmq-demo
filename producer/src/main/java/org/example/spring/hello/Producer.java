package org.example.spring.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

/**
 * @author zhang
 */
public class Producer {
    private static final String HELLO_QUEUE = "hello";

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
        /*
           1.队列的名称
           2.队列是否持久化
           3.是否排他性
           4.是否自动删除消息（没有消费者自动删除）
           5.是否设置额外的参数
         */
        channel.queueDeclare(HELLO_QUEUE, false, false, false, null);
        //  6 发送消息
        /*
            1.交换机参数
            2.路由器 key
            3.消息属性
            4.消息内容
         */
        channel.basicPublish("", HELLO_QUEUE, null, "Hello RabbitMQ".getBytes());
        //7 关闭资源
        channel.close();
        connection.close();


    }

}

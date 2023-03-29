package org.example.spring.work;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhang
 */
public class Consumer {
    private static final String WORK_QUEUE = "work";

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
        //  5 设置对象属性
                /*
                   1.队列的名称
                   2.队列是否持久化
                   3.是否排他性
                   4.是否自动删除消息（没有消费者自动删除）
                   5.是否设置额外的参数
                 */
        channel.queueDeclare(WORK_QUEUE, false, false, false, null);
        channel.basicQos(1);
        //  6 取消息
                /*
                    1.队列名字
                    2.是否自动签收
                    3.消息属性
                    4.消息内容
                 */
        String s = channel.basicConsume(WORK_QUEUE, false, new DeliverCallback() {
            /**
             * 消费消息就在这之中
             *
             * @param s
             * @param delivery
             * @throws IOException
             */
            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("消费者1----消息内容为：" + new String(delivery.getBody()));
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

            }
        }, new CancelCallback() {
            /**
             *  当消息取消了回调这个函数
             * @param s
             * @throws IOException
             */
            @Override
            public void handle(String s) throws IOException {
                System.out.println("s");
            }
        });
    }
}

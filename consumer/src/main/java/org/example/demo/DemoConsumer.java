package org.example.demo;

import com.alibaba.fastjson2.JSON;
import org.example.domain.UserDemo;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhang
 */
@Component
public class DemoConsumer {


    @RabbitListener(bindings =
    @QueueBinding(
            value = @Queue(),
            exchange = @Exchange(value = "demo")
    ))
    public void process(String msg) {
        UserDemo userDemo = JSON.to(UserDemo.class, msg);
        System.out.println("消费者收到消息：" + userDemo.getPassword() + "================================" + userDemo.getUsername());
    }
}

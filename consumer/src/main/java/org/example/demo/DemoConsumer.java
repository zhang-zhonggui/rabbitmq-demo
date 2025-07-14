package org.example.demo;


import org.example.domain.UserDemo;
import org.example.utils.JacksonUtils;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
        Optional<UserDemo> userDemo = JacksonUtils.fromJson( msg,UserDemo.class);
        System.out.println("消费者收到消息：" + userDemo.get()
                .getPassword() + "================================" + userDemo.get().getUsername());
    }
}

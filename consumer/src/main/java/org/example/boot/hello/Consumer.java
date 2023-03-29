package org.example.boot.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhang
 */
@Component
public class Consumer {
    @RabbitListener(queuesToDeclare = @Queue("boot-hello"))
    public void process(String message) {
        System.out.println("Consumer receive message: " + message);
    }

}

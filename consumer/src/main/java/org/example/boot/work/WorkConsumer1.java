package org.example.boot.work;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @author zhang
 */
@Component
public class WorkConsumer1 {
    @RabbitListener(queuesToDeclare = @Queue("boot-work"))
    public void process(String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryId, Channel channel) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("消费者2 " + message);
        try {
            channel.basicAck(deliveryId, true);
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

}

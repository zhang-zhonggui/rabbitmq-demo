package org.example.boot.pubsub;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @author zhang
 */
@Component
public class PubsubConsumer1 {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "pubsub-queue-1"), exchange = @Exchange(value = "springboot-pubsub", type = ExchangeTypes.FANOUT)))
    public void process(String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryId, Channel channel) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("消费者2 " + message);
        try {
            channel.basicAck(deliveryId, true);
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

}

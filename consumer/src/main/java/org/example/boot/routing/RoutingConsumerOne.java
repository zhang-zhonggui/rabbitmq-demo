package org.example.boot.routing;

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
public class RoutingConsumerOne {
    @RabbitListener(bindings =
    @QueueBinding(
            value = @Queue(value = "springboot-routing-one"),
            exchange = @Exchange(value = "springboot-routing", type = ExchangeTypes.DIRECT),
            key = {"error", "info"}
    ))
    public void consumer(String msg, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryId, Channel channel) throws IOException {
        System.out.println("消费者1 " + msg);
        channel.basicAck(deliveryId, true);
    }
}

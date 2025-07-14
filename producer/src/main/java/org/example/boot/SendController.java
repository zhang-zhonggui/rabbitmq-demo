package org.example.boot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.UserDemo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 发送消息
 *
 * @author zhang
 */
@RestController
@RequestMapping
public class SendController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource
    private ObjectMapper objectMapper;


    /**
     * 发送hello
     * @param msg
     * @return
     */
    @RequestMapping("/sendHello")
    public String setHelloMsg(String msg) {
        rabbitTemplate.convertAndSend("", "boot-hello", "zzzzzzz");
        System.out.println(122312);
        return "发送成功";
    }
    @RequestMapping("/sendDemo")
    public String sendDemoMsg(String msg) {
        UserDemo userDemo = new UserDemo("zzg", "zzg");
        try {
            rabbitTemplate.convertAndSend("demo", "", objectMapper.writeValueAsString(userDemo));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return "发送成功";
    }

    @RequestMapping("/sendMsg")
    public String sendMsg(String msg) {
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.convertAndSend("", "boot-work", msg);
        }
        return "发送成功";
    }

    @RequestMapping("/sendPubSubMsg")
    public String sendPubSubMsg(String msg) {
        rabbitTemplate.convertAndSend("springboot-pubsub", "", msg);
        return "发送成功";
    }

    @RequestMapping("/sendRoutingMsg")
    public String sendRountingMsg(String msg, String key) {
        rabbitTemplate.convertAndSend("springboot-routing", key, msg);
        return "发送成功";
    }

    @RequestMapping("/sendTopicMsg")
    public String sendTopicMsg(String msg, String key) {
        rabbitTemplate.convertAndSend("springboot-topic", key, msg);
        return "发送成功";
    }
}

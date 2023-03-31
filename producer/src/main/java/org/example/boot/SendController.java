package org.example.boot;

import com.alibaba.fastjson2.JSON;
import org.example.domain.UserDemo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/sendDemo")
    public String sendDemoMsg(String msg) {

        UserDemo userDemo = new UserDemo("zzg", "zzg");
        rabbitTemplate.convertAndSend("demo", "", JSON.toJSONString(userDemo));
        return "发送成功";
    }


}

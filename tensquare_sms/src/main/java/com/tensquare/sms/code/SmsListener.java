package com.tensquare.sms.code;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 华韵流风
 * @ClassName SmsListener
 * @Date 2021/10/9 14:47
 * @packageName com.tensquare.sms
 * @Description TODO
 */
@Component
@RabbitListener(queues = "sms")
@RefreshScope
public class SmsListener {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 发送验证码
     *
     * @param map map
     */
    @RabbitHandler
    public void sendSms(Map<String, String> map) {
        String phoneNumber = map.get("phoneNumber");
        String code = map.get("code");
        System.out.println("手机号：" + phoneNumber);
        System.out.println("验证码：" + code);
        //保存
        redisTemplate.opsForValue().set("smscode_" + phoneNumber, code, 5, TimeUnit.MINUTES);

    }

}

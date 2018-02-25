package com.sapling.spiderMans.service.douban.Impl;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
/**
 * 
 * @ClassName: TestConsumer  
 * @Description: rabbitMq测试类  
 * @author 文泊山  
 * @date 2018年2月22日
 */
@Service
public class TestConsumer implements ChannelAwareMessageListener {
    private static final Logger logger = Logger.getLogger(TestConsumer.class);

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("receive queue:"+message.getMessageProperties().getReceivedRoutingKey()+", prority:"+message.getMessageProperties().getPriority());
        String result = new String(message.getBody(), "utf-8");
        System.out.println(result);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}

package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author THLiu
 */
@Component
public class Processors implements CommandLineRunner {

    @Autowired
    @Qualifier("output")
    MessageChannel output;

    @Autowired
    @Qualifier("outputOrder")
    MessageChannel outputOrder;

    @Autowired
    ProductProcessor productProcessor;

    // 监听 binding 为 Processor.INPUT 的消息

    @StreamListener(Processor.INPUT)
    public void input(Message<String> message) {
        System.out.println("一般监听收到：" + message.getPayload());
    }

    // 监听 binding 为 OrderIntf.INPUT_ORDER 的消息
    @StreamListener(OrderProcessor.INPUT_ORDER)
    public void inputOrder(Order order) {
        System.out.println("=====订单监听收到=====");
        System.out.println("订单编号：" + order.getOrderNum());
        System.out.println("订单类型：" + order.getType());
        System.out.println("订单数量：" + order.getNum());
        System.out.println("=====订单处理完成=====");
    }


    @StreamListener(ProductProcessor.INPUT_PRODUCT_ADD)
    public void inputProductAdd(Message<String> message) {
        System.out.println("新增产品监听收到：" + message.getPayload());
    }


    @Override
    public void run(String... strings) throws Exception {
        // 字符串类型发送MQ
        System.out.println("字符串信息发送");
        output.send(MessageBuilder.withPayload("大家好").build());

        // 使用 定义的接口的方式来发送
        System.out.println("新增产品发送");
        productProcessor.outputProductAdd().send(MessageBuilder.withPayload("添加一个产品").build());

        // 实体类型发送MQ
        System.out.println("订单实体发送");
        Order appleOrder = new Order();
        appleOrder.setOrderNum("0000001");
        appleOrder.setNum(10);
        appleOrder.setType("APPLE");
        appleOrder.setCreateAt(new Date());
        // 使用 注入 MessageChannel 方式发送
        outputOrder.send(MessageBuilder.withPayload(appleOrder).build());
    }

}

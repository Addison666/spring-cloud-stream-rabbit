package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author THLiu
 */
@Component
@EnableBinding({Processor.class, OrderProcessor.class, ProductProcessor.class})
public class config {
    @Autowired
    @Qualifier("output")
    MessageChannel output;

    @Bean
    @InboundChannelAdapter(value = Processor.OUTPUT, poller = @Poller(fixedDelay = "3000", maxMessagesPerPoll = "1"))
    public MessageSource<String> timerMessageSource() {
        return () -> MessageBuilder.withPayload("短消息-" + new Date()).build();
    }
}

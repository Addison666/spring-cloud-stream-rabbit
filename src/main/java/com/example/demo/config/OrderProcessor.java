package com.example.demo.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author THLiu
 */
public interface OrderProcessor {
    String INPUT_ORDER = "inputOrder";
    String OUTPUT_ORDER = "outputOrder";

    @Input(INPUT_ORDER)
    SubscribableChannel inputOrder();

    @Output(OUTPUT_ORDER)
    MessageChannel outputOrder();
}

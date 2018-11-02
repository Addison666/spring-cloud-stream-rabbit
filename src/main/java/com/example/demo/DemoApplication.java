package com.example.demo;

import com.example.demo.config.OrderProcessor;
import com.example.demo.config.ProductProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;


@SpringBootApplication
@EnableBinding({Processor.class, OrderProcessor.class, ProductProcessor.class})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    //todo:mvn clean install -Dmaven.test.skip=true
}

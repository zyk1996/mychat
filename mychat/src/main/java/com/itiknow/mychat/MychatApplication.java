package com.itiknow.mychat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MychatApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext configurableApplicationContext=SpringApplication.run(MychatApplication.class, args);

    }

}

package com.heroys6.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Server implementation
 */
@SpringBootApplication
public class SpringServer extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringServer.class, args);
    }

}

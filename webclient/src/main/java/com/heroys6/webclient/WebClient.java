package com.heroys6.webclient;

import com.heroys6.webclient.controller.UserRestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Web client implementation. Consist of RestAPI in {@link UserRestController}
 * and static html web page(index.html) that able to work with that API.
 */
@SpringBootApplication
public class WebClient extends SpringBootServletInitializer {

    public static void main(String[] args) {
        String s;
        SpringApplication.run(WebClient.class, args);
    }

}

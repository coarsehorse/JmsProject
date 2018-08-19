package com.heroys6.webclient.controller;

import com.heroys6.library.model.Request;
import com.heroys6.library.model.RequestWithID;
import com.heroys6.webclient.WebClient;
import com.heroys6.webclient.activemq.sender.Sender;
import com.heroys6.webclient.storage.ResponseStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


/**
 * Simple RestAPI controller that handles both GET and POST requests.
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    Sender sender;

    @Autowired
    ResponseStorage respStorage;

    @Value("${queue.webclient-server}")
    String webclientServer;

    private static final Logger log = LoggerFactory.getLogger(WebClient.class);


    @PostMapping
    String makeAction(@RequestBody Request request) {
        log.info("Handled POST request='{}'", request.toString());
        /* Generate uniq task id */
        int uniqId = Long.valueOf(System.currentTimeMillis()).hashCode();
        /* Send ActiveMQ message */
        sender.send(new RequestWithID(uniqId, request), webclientServer);
        /* Register new request in local storage */
        respStorage.getMap().put(uniqId, "-");
        return String.valueOf(uniqId);
    }

    @GetMapping
    String getAnswer(@RequestParam(value = "uniqId", defaultValue = "-") String uniqId) {
        log.info("Handled GET request with parameter='{}'", uniqId);
        if (uniqId.equals("-")) {
            return "-";
        }
        //"<html><body><h1>You've got it!</h1></body></html>"
        return respStorage.getMap().getOrDefault(Integer.valueOf(uniqId), "-");
    }
}



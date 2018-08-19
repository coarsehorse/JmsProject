package com.heroys6.server.activemq.sender;

import com.heroys6.library.model.ResponseWithID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * It used for sending ActiveMQ messages.
 */
@Component
public class Sender {

    private static final Logger log = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(ResponseWithID resWithID, String destination) {
        log.info("Sending response='{}' to '{}'", resWithID, destination);
        jmsTemplate.convertAndSend(destination, resWithID);
    }
}

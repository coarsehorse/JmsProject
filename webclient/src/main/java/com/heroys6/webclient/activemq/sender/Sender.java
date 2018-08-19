package com.heroys6.webclient.activemq.sender;

import com.heroys6.library.model.RequestWithID;
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

    public void send(RequestWithID reqWithID, String destination) {
        log.info("Sending request='{}' to '{}'", reqWithID, destination);
        jmsTemplate.convertAndSend(destination, reqWithID);
    }
}

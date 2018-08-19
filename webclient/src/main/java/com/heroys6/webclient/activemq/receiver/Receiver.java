package com.heroys6.webclient.activemq.receiver;

import com.heroys6.library.model.ResponseWithID;
import com.heroys6.webclient.activemq.sender.Sender;
import com.heroys6.webclient.storage.ResponseStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * It used for receiving and process ActiveMQ messages.
 */
@Component
public class Receiver {

    @Autowired
    ResponseStorage respStorage;

    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    Sender sender;

    @Value("${queue.server-webclient}")
    String serverWebclient;

    @JmsListener(destination = "${queue.server-webclient}")
    public void receive(ResponseWithID resWithID) {
        log.info("Received new response='{}'", resWithID.toString());
        respStorage.getMap().put(resWithID.getUniqID(), resWithID.getStatusMessage());
    }
}

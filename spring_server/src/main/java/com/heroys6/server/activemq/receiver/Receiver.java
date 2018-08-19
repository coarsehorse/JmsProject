package com.heroys6.server.activemq.receiver;

import com.heroys6.library.model.RequestWithID;
import com.heroys6.library.model.ResponseWithID;
import com.heroys6.library.model.User;
import com.heroys6.server.activemq.sender.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * It used for receiving and process ActiveMQ messages.
 */
@Component
public class Receiver {

    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Sender sender;

    @Value("${queue.server-webclient}")
    String serverWebclient;

    @JmsListener(destination = "${queue.webclient-server}")
    public void receive(RequestWithID reqWithID) {
        log.info("Received new request = '{}'", reqWithID.getRequest().toString());
        switch (reqWithID.getRequest().getAction()) {
            case GET:
                try {
                    List<User> foundUsers = new ArrayList<>(jdbcTemplate.query(
                            "SELECT name, surname FROM customers WHERE name = ?",
                            new Object[]{reqWithID.getRequest().getUser().getName()},
                            (row, i) -> new User(row.getString("name"), row.getString("surname"))));

                    if (foundUsers.size() == 0) {
                        String message = "User \""
                                + reqWithID.getRequest().getUser().getName() + " "
                                + reqWithID.getRequest().getUser().getSurname() + "\" "
                                + "not found!";
                        log.info(message);
                        sender.send(new ResponseWithID(reqWithID.getIniqId(), message), serverWebclient);
                    } else {
                        User u = foundUsers.get(0);
                        String message = "Found user \"" + u.getName() + " " + u.getSurname() + "\"";
                        log.info(message);
                        sender.send(new ResponseWithID(reqWithID.getIniqId(), message), serverWebclient);
                    }
                } catch (DataAccessException e) {
                    log.error("Error='{}' for request='{}'", e.getMessage(), reqWithID);
                    sender.send(new ResponseWithID(reqWithID.getIniqId(),
                            "An error has occurred during searching for the user: " + e.getMessage()),
                            serverWebclient);
                }
                break;
            case ADD:
                try {
                    jdbcTemplate.update("INSERT INTO customers(name, surname) VALUES (?,?)",
                            reqWithID.getRequest().getUser().getName(),
                            reqWithID.getRequest().getUser().getSurname());
                    String add_message = "User \""
                            + reqWithID.getRequest().getUser().getName() + " "
                            + reqWithID.getRequest().getUser().getSurname() + "\" "
                            + "successfully added!";
                    log.info(add_message);
                    sender.send(new ResponseWithID(reqWithID.getIniqId(), add_message), serverWebclient);
                } catch (DataAccessException e) {
                    log.error("Error='{}' for request='{}'", e.getMessage(), reqWithID);
                    sender.send(new ResponseWithID(reqWithID.getIniqId(),
                            "An error has occurred during adding new user: " + e.getMessage()),
                            serverWebclient);
                }
                break;
            case DELETE:
                try {
                    jdbcTemplate.update("DELETE FROM customers WHERE name = ? AND surname = ?",
                            reqWithID.getRequest().getUser().getName(), reqWithID.getRequest().getUser().getSurname());
                    jdbcTemplate.update("DELETE FROM oll");
                    String del_message = "User \""
                            + reqWithID.getRequest().getUser().getName() + " "
                            + reqWithID.getRequest().getUser().getSurname() + "\" "
                            + "successfully deleted!";
                    log.info(del_message);
                    sender.send(new ResponseWithID(reqWithID.getIniqId(), del_message), serverWebclient);
                } catch (DataAccessException e) {
                    log.error("Error='{}' for request='{}'", e.getMessage(), reqWithID);
                    sender.send(new ResponseWithID(reqWithID.getIniqId(),
                            "An error has occurred during deleting user: " + e.getMessage()),
                            serverWebclient);
                }
                break;
        }
    }
}

package com.heroys6.webclient.storage;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Simple inmemory storage implementation.
 */
@Component
public class ResponseStorage {

    private HashMap<Integer, String> respStorage = new HashMap<>();

    public HashMap<Integer, String> getMap() {
        return respStorage;
    }
}

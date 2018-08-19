package com.heroys6.library.model;

import java.util.Objects;

/**
 * Represents request type.
 */
public class Request {

    private Action action;
    private User user;

    public Request() {}

    public Request(Action action, User user) {
        this.action = action;
        this.user = user;
    }

    public Action getAction() {
        return action;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return action == request.action &&
                Objects.equals(user, request.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(action, user);
    }

    @Override
    public String toString() {
        return "Request{" +
                "action=" + action +
                ", user=" + user +
                '}';
    }
}

package com.heroys6.library.model;

import java.util.Objects;

/**
 * Represents type of request with uniqId.
 */
public class RequestWithID {
    private Integer iniqId;
    private Request request;

    public RequestWithID() {}

    public RequestWithID(Integer iniqId, Request request) {
        this.iniqId = iniqId;
        this.request = request;
    }

    public Integer getIniqId() {
        return iniqId;
    }

    public Request getRequest() {
        return request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestWithID)) return false;
        RequestWithID that = (RequestWithID) o;
        return Objects.equals(iniqId, that.iniqId) &&
                Objects.equals(request, that.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iniqId, request);
    }

    @Override
    public String toString() {
        return "RequestWithID{" +
                "iniqId=" + iniqId +
                ", request=" + request +
                '}';
    }
}

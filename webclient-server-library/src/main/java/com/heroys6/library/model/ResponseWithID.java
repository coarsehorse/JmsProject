package com.heroys6.library.model;

import java.util.Objects;

/**
 * Represents type of response with uniqId.
 */
public class ResponseWithID {

    private Integer uniqID;
    private String statusMessage;

    public ResponseWithID() {}

    public ResponseWithID(Integer uniqID, String statusMessage) {
        this.uniqID = uniqID;
        this.statusMessage = statusMessage;
    }

    public Integer getUniqID() {
        return uniqID;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseWithID)) return false;
        ResponseWithID that = (ResponseWithID) o;
        return Objects.equals(uniqID, that.uniqID) &&
                Objects.equals(statusMessage, that.statusMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqID, statusMessage);
    }

    @Override
    public String toString() {
        return "ResponseWithID{" +
                "uniqID=" + uniqID +
                ", statusMessage='" + statusMessage + '\'' +
                '}';
    }
}

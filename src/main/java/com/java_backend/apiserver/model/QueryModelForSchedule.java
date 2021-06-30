package com.java_backend.apiserver.model;

import java.util.Objects;

public class QueryModelForSchedule  {
    private String userID;
    private String sortByField;
    private boolean sortOrder;
    private String status;


    public QueryModelForSchedule() {
    }

    public QueryModelForSchedule(String userID, String sortByField, boolean sortOrder) {
        this.userID = userID;
        this.sortByField = sortByField;
        this.sortOrder = sortOrder;
    }

    public QueryModelForSchedule(String userID, String sortByField, boolean sortOrder, String status) {
        this.userID = userID;
        this.sortByField = sortByField;
        this.sortOrder = sortOrder;
        this.status = status;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSortByField() {
        return this.sortByField;
    }

    public void setSortByField(String sortByField) {
        this.sortByField = sortByField;
    }

    public boolean isSortOrder() {
        return this.sortOrder;
    }

    public boolean getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(boolean sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public QueryModelForSchedule userID(String userID) {
        setUserID(userID);
        return this;
    }

    public QueryModelForSchedule sortByField(String sortByField) {
        setSortByField(sortByField);
        return this;
    }

    public QueryModelForSchedule sortOrder(boolean sortOrder) {
        setSortOrder(sortOrder);
        return this;
    }

    public QueryModelForSchedule status(String status) {
        setStatus(status);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof QueryModelForSchedule)) {
            return false;
        }
        QueryModelForSchedule queryModelForSchedule = (QueryModelForSchedule) o;
        return Objects.equals(userID, queryModelForSchedule.userID) && Objects.equals(sortByField, queryModelForSchedule.sortByField) && sortOrder == queryModelForSchedule.sortOrder && Objects.equals(status, queryModelForSchedule.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, sortByField, sortOrder, status);
    }

    @Override
    public String toString() {
        return "{" +
            " userID='" + getUserID() + "'" +
            ", sortByField='" + getSortByField() + "'" +
            ", sortOrder='" + isSortOrder() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
    

}

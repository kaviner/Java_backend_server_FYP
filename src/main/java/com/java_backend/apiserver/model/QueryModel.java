package com.java_backend.apiserver.model;

import java.util.Objects;

public class QueryModel {
    private String userID;
    private String sortByField;
    private boolean sortOrder;

    public QueryModel() {
    }

    public QueryModel(String userID, String sortByField, boolean sortOrder) {
        this.userID = userID;
        this.sortByField = sortByField;
        this.sortOrder = sortOrder;
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

    public QueryModel userID(String userID) {
        setUserID(userID);
        return this;
    }

    public QueryModel sortByField(String sortByField) {
        setSortByField(sortByField);
        return this;
    }

    public QueryModel sortOrder(boolean sortOrder) {
        setSortOrder(sortOrder);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof QueryModel)) {
            return false;
        }
        QueryModel queryModel = (QueryModel) o;
        return Objects.equals(userID, queryModel.userID) && Objects.equals(sortByField, queryModel.sortByField) && sortOrder == queryModel.sortOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, sortByField, sortOrder);
    }

    @Override
    public String toString() {
        return "{" +
            " userID='" + getUserID() + "'" +
            ", sortByField='" + getSortByField() + "'" +
            ", sortOrder='" + isSortOrder() + "'" +
            "}";
    }



  
}

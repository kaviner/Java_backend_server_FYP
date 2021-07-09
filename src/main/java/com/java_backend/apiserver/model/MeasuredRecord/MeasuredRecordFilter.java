package com.java_backend.apiserver.model.MeasuredRecord;

import java.util.Objects;

import org.bson.types.ObjectId;

public class MeasuredRecordFilter {
    private String userID;
    private String sortByField;
    private Boolean sortOrder;


    public MeasuredRecordFilter() {
    }

    public MeasuredRecordFilter(String userID, String sortByField, Boolean sortOrder) {
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

    public Boolean isSortOrder() {
        return this.sortOrder;
    }

    public Boolean getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(Boolean sortOrder) {
        this.sortOrder = sortOrder;
    }

    public MeasuredRecordFilter userID(String userID) {
        setUserID(userID);
        return this;
    }

    public MeasuredRecordFilter sortByField(String sortByField) {
        setSortByField(sortByField);
        return this;
    }

    public MeasuredRecordFilter sortOrder(Boolean sortOrder) {
        setSortOrder(sortOrder);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MeasuredRecordFilter)) {
            return false;
        }
        MeasuredRecordFilter measuredRecordFilter = (MeasuredRecordFilter) o;
        return Objects.equals(userID, measuredRecordFilter.userID) && Objects.equals(sortByField, measuredRecordFilter.sortByField) && Objects.equals(sortOrder, measuredRecordFilter.sortOrder);
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

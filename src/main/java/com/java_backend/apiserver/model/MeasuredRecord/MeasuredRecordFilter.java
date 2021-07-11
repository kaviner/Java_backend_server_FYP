package com.java_backend.apiserver.model.MeasuredRecord;

import java.util.Objects;

import org.bson.types.ObjectId;

public class MeasuredRecordFilter {
    private String userID;
    private String sortByField;
    private Boolean sortOrder;
    private String category;



    public MeasuredRecordFilter() {
    }

    public MeasuredRecordFilter(String userID, String sortByField, Boolean sortOrder, String category) {
        this.userID = userID;
        this.sortByField = sortByField;
        this.sortOrder = sortOrder;
        this.category = category;
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

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public MeasuredRecordFilter category(String category) {
        setCategory(category);
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
        return Objects.equals(userID, measuredRecordFilter.userID) && Objects.equals(sortByField, measuredRecordFilter.sortByField) && Objects.equals(sortOrder, measuredRecordFilter.sortOrder) && Objects.equals(category, measuredRecordFilter.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, sortByField, sortOrder, category);
    }

    @Override
    public String toString() {
        return "{" +
            " userID='" + getUserID() + "'" +
            ", sortByField='" + getSortByField() + "'" +
            ", sortOrder='" + isSortOrder() + "'" +
            ", category='" + getCategory() + "'" +
            "}";
    }
   

  
}

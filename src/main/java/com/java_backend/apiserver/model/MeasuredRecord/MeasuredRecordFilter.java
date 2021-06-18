package com.java_backend.apiserver.model.MeasuredRecord;

import java.util.Objects;

import org.bson.types.ObjectId;

public class MeasuredRecordFilter {
    private String recordOwner;
    private String sortByFieldName;
    private String startDateTime;
    private String endDateTime;
    private Boolean isAscending;


    public MeasuredRecordFilter() {
    }

    public MeasuredRecordFilter(String recordOwner, String sortByFieldName, String startDateTime, String endDateTime, Boolean isAscending) {
        this.recordOwner = recordOwner;
        this.sortByFieldName = sortByFieldName;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isAscending = isAscending;
    }

    public String getRecordOwner() {
        return this.recordOwner;
    }

    public void setRecordOwner(String recordOwner) {
        this.recordOwner = recordOwner;
    }

    public String getSortByFieldName() {
        return this.sortByFieldName;
    }

    public void setSortByFieldName(String sortByFieldName) {
        this.sortByFieldName = sortByFieldName;
    }

    public String getStartDateTime() {
        return this.startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return this.endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Boolean isIsAscending() {
        return this.isAscending;
    }

    public Boolean getIsAscending() {
        return this.isAscending;
    }

    public void setIsAscending(Boolean isAscending) {
        this.isAscending = isAscending;
    }

    public MeasuredRecordFilter recordOwner(String recordOwner) {
        setRecordOwner(recordOwner);
        return this;
    }

    public MeasuredRecordFilter sortByFieldName(String sortByFieldName) {
        setSortByFieldName(sortByFieldName);
        return this;
    }

    public MeasuredRecordFilter startDateTime(String startDateTime) {
        setStartDateTime(startDateTime);
        return this;
    }

    public MeasuredRecordFilter endDateTime(String endDateTime) {
        setEndDateTime(endDateTime);
        return this;
    }

    public MeasuredRecordFilter isAscending(Boolean isAscending) {
        setIsAscending(isAscending);
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
        return Objects.equals(recordOwner, measuredRecordFilter.recordOwner) && Objects.equals(sortByFieldName, measuredRecordFilter.sortByFieldName) && Objects.equals(startDateTime, measuredRecordFilter.startDateTime) && Objects.equals(endDateTime, measuredRecordFilter.endDateTime) && Objects.equals(isAscending, measuredRecordFilter.isAscending);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordOwner, sortByFieldName, startDateTime, endDateTime, isAscending);
    }

    @Override
    public String toString() {
        return "{" +
            " recordOwner='" + getRecordOwner() + "'" +
            ", sortByFieldName='" + getSortByFieldName() + "'" +
            ", startDateTime='" + getStartDateTime() + "'" +
            ", endDateTime='" + getEndDateTime() + "'" +
            ", isAscending='" + isIsAscending() + "'" +
            "}";
    }

  
}

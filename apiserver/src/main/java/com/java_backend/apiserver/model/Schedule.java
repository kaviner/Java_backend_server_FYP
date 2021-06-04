package com.java_backend.apiserver.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class Schedule {

    private String _id;
    private String ownerInUserCollection;
    private String activityIdInActivityCollection;
    private Date startDateTime;
    private Date endDateTime;
    private boolean isAlert;
    private List<Map<String,Integer>> alertValue = new ArrayList<Map<String,Integer>>();
    private String status;
    
    public Schedule() {
    }

    public Schedule(String _id, String ownerInUserCollection, String activityIdInActivityCollection, Date startDateTime, Date endDateTime, boolean isAlert, List<Map<String,Integer>> alertValue, String status) {
        this._id = _id;
        this.ownerInUserCollection = ownerInUserCollection;
        this.activityIdInActivityCollection = activityIdInActivityCollection;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isAlert = isAlert;
        this.alertValue = alertValue;
        this.status = status;
    }

    public String get_id() {
        return this._id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getOwnerInUserCollection() {
        return this.ownerInUserCollection;
    }

    public void setOwnerInUserCollection(String ownerInUserCollection) {
        this.ownerInUserCollection = ownerInUserCollection;
    }

    public String getActivityIdInActivityCollection() {
        return this.activityIdInActivityCollection;
    }

    public void setActivityIdInActivityCollection(String activityIdInActivityCollection) {
        this.activityIdInActivityCollection = activityIdInActivityCollection;
    }

    public Date getStartDateTime() {
        return this.startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return this.endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public boolean isIsAlert() {
        return this.isAlert;
    }

    public boolean getIsAlert() {
        return this.isAlert;
    }

    public void setIsAlert(boolean isAlert) {
        this.isAlert = isAlert;
    }

    public List<Map<String,Integer>> getAlertValue() {
        return this.alertValue;
    }

    public void setAlertValue(List<Map<String,Integer>> alertValue) {
        this.alertValue = alertValue;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Schedule _id(String _id) {
        set_id(_id);
        return this;
    }

    public Schedule ownerInUserCollection(String ownerInUserCollection) {
        setOwnerInUserCollection(ownerInUserCollection);
        return this;
    }

    public Schedule activityIdInActivityCollection(String activityIdInActivityCollection) {
        setActivityIdInActivityCollection(activityIdInActivityCollection);
        return this;
    }

    public Schedule startDateTime(Date startDateTime) {
        setStartDateTime(startDateTime);
        return this;
    }

    public Schedule endDateTime(Date endDateTime) {
        setEndDateTime(endDateTime);
        return this;
    }

    public Schedule isAlert(boolean isAlert) {
        setIsAlert(isAlert);
        return this;
    }

    public Schedule alertValue(List<Map<String,Integer>> alertValue) {
        setAlertValue(alertValue);
        return this;
    }

    public Schedule status(String status) {
        setStatus(status);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Schedule)) {
            return false;
        }
        Schedule schedule = (Schedule) o;
        return Objects.equals(_id, schedule._id) && Objects.equals(ownerInUserCollection, schedule.ownerInUserCollection) && Objects.equals(activityIdInActivityCollection, schedule.activityIdInActivityCollection) && Objects.equals(startDateTime, schedule.startDateTime) && Objects.equals(endDateTime, schedule.endDateTime) && isAlert == schedule.isAlert && Objects.equals(alertValue, schedule.alertValue) && Objects.equals(status, schedule.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, ownerInUserCollection, activityIdInActivityCollection, startDateTime, endDateTime, isAlert, alertValue, status);
    }

    @Override
    public String toString() {
        return "{" +
            " _id='" + get_id() + "'" +
            ", ownerInUserCollection='" + getOwnerInUserCollection() + "'" +
            ", activityIdInActivityCollection='" + getActivityIdInActivityCollection() + "'" +
            ", startDateTime='" + getStartDateTime() + "'" +
            ", endDateTime='" + getEndDateTime() + "'" +
            ", isAlert='" + isIsAlert() + "'" +
            ", alertValue='" + getAlertValue() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }

    

}

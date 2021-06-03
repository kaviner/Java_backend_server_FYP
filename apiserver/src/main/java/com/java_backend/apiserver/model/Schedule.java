package com.java_backend.apiserver.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import org.bson.types.ObjectId;

public class Schedule {

    private ObjectId _id;
    private Date startDateTime;
    private Date endDateTime;
    private boolean isAlert;
    private HashMap<String, Integer> alertValue = new HashMap<String, Integer>();
    private ObjectId activityIdInActivityCollection;
    private String status;


    public Schedule() {
    }

    public Schedule(ObjectId _id, Date startDateTime, Date endDateTime, boolean isAlert, HashMap<String,Integer> alertValue, ObjectId activityIdInActivityCollection, String status) {
        this._id = _id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isAlert = isAlert;
        this.alertValue = alertValue;
        this.activityIdInActivityCollection = activityIdInActivityCollection;
        this.status = status;
    }

    public ObjectId get_id() {
        return this._id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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

    public HashMap<String,Integer> getAlertValue() {
        return this.alertValue;
    }

    public void setAlertValue(HashMap<String,Integer> alertValue) {
        this.alertValue = alertValue;
    }

    public ObjectId getActivityIdInActivityCollection() {
        return this.activityIdInActivityCollection;
    }

    public void setActivityIdInActivityCollection(ObjectId activityIdInActivityCollection) {
        this.activityIdInActivityCollection = activityIdInActivityCollection;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Schedule _id(ObjectId _id) {
        set_id(_id);
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

    public Schedule alertValue(HashMap<String,Integer> alertValue) {
        setAlertValue(alertValue);
        return this;
    }

    public Schedule activityIdInActivityCollection(ObjectId activityIdInActivityCollection) {
        setActivityIdInActivityCollection(activityIdInActivityCollection);
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
        return Objects.equals(_id, schedule._id) && Objects.equals(startDateTime, schedule.startDateTime) && Objects.equals(endDateTime, schedule.endDateTime) && isAlert == schedule.isAlert && Objects.equals(alertValue, schedule.alertValue) && Objects.equals(activityIdInActivityCollection, schedule.activityIdInActivityCollection) && Objects.equals(status, schedule.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, startDateTime, endDateTime, isAlert, alertValue, activityIdInActivityCollection, status);
    }

    @Override
    public String toString() {
        return "{" +
            " _id='" + get_id() + "'" +
            ", startDateTime='" + getStartDateTime() + "'" +
            ", endDateTime='" + getEndDateTime() + "'" +
            ", isAlert='" + isIsAlert() + "'" +
            ", alertValue='" + getAlertValue() + "'" +
            ", activityIdInActivityCollection='" + getActivityIdInActivityCollection() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }


}

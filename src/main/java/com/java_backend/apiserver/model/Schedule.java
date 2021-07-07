package com.java_backend.apiserver.model;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;


public class Schedule {

    private String _id;
    private String ownerInUserCollection;
    private String activityIdInActivityCollection;
    private String activityName;
    private String category;
    @JsonFormat(locale="zh", timezone="UTC", pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date startDateTime;
    @JsonFormat(locale="zh", timezone="UTC", pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date endDateTime;
    private String status;
    

    public Schedule() {
    }

    public Schedule(String _id, String ownerInUserCollection, String activityIdInActivityCollection, String activityName, String category, Date startDateTime, Date endDateTime, String status) {
        this._id = _id;
        this.ownerInUserCollection = ownerInUserCollection;
        this.activityIdInActivityCollection = activityIdInActivityCollection;
        this.activityName = activityName;
        this.category = category;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
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

    public String getActivityName() {
        return this.activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public Schedule activityName(String activityName) {
        setActivityName(activityName);
        return this;
    }

    public Schedule category(String category) {
        setCategory(category);
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
        return Objects.equals(_id, schedule._id) && Objects.equals(ownerInUserCollection, schedule.ownerInUserCollection) && Objects.equals(activityIdInActivityCollection, schedule.activityIdInActivityCollection) && Objects.equals(activityName, schedule.activityName) && Objects.equals(category, schedule.category) && Objects.equals(startDateTime, schedule.startDateTime) && Objects.equals(endDateTime, schedule.endDateTime) && Objects.equals(status, schedule.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, ownerInUserCollection, activityIdInActivityCollection, activityName, category, startDateTime, endDateTime, status);
    }

    @Override
    public String toString() {
        return "{" +
            " _id='" + get_id() + "'" +
            ", ownerInUserCollection='" + getOwnerInUserCollection() + "'" +
            ", activityIdInActivityCollection='" + getActivityIdInActivityCollection() + "'" +
            ", activityName='" + getActivityName() + "'" +
            ", category='" + getCategory() + "'" +
            ", startDateTime='" + getStartDateTime() + "'" +
            ", endDateTime='" + getEndDateTime() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
   

  
    

}

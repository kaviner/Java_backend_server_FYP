package com.java_backend.apiserver.model;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MonitoringRelationship_1To1 {
    private String host_ID;
    private String targetID;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private Date relationshipStartDate; 
    private String status;
    private String pairCode;


    public MonitoringRelationship_1To1() {
    }

    public MonitoringRelationship_1To1(String host_ID, String targetID, Date relationshipStartDate, String status, String pairCode) {
        this.host_ID = host_ID;
        this.targetID = targetID;
        this.relationshipStartDate = relationshipStartDate;
        this.status = status;
        this.pairCode = pairCode;
    }

    public String getHost_ID() {
        return this.host_ID;
    }

    public void setHost_ID(String host_ID) {
        this.host_ID = host_ID;
    }

    public String getTargetID() {
        return this.targetID;
    }

    public void setTargetID(String targetID) {
        this.targetID = targetID;
    }

    public Date getRelationshipStartDate() {
        return this.relationshipStartDate;
    }

    public void setRelationshipStartDate(Date relationshipStartDate) {
        this.relationshipStartDate = relationshipStartDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPairCode() {
        return this.pairCode;
    }

    public void setPairCode(String pairCode) {
        this.pairCode = pairCode;
    }

    public MonitoringRelationship_1To1 host_ID(String host_ID) {
        setHost_ID(host_ID);
        return this;
    }

    public MonitoringRelationship_1To1 targetID(String targetID) {
        setTargetID(targetID);
        return this;
    }

    public MonitoringRelationship_1To1 relationshipStartDate(Date relationshipStartDate) {
        setRelationshipStartDate(relationshipStartDate);
        return this;
    }

    public MonitoringRelationship_1To1 status(String status) {
        setStatus(status);
        return this;
    }

    public MonitoringRelationship_1To1 pairCode(String pairCode) {
        setPairCode(pairCode);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MonitoringRelationship_1To1)) {
            return false;
        }
        MonitoringRelationship_1To1 monitoringRelationship_1To1 = (MonitoringRelationship_1To1) o;
        return Objects.equals(host_ID, monitoringRelationship_1To1.host_ID) && Objects.equals(targetID, monitoringRelationship_1To1.targetID) && Objects.equals(relationshipStartDate, monitoringRelationship_1To1.relationshipStartDate) && Objects.equals(status, monitoringRelationship_1To1.status) && Objects.equals(pairCode, monitoringRelationship_1To1.pairCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host_ID, targetID, relationshipStartDate, status, pairCode);
    }

    @Override
    public String toString() {
        return "{" +
            " host_ID='" + getHost_ID() + "'" +
            ", targetID='" + getTargetID() + "'" +
            ", relationshipStartDate='" + getRelationshipStartDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", pairCode='" + getPairCode() + "'" +
            "}";
    }

    
}

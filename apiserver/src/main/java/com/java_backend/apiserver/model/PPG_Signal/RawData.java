package com.java_backend.apiserver.model.PPG_Signal;

import java.util.Date;
import java.util.Objects;

public class RawData {
    private double ppg1,ppg2,ppg3;
    private Date timestamp;


    public RawData() {
    }

    public RawData(double ppg1, double ppg2, double ppg3, Date timestamp) {
        this.ppg1 = ppg1;
        this.ppg2 = ppg2;
        this.ppg3 = ppg3;
        this.timestamp = timestamp;
    }

    public double getPpg1() {
        return this.ppg1;
    }

    public void setPpg1(double ppg1) {
        this.ppg1 = ppg1;
    }

    public double getPpg2() {
        return this.ppg2;
    }

    public void setPpg2(double ppg2) {
        this.ppg2 = ppg2;
    }

    public double getPpg3() {
        return this.ppg3;
    }

    public void setPpg3(double ppg3) {
        this.ppg3 = ppg3;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public RawData ppg1(double ppg1) {
        setPpg1(ppg1);
        return this;
    }

    public RawData ppg2(double ppg2) {
        setPpg2(ppg2);
        return this;
    }

    public RawData ppg3(double ppg3) {
        setPpg3(ppg3);
        return this;
    }

    public RawData timestamp(Date timestamp) {
        setTimestamp(timestamp);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RawData)) {
            return false;
        }
        RawData rawData = (RawData) o;
        return ppg1 == rawData.ppg1 && ppg2 == rawData.ppg2 && ppg3 == rawData.ppg3 && Objects.equals(timestamp, rawData.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ppg1, ppg2, ppg3, timestamp);
    }

    @Override
    public String toString() {
        return "{" +
            " ppg1='" + getPpg1() + "'" +
            ", ppg2='" + getPpg2() + "'" +
            ", ppg3='" + getPpg3() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            "}";
    }


}

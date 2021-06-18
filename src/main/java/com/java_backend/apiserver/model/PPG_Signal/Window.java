package com.java_backend.apiserver.model.PPG_Signal;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Window {
    private int windowsID;
    private Date windowStartTime;
    private Date windowEndTime;
    private ArrayList<RawData> sample;


    public Window() {
    }

    public Window(int windowsID, Date windowStartTime, Date windowEndTime, ArrayList<RawData> sample) {
        this.windowsID = windowsID;
        this.windowStartTime = windowStartTime;
        this.windowEndTime = windowEndTime;
        this.sample = sample;
    }

    public int getWindowsID() {
        return this.windowsID;
    }

    public void setWindowsID(int windowsID) {
        this.windowsID = windowsID;
    }

    public Date getWindowStartTime() {
        return this.windowStartTime;
    }

    public void setWindowStartTime(Date windowStartTime) {
        this.windowStartTime = windowStartTime;
    }

    public Date getWindowEndTime() {
        return this.windowEndTime;
    }

    public void setWindowEndTime(Date windowEndTime) {
        this.windowEndTime = windowEndTime;
    }

    public ArrayList<RawData> getSample() {
        return this.sample;
    }

    public void setSample(ArrayList<RawData> sample) {
        this.sample = sample;
    }

    public Window windowsID(int windowsID) {
        setWindowsID(windowsID);
        return this;
    }

    public Window windowStartTime(Date windowStartTime) {
        setWindowStartTime(windowStartTime);
        return this;
    }

    public Window windowEndTime(Date windowEndTime) {
        setWindowEndTime(windowEndTime);
        return this;
    }

    public Window sample(ArrayList<RawData> sample) {
        setSample(sample);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Window)) {
            return false;
        }
        Window window = (Window) o;
        return windowsID == window.windowsID && Objects.equals(windowStartTime, window.windowStartTime) && Objects.equals(windowEndTime, window.windowEndTime) && Objects.equals(sample, window.sample);
    }

    @Override
    public int hashCode() {
        return Objects.hash(windowsID, windowStartTime, windowEndTime, sample);
    }

    @Override
    public String toString() {
        return "{" +
            " windowsID='" + getWindowsID() + "'" +
            ", windowStartTime='" + getWindowStartTime() + "'" +
            ", windowEndTime='" + getWindowEndTime() + "'" +
            ", sample='" + getSample() + "'" +
            "}";
    }

}

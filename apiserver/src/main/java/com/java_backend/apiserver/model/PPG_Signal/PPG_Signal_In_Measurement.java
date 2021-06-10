package com.java_backend.apiserver.model.PPG_Signal;

import java.util.ArrayList;
import java.util.Objects;

import org.bson.types.ObjectId;

public class PPG_Signal_In_Measurement {
    private ObjectId _id;
    private String measureID;
    private ArrayList<Window> slidingWindows;

    public PPG_Signal_In_Measurement() {
    }

    public PPG_Signal_In_Measurement(ObjectId _id, String measureID, ArrayList<Window> slidingWindows) {
        this._id = _id;
        this.measureID = measureID;
        this.slidingWindows = slidingWindows;
    }

    public ObjectId get_id() {
        return this._id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getMeasureID() {
        return this.measureID;
    }

    public void setMeasureID(String measureID) {
        this.measureID = measureID;
    }

    public ArrayList<Window> getSlidingWindows() {
        return this.slidingWindows;
    }

    public void setSlidingWindows(ArrayList<Window> slidingWindows) {
        this.slidingWindows = slidingWindows;
    }

    public PPG_Signal_In_Measurement _id(ObjectId _id) {
        set_id(_id);
        return this;
    }

    public PPG_Signal_In_Measurement measureID(String measureID) {
        setMeasureID(measureID);
        return this;
    }

    public PPG_Signal_In_Measurement slidingWindows(ArrayList<Window> slidingWindows) {
        setSlidingWindows(slidingWindows);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PPG_Signal_In_Measurement)) {
            return false;
        }
        PPG_Signal_In_Measurement pPG_Signal_In_Measurement = (PPG_Signal_In_Measurement) o;
        return Objects.equals(_id, pPG_Signal_In_Measurement._id) && Objects.equals(measureID, pPG_Signal_In_Measurement.measureID) && Objects.equals(slidingWindows, pPG_Signal_In_Measurement.slidingWindows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, measureID, slidingWindows);
    }

    @Override
    public String toString() {
        return "{" +
            " _id='" + get_id() + "'" +
            ", measureID='" + getMeasureID() + "'" +
            ", slidingWindows='" + getSlidingWindows() + "'" +
            "}";
    }

}

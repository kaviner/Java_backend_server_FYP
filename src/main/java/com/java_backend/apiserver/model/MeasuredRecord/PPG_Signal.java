package com.java_backend.apiserver.model.MeasuredRecord;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PPG_Signal {
        private int ambient;
        private int ambient2;
        @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss.SSS")
        private Date eventTime;
        private String measureId;
        private int ppg0;
        private int ppg1;
        private int ppg2;
        private int status;
        private String userId;

        public PPG_Signal() {
        }

        public PPG_Signal(int ambient, int ambient2, Date eventTime, String measureId, int ppg0, int ppg1, int ppg2, int status, String userId) {
            this.ambient = ambient;
            this.ambient2 = ambient2;
            this.eventTime = eventTime;
            this.measureId = measureId;
            this.ppg0 = ppg0;
            this.ppg1 = ppg1;
            this.ppg2 = ppg2;
            this.status = status;
            this.userId = userId;
        }

        public int getAmbient() {
            return this.ambient;
        }

        public void setAmbient(int ambient) {
            this.ambient = ambient;
        }

        public int getAmbient2() {
            return this.ambient2;
        }

        public void setAmbient2(int ambient2) {
            this.ambient2 = ambient2;
        }

        public Date getEventTime() {
            return this.eventTime;
        }

        public void setEventTime(Date eventTime) {
            this.eventTime = eventTime;
        }

        public String getMeasureId() {
            return this.measureId;
        }

        public void setMeasureId(String measureId) {
            this.measureId = measureId;
        }

        public int getPpg0() {
            return this.ppg0;
        }

        public void setPpg0(int ppg0) {
            this.ppg0 = ppg0;
        }

        public int getPpg1() {
            return this.ppg1;
        }

        public void setPpg1(int ppg1) {
            this.ppg1 = ppg1;
        }

        public int getPpg2() {
            return this.ppg2;
        }

        public void setPpg2(int ppg2) {
            this.ppg2 = ppg2;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public PPG_Signal ambient(int ambient) {
            setAmbient(ambient);
            return this;
        }

        public PPG_Signal ambient2(int ambient2) {
            setAmbient2(ambient2);
            return this;
        }

        public PPG_Signal eventTime(Date eventTime) {
            setEventTime(eventTime);
            return this;
        }

        public PPG_Signal measureId(String measureId) {
            setMeasureId(measureId);
            return this;
        }

        public PPG_Signal ppg0(int ppg0) {
            setPpg0(ppg0);
            return this;
        }

        public PPG_Signal ppg1(int ppg1) {
            setPpg1(ppg1);
            return this;
        }

        public PPG_Signal ppg2(int ppg2) {
            setPpg2(ppg2);
            return this;
        }

        public PPG_Signal status(int status) {
            setStatus(status);
            return this;
        }

        public PPG_Signal userId(String userId) {
            setUserId(userId);
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof PPG_Signal)) {
                return false;
            }
            PPG_Signal pPG_Signal = (PPG_Signal) o;
            return ambient == pPG_Signal.ambient && ambient2 == pPG_Signal.ambient2 && Objects.equals(eventTime, pPG_Signal.eventTime) && Objects.equals(measureId, pPG_Signal.measureId) && ppg0 == pPG_Signal.ppg0 && ppg1 == pPG_Signal.ppg1 && ppg2 == pPG_Signal.ppg2 && status == pPG_Signal.status && Objects.equals(userId, pPG_Signal.userId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ambient, ambient2, eventTime, measureId, ppg0, ppg1, ppg2, status, userId);
        }

        @Override
        public String toString() {
            return "{" +
                " ambient='" + getAmbient() + "'" +
                ", ambient2='" + getAmbient2() + "'" +
                ", eventTime='" + getEventTime() + "'" +
                ", measureId='" + getMeasureId() + "'" +
                ", ppg0='" + getPpg0() + "'" +
                ", ppg1='" + getPpg1() + "'" +
                ", ppg2='" + getPpg2() + "'" +
                ", status='" + getStatus() + "'" +
                ", userId='" + getUserId() + "'" +
                "}";
        }
    

    
    }
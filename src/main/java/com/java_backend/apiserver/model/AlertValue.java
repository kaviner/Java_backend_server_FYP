package com.java_backend.apiserver.model;

import java.util.Objects;

public class AlertValue {
    private String key;
    private int value;

    public AlertValue() {
    }

    public AlertValue(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public AlertValue key(String key) {
        setKey(key);
        return this;
    }

    public AlertValue value(int value) {
        setValue(value);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AlertValue)) {
            return false;
        }
        AlertValue alertValue = (AlertValue) o;
        return Objects.equals(key, alertValue.key) && value == alertValue.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "{" +
            " key='" + getKey() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }

}

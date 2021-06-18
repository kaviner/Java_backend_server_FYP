package com.java_backend.apiserver.config.mongodb_config;

import java.util.Objects;

public class MongoDBConnectionInfo {
    private String connectionString;
    private String databaseName;

    public MongoDBConnectionInfo() {
    }


    public MongoDBConnectionInfo(String connectionString, String databaseName) {
        this.connectionString = connectionString;
        this.databaseName = databaseName;
    }

    public String getConnectionString() {
        return this.connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public MongoDBConnectionInfo connectionString(String connectionString) {
        setConnectionString(connectionString);
        return this;
    }

    public MongoDBConnectionInfo databaseName(String databaseName) {
        setDatabaseName(databaseName);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MongoDBConnectionInfo)) {
            return false;
        }
        MongoDBConnectionInfo mongoDBConnectionInfo = (MongoDBConnectionInfo) o;
        return Objects.equals(connectionString, mongoDBConnectionInfo.connectionString) && Objects.equals(databaseName, mongoDBConnectionInfo.databaseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectionString, databaseName);
    }

    @Override
    public String toString() {
        return "{" +
            " connectionString='" + getConnectionString() + "'" +
            ", databaseName='" + getDatabaseName() + "'" +
            "}";
    }
 
}

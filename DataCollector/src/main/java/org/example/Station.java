package org.example;

public class Station {
    String name;
    String lineNumber;
    Boolean hasConnection;

    public Station(String name, String lineNumber, Boolean hasConnection) {
        this.name = name;
        this.lineNumber = lineNumber;
        this.hasConnection = hasConnection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Boolean getHasConnection() {
        return hasConnection;
    }

    public void setHasConnection(Boolean hasConnection) {
        this.hasConnection = hasConnection;
    }

    @Override
    public String toString() {
        return "lineNumber: " + name + ", name: " + lineNumber + ", conect: " + hasConnection;
    }
}

package org.example;

public class stationJSON {
    String name;
    String line;
    String data;
    String depth;
    Boolean hasConnection;

    public stationJSON(String name, String line, String data, String depth, Boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.data = data;
        this.depth = depth;
        this.hasConnection = hasConnection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getData() {
        return data;
    }

    public void setData(String date) {
        this.data = date;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public Boolean getHasConnection() {
        return hasConnection;
    }

    public void setHasConnection(Boolean hasConnection) {
        this.hasConnection = hasConnection;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", line='" + line + '\'' +
                ", data='" + data + '\'' +
                ", depth='" + depth + '\'' +
                ", hasConnection='" + hasConnection + '\'' +
                '}';
    }
}

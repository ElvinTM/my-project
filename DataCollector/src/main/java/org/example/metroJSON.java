package org.example;

public class metroJSON {
    public String name;
    public String depth;

    public metroJSON(String name, String depth) {
        this.name = name;
        this.depth = depth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "station name: " + name + ", deth: " + depth;
    }
}

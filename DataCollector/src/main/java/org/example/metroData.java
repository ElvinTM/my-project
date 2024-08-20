package org.example;

public class metroData {
    String name;
    String data;
    String depth;

    public metroData(String name, String data, String depth) {
        this.name = name;
        this.data = data;
        this.depth = depth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    @Override
    public String toString() {
        return "name='" + name + ", data='" + data + ", depth='" + depth;
    }
}

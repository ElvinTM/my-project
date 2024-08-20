package org.example;

public class metroCSV {
    public String name;
    public String data;

    public metroCSV(String name, String data) {
        this.name = name;
        this.data = data;
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

    @Override
    public String toString() {
        return "metroData: " + name + ", data: " + data;
    }
}

package org.example;

public class buildJSONfile {
    String name;
    String depth;
    String line;

    public buildJSONfile(String name, String depth, String line) {
        this.name = name;
        this.depth = depth;
        this.line = line;
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

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "buildJSONfile{" +
                "name='" + name + '\'' +
                ", depth='" + depth + '\'' +
                ", line='" + line + '\'' +
                '}';
    }
}

package org.example.taskmanager.Model;

import lombok.Data;

@Data
public class Task {

    public long id;

    public String title;

    public String description;

    public String priority;
}

package org.example.taskmanager.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    public long id;

    public String title;

    public String description;

    public int priority;
}

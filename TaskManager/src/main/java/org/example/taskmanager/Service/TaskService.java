package org.example.taskmanager.Service;

import org.example.taskmanager.Model.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    Task findById(long id);

    Task save(Task task);

    Task update(Task task);

    void deleteById(long id);
}

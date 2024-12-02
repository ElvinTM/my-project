package org.example.taskmanager.Repository;

import org.example.taskmanager.Model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<Task> findAll();

    Optional<Task> findById(long id);

    Task save(Task task);

    Task update(Task task);

    void deleteById(long id);
}

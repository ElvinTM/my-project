package org.example.taskmanager.Controller;

import org.example.taskmanager.Model.Task;
import org.example.taskmanager.Repository.TaskRepository;
import org.example.taskmanager.Service.InTaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    private final TaskRepository taskRepository = Mockito.mock(TaskRepository.class);

    private final InTaskService taskService = new InTaskService(taskRepository);

    @Test
    @DisplayName("Find all test")
    public void findAllTest() {
        List<Task> tasksList = new ArrayList<>();
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task Title");
        task.setDescription("Task Description");
        task.setPriority(1);
        tasksList.add(task);
        when(taskRepository.findAll()).thenReturn(tasksList);
        Collection<Task> ctrl = taskService.findAll();
        assertEquals(tasksList.size(), ctrl.size());
        verify(taskRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Find by id test")
    public void findByIdTest() {
        long taskId = 1L;
        Task task = new Task();
        task.setId(taskId);
        task.setTitle("Task Title");
        task.setDescription("Task Description");
        task.setPriority(1);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));
        Task ctrl = taskService.findById(taskId);
        assertEquals(taskId, ctrl.getId());
        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    @DisplayName("Save test")
    public void saveTest() {
        Task task = new Task();
        taskService.save(task);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    @DisplayName("Update test")
    public void updateTest() {
        Task task = new Task();
        taskService.update(task);
        verify(taskRepository, times(1)).update(any(Task.class));
    }

    @Test
    @DisplayName("Delete test")
    public void deleteTest() {
        long taskId = 1L;
        taskService.deleteById(taskId);
        verify(taskRepository, times(1)).deleteById(taskId);
    }
}

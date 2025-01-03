package org.example.taskmanager.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.taskmanager.Model.Task;
import org.example.taskmanager.Service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

    @GetMapping("/task/create")
    public String addForm(Model model) {
        model.addAttribute("task", new Task());
        return "create";
    }


    @PostMapping("/task/create")
    public String createTask(@ModelAttribute("task") Task task) {
        taskService.save(task);
        return "redirect:/";
    }

    @GetMapping("/task/edit/{id}")
    public String editForm(@PathVariable long id, Model model) {
        Task task = taskService.findById(id);
        model.addAttribute("task", task);
        return "edit";
    }

    @PostMapping("/task/edit/{id}")
    public String editTask(@PathVariable long id, @ModelAttribute("task") Task task) {
        task.setId(id);
        taskService.update(task);
        return "redirect:/";
    }

    @GetMapping("/task/delete/{id}")
    public String delete(@PathVariable long id) {
        taskService.deleteById(id);
        return "redirect:/";
    }
}

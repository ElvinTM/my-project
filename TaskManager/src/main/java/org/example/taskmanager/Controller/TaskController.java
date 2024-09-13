package org.example.taskmanager.Controller;

import org.example.taskmanager.Model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    private final List<Task> tasks = new ArrayList<>();

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping("/task/create")
    public String add(Model model) {
        model.addAttribute("task", new Task());
        return "create";
    }


    @PostMapping("/task/create")
    public String create(@ModelAttribute Task task) {
        task.setId(System.currentTimeMillis());
        tasks.add(task);
        return "redirect:/";
    }

    @GetMapping("/task/edit/{id}")
    public String editForm(@PathVariable long id, Model model) {
        Task task = findById(id);
        if (task != null) {
            model.addAttribute("task", task);
            return "edit";
        }
        return "redirect:/";}

    @PostMapping("/task/edit")
    public String editTask(@ModelAttribute Task task) {
        Task existing = findById(task.getId());
        if (existing != null) {
            existing.setTitle(task.getTitle());
            existing.setDescription(task.getDescription());
            existing.setPriority(task.getPriority());
        }
        return "redirect:/";
    }

    @GetMapping("/task/delete/{id}")
    public String delete(@PathVariable long id) {
        Task task = findById(id);
        if (task != null) {
            tasks.remove(task);
        }
        return "redirect:/";
    }

    private Task findById(long id) {
        return tasks.stream()
                .filter(t -> t.getId() ==id)
                .findFirst()
                .orElse(null);
    }
}

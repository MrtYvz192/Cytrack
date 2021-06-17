package com.cydeo.controller;

import com.cydeo.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task")
public class TaskController {

    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/taskList")
    public String taskList(Model model){
        model.addAttribute("tasks",taskService.listAllTasks());
        return "task/task-list";
    }
}

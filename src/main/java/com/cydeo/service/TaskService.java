package com.cydeo.service;

import com.cydeo.dto.TaskDTO;

import java.util.List;
import java.util.Map;

public interface TaskService {

    Map<TaskDTO,String> getTaskAndLessonsMap();
    List<TaskDTO> listAllTasks();
}

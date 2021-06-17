package com.cydeo.implementation;

import com.cydeo.dto.TaskDTO;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.TaskRepository;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    TaskRepository taskRepository;
    MapperUtil mapperUtil;

    public TaskServiceImpl(TaskRepository taskRepository, MapperUtil mapperUtil) {
        this.taskRepository = taskRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public Map<TaskDTO, String> getTaskAndLessonsMap() {
        return null;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        List<TaskDTO> list = taskRepository.findAll().stream().map(obj->mapperUtil.convert(obj,new TaskDTO())).collect(Collectors.toList());
        list.sort((o2,o1) -> o1.getAssignDate().compareTo(o2.getAssignDate()));
        return list;
    }
}

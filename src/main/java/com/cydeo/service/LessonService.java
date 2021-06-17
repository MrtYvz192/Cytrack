package com.cydeo.service;

import com.cydeo.dto.LessonDTO;

import java.util.List;
import java.util.Map;

public interface LessonService {

    List<LessonDTO> listAllLessonsByInstructor(String username);
    Map<LessonDTO,String> getLessonInstructorMap();
}

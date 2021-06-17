package com.cydeo.implementation;

import com.cydeo.dto.LessonDTO;
import com.cydeo.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    @Override
    public List<LessonDTO> listAllLessonsByInstructor(String username) {
        return null;
    }
}

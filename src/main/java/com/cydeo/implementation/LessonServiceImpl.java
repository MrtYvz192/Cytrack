package com.cydeo.implementation;

import com.cydeo.dto.LessonDTO;
import com.cydeo.entity.Lesson;
import com.cydeo.entity.User;
import com.cydeo.mapper.MainMapper;
import com.cydeo.repository.LessonRepository;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {

    LessonRepository lessonRepository;
    UserRepository userRepository;
    MainMapper<Lesson,LessonDTO> lessonMapper;


    public LessonServiceImpl(LessonRepository lessonRepository, UserRepository userRepository, MainMapper<Lesson, LessonDTO> lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
        this.lessonMapper = lessonMapper;
    }

    @Override
    public List<LessonDTO> listAllLessonsByInstructor(String username) {
        User user = userRepository.findByEmail(username);
        List<Lesson> lessons = lessonRepository.findAllByInstructorSet(user);

        return lessons.stream().map(lesson -> lessonMapper.convertToDTO(lesson)).collect(Collectors.toList());
    }
}

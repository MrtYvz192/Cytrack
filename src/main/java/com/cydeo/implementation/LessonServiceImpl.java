package com.cydeo.implementation;

import com.cydeo.dto.LessonDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Lesson;
import com.cydeo.entity.User;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.LessonRepository;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.LessonService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {

    LessonRepository lessonRepository;
    UserRepository userRepository;
    MapperUtil mapperUtil;
    UserService userService;


    public LessonServiceImpl(LessonRepository lessonRepository, UserRepository userRepository, MapperUtil mapperUtil, UserService userService) {
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.userService = userService;
    }

    @Override
    public List<LessonDTO> listAllLessonsByInstructor(String username) {
        User user = userRepository.findByEmail(username);
        List<Lesson> lessons = lessonRepository.findAllByInstructorSet(user);

        return lessons.stream().map(lesson -> mapperUtil.convert(lesson, new LessonDTO())).collect(Collectors.toList());
    }

    @Override
    public Map<LessonDTO, String> getLessonInstructorMap() {
        Map<LessonDTO,String> map = new HashMap<>();
        List<LessonDTO> list = lessonRepository.findAll().stream().map(obj->mapperUtil.convert(obj,new LessonDTO()))
                .collect(Collectors.toList());

        for(LessonDTO each:list){
//            Lesson lesson = lessonRepository.findByName(each.getName());
            List<UserDTO> instructors = userService.listAllInstructorsByLesson(each.getName());
            String instructorList = instructors.stream().map(obj -> obj.getFirstName() +" "+ obj.getLastName())
                    .reduce("",(x,y)-> x + y + " | ");
            instructorList = instructorList.length()>0?instructorList.substring(0,instructorList.length()-2):"-";
            map.put(each,instructorList);
        }
        return map;
    }
}

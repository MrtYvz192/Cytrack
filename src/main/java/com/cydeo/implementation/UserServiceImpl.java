package com.cydeo.implementation;

import com.cydeo.dto.GroupDTO;
import com.cydeo.dto.LessonDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Lesson;
import com.cydeo.entity.User;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.LessonRepository;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.GroupService;
import com.cydeo.service.LessonService;
import com.cydeo.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    MapperUtil mapperUtil;
    LessonService lessonService;
    GroupService groupService;
    LessonRepository lessonRepository;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil, @Lazy LessonService lessonService, GroupService groupService, LessonRepository lessonRepository) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.lessonService = lessonService;
        this.groupService = groupService;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public List<UserDTO> listAllUsersByRole(String roleDescription) {
        List<User> users = userRepository.findAllByRoleDescription(roleDescription);
        return users.stream().map(user -> mapperUtil.convert(user,new UserDTO())).collect(Collectors.toList());
    }

    @Override
    public Map<UserDTO, String> getInstructorsAndLessonsMap() {
        Map<UserDTO,String> map = new HashMap<>();
        List<UserDTO> list = listAllUsersByRole("Instructor");
        for(UserDTO each: list){
            List<LessonDTO> lessons = lessonService.listAllLessonsByInstructor(each.getEmail());
            String lessonList = lessons.stream().map(obj -> obj.getName()).reduce("",(x,y)-> x + y + " | ");
            lessonList = lessonList.length()>0?lessonList.substring(0,lessonList.length()-2):"-";
            map.put(each,lessonList);
        }
        return map;
    }

    @Override
    public Map<UserDTO, String> getCybertekMentorsAndGroupsMap() {
        Map<UserDTO,String> map = new HashMap<>();
        List<UserDTO> list = listAllUsersByRole("CybertekMentor");
        for(UserDTO each: list){
            List<GroupDTO> groups = groupService.listAllGroupsOfCybertekMentor(each.getEmail());
            String groupList = groups.stream().map(obj -> obj.getBatch().getName() + " " + obj.getName())
                    .reduce("",(x,y)-> x + y + " | ");
            groupList = groupList.length()>0?groupList.substring(0,groupList.length()-2):"-";
            map.put(each,groupList);
        }
        return map;
    }
    @Override
    public Map<UserDTO, String> getAlumniMentorsAndGroupsMap() {
        Map<UserDTO,String> map = new HashMap<>();
        List<UserDTO> list = listAllUsersByRole("AlumniMentor");
        for(UserDTO each: list){
            List<GroupDTO> groups = groupService.listAllGroupsOfAlumniMentor(each.getEmail());
            String groupList = groups.stream().map(obj -> obj.getBatch().getName() + " " + obj.getName())
                    .reduce("",(x,y)-> x + y + " | ");
            groupList = groupList.length()>0?groupList.substring(0,groupList.length()-2):"-";
            map.put(each,groupList);
        }
        return map;
    }

    @Override
    public List<UserDTO> listAllInstructorsByLesson(String lessonName) {
        Lesson lesson = lessonRepository.findByName(lessonName);
        return userRepository.findAllByLessonSet(lesson).stream().map(obj->mapperUtil.convert(obj,new UserDTO()))
                .collect(Collectors.toList());
    }
}

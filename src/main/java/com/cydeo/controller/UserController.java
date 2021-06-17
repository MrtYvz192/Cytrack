package com.cydeo.controller;

import com.cydeo.service.LessonService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    UserService userService;
    LessonService lessonService;

    public UserController(UserService userService, LessonService lessonService) {
        this.userService = userService;
        this.lessonService = lessonService;
    }

    @GetMapping("/adminList")
    public String adminList(Model model){
        model.addAttribute("admins",userService.listAllUsersByRole("Admin"));
        return "user/admin-list";
    }

    @GetMapping("/instructorList")
    public String instructorList(Model model){
        model.addAttribute("instructors",userService.getInstructorsAndLessonsMap());
        return "user/instructor-list";
    }

    @GetMapping("/cybertekMentorList")
    public String cybertekMentorList(Model model){
        model.addAttribute("cybertekMentors",userService.getCybertekMentorsAndGroupsMap());
        return "user/cybertekmentor-list";
    }

}

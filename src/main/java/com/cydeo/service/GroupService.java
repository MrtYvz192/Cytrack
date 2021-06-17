package com.cydeo.service;

import com.cydeo.dto.GroupDTO;
import com.cydeo.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface GroupService {
    List<GroupDTO> listAllGroupsOfCybertekMentor(String email);
}

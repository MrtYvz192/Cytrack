package com.cydeo.implementation;

import com.cydeo.dto.GroupDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.GroupRepository;
import com.cydeo.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    GroupRepository groupRepository;
    MapperUtil mapperUtil;

    public GroupServiceImpl(GroupRepository groupRepository, MapperUtil mapperUtil) {
        this.groupRepository = groupRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<GroupDTO> listAllGroupsOfCybertekMentor(String email) {
        return groupRepository.findAllByCybertekMentorEmail(email).stream().map(obj-> mapperUtil.convert(obj,new GroupDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<GroupDTO> listAllGroupsOfAlumniMentor(String email) {
        return groupRepository.findAllByAlumniMentorEmail(email).stream().map(obj-> mapperUtil.convert(obj,new GroupDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public List<GroupDTO> listAllGroups() {
        return groupRepository.findAll().stream().map(obj->mapperUtil.convert(obj,new GroupDTO()))
                .collect(Collectors.toList());
    }
}

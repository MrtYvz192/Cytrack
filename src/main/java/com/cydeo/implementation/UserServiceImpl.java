package com.cydeo.implementation;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.MainMapper;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    MainMapper<User,UserDTO> userMapper;

    public UserServiceImpl(UserRepository userRepository, MainMapper<User, UserDTO> userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> listAllUsersByRole(String roleDescription) {
        List<User> users = userRepository.findAllByRoleDescription(roleDescription);
        return users.stream().map(user -> userMapper.convertToDTO(user)).collect(Collectors.toList());
    }
}

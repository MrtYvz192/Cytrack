package com.cydeo.dto;

import com.cydeo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GroupDTO {


    private String name;
    private String mascot;
    private BatchDTO batch;
    private UserDTO cybertekMentor;
    private UserDTO alumniMentor;
}

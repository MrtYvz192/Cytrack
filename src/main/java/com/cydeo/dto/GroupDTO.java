package com.cydeo.dto;

import com.cydeo.entity.Batch;
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
    private Batch batch;
    private User cybertekMentor;
    private User alumniMentor;
}

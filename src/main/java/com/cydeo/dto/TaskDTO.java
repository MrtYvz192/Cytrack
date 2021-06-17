package com.cydeo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskDTO{

    private String name;
    private String type;
    private LocalDate assignDate;
    private LocalDate dueDate;
    private LessonDTO lesson;

}

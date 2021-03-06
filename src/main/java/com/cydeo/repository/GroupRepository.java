package com.cydeo.repository;

import com.cydeo.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {

    List<Group> findAllByCybertekMentorEmail(String email);
    List<Group> findAllByAlumniMentorEmail(String email);
}

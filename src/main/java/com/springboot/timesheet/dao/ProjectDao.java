package com.springboot.timesheet.dao;

import com.springboot.timesheet.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectDao extends JpaRepository<Project, Object> {

    //find user all project
    @Query(value =
            "select * from project \n" +
                    "join employee_project ep on project.id=ep.project_id\n" +
                    "join employee emp on ep.employee_id=emp.id\n" +
                    "where emp.emp_username=?1", nativeQuery = true)
    List<Project> findProjectByUsername(String username);

    //find project name by timesheet id
    @Query(value =
            "select project.project_name as project from project\n" +
                    "join timesheet_project tp on project.id=tp.project_id\n" +
                    "where tp.timesheet_id=?", nativeQuery = true)
    String findProjectNameByTimesheetId(Long timesheetId);

    Project findByName(String name);


}

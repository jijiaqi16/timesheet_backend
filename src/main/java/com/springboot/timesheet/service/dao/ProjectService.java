package com.springboot.timesheet.service.dao;

import com.springboot.timesheet.model.Project;

import java.util.List;

public interface ProjectService {
    List<Project> getProjectByUsername(String username);
}

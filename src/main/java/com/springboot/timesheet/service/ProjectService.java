package com.springboot.timesheet.service;

import com.springboot.timesheet.dao.ProjectDao;
import com.springboot.timesheet.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProjectService")
public class ProjectService implements com.springboot.timesheet.service.dao.ProjectService {

    @Autowired
    ProjectDao projectDao;

    @Override
    public List<Project> getProjectByUsername(String username) {
        return projectDao.findProjectByUsername(username);
    }
}

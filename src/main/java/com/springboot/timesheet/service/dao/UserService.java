package com.springboot.timesheet.service.dao;

import com.springboot.timesheet.dao.EmployeeDao;
import com.springboot.timesheet.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    String login(Employee employee);
    String logout(Employee employee);
    Employee getUser(String username);
}

package com.springboot.timesheet.service;

import com.springboot.timesheet.dao.EmployeeDao;
import com.springboot.timesheet.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements com.springboot.timesheet.service.dao.UserService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee getUser(String username) {
        Employee findEmployee = employeeDao.findEmployeeByUsername(username);
        findEmployee.setPassword("");
        return findEmployee;
    }

}

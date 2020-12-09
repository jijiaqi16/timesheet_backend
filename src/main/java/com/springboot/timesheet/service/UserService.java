package com.springboot.timesheet.service;

import com.springboot.timesheet.dao.EmployeeDao;
import com.springboot.timesheet.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements com.springboot.timesheet.service.dao.UserService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public String login(Employee employee) {
        //judge if username exist
        Employee findEmployee = employeeDao.findEmployeeByUsername(employee.getUsername());
        if(findEmployee==null){
            return "noUser";
        }
        //judge if password vaild
        Boolean res=findEmployee.getPassword().equals(employee.getPassword());
        return res+"";
    }

    public String logout(Employee employee){

        return "logout";
    }

    @Override
    public Employee getUser(String username) {
        Employee findEmployee = employeeDao.findEmployeeByUsername(username);
        return findEmployee;
    }


}

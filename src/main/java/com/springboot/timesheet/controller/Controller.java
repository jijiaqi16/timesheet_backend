package com.springboot.timesheet.controller;

import com.springboot.timesheet.model.Employee;
import com.springboot.timesheet.service.dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("employee")
public class Controller {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody Employee employee){
        return userService.login(employee);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String logout(@RequestBody Employee employee){
        return userService.logout(employee);
    }

    @RequestMapping("/getuser")
    @ResponseBody
    public Employee getuser(@RequestBody String username){
        return userService.getUser(username);
    }
}

package com.springboot.timesheet.controller;

import com.springboot.timesheet.model.Employee;
import com.springboot.timesheet.service.dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class Controller {
    @Autowired
    private UserService userService;

//    @RequestMapping("/login")
//    @ResponseBody
//    public String login(@RequestBody Employee employee){
//        return userService.login(employee);
//    }
//
//    @RequestMapping("/logout")
//    @ResponseBody
//    public String logout(@RequestBody Employee employee){
//        return userService.logout(employee);
//    }

    @RequestMapping("/getuser")
    @ResponseBody
    public Employee getuser(@RequestBody String username) {
        return userService.getUser(username);
    }

    @RequestMapping("/role")
    @ResponseBody
    public String role(){return "role";}

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello controller";
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/roleauth")
    @ResponseBody
    public String roleath() {
        return "ROLE";
    }


    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "TEST";
    }

}

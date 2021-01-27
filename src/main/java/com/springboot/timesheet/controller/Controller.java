package com.springboot.timesheet.controller;

import com.springboot.timesheet.model.Employee;
import com.springboot.timesheet.model.Project;
import com.springboot.timesheet.model.Timesheet;
import com.springboot.timesheet.service.ProjectService;
import com.springboot.timesheet.service.dao.TimesheetService;
import com.springboot.timesheet.service.dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class Controller {
    @Autowired
    private UserService userService;

    @Autowired
    private TimesheetService timesheetService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/getuser")
    @ResponseBody
    public Employee getuser(@RequestBody String username) {
        System.out.println(username);
        return userService.getUser(username);
    }

    @RequestMapping("/role")
    @ResponseBody
    public String role() {
        return "role";
    }

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

    //timesheet add timesheet
    @RequestMapping(
            value = "/creatTimesheet",
            method = RequestMethod.POST)
    public void createTimesheet(@RequestBody Map<String, Object> payload) throws Exception {
        timesheetService.addTimesheet(payload);
    }

    //timesheet show timesheet
    @RequestMapping("/showtimesheet")
    @ResponseBody
    public Map<String, Timesheet> showTimesheet(@RequestBody Map<String, Object> payload) throws ParseException {
        return timesheetService.showTimesheet((String)payload.get("startDate"), (String) payload.get("username"));
    }

    //timesheet save timesheet
    @RequestMapping("/savetimesheet")
    @ResponseBody
    public String saveTimesheet(@RequestBody Map<String,Map<String,Object>> timesheetmap) throws ParseException {
        System.out.println(timesheetmap);
        timesheetService.saveTimesheet(timesheetmap);
        return "save Success";
    }

    //project
    @RequestMapping("/getproject")
    @ResponseBody
    public List<Project> getproject(@RequestBody String username) {
        return projectService.getProjectByUsername(username);
    }


}

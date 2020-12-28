package com.springboot.timesheet.service;

import com.springboot.timesheet.dao.EmployeeDao;
import com.springboot.timesheet.dao.ProjectDao;
import com.springboot.timesheet.dao.TimesheetDao;
import com.springboot.timesheet.model.Project;
import com.springboot.timesheet.model.Timesheet;
import com.springboot.timesheet.service.dao.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("TimesheetService")
public class TimesheetSerivce implements TimesheetService {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private TimesheetDao timesheetDao;

    @Autowired
    private ProjectDao projectDao;


    @Override
    public void addTimesheet(Map<String, Object> payload) throws ParseException {
        //get username
        String username = (String) payload.get("username");
        //date transfer
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date startDate = sdf.parse((String) payload.get("startDate"));
        Date endDate = sdf.parse((String) payload.get("endDate"));
        java.sql.Date startDateSql = new java.sql.Date(startDate.getTime());
        java.sql.Date endDateSql = new java.sql.Date(endDate.getTime());
        //remove date and username
        payload.remove("username");
        payload.remove("startDate");
        payload.remove("endDate");
        //insert timesheet
        for (String key : payload.keySet()) {
            Timesheet timesheet = new Timesheet();
            //load date
            timesheet.setEndDate(endDateSql);
            timesheet.setStartDate(startDateSql);
            //load time in a week
            HashMap<String, Integer> map = (HashMap<String, Integer>) payload.get(key);
            for (String week : map.keySet()) {
                timesheet.setSu(map.get("Su"));
                timesheet.setMo(map.get("Mo"));
                timesheet.setTu(map.get("Tu"));
                timesheet.setWe(map.get("We"));
                timesheet.setTh(map.get("Th"));
                timesheet.setFr(map.get("Fr"));
                timesheet.setSa(map.get("Sa"));
            }
            //insert into timesheet table
            timesheet = timesheetDao.save(timesheet);
            timesheetDao.flush();
            //insert into timesheet_project table
            timesheetDao.addTimesheetProject(timesheet.getId(), projectDao.findByName(key).getId());
            timesheetDao.flush();
            //insert into timesheet_employee table
            timesheetDao.addTimesheetEmployee(timesheet.getId(), employeeDao.findEmployeeByUsername(username).getId());
            timesheetDao.flush();
        }
    }

    @Override
    public Map<String, Timesheet> showTimesheet(String startDate,String username)  throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date startdate = sdf.parse(startDate);
        java.sql.Date startDateSql = new java.sql.Date(startdate.getTime());
        Map<String,Timesheet> map = new HashMap<>();
       List<Timesheet> timesheets= timesheetDao.findByStartDateAndUsername(startDateSql,username);
       for(Timesheet timesheet:timesheets){
           map.put(projectDao.findProjectNameByTimesheetId(timesheet.getId()),timesheet);
       }
        System.out.println(map);
       return map;
    }
}

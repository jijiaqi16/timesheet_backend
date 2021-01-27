package com.springboot.timesheet.service;

import com.springboot.timesheet.dao.EmployeeDao;
import com.springboot.timesheet.dao.ProjectDao;
import com.springboot.timesheet.dao.TimesheetDao;
import com.springboot.timesheet.model.Project;
import com.springboot.timesheet.model.Timesheet;
import com.springboot.timesheet.service.dao.TimesheetService;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
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
                timesheet.setSu(map.get("su"));
                timesheet.setMo(map.get("mo"));
                timesheet.setTu(map.get("tu"));
                timesheet.setWe(map.get("we"));
                timesheet.setTh(map.get("th"));
                timesheet.setFr(map.get("fr"));
                timesheet.setSa(map.get("sa"));
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
    public Map<String, Timesheet> showTimesheet(String startDate, String username) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date startdate = sdf.parse(startDate);
        java.sql.Date startDateSql = new java.sql.Date(startdate.getTime());
        List<Timesheet> timesheets = timesheetDao.findByStartDateAndUsername(startDateSql, username);
        if (timesheets.isEmpty()) {
            return null;
        }
        Map<String, Timesheet> map = new HashMap<>();

//        int[] totalDay = {0, 0, 0, 0, 0, 0, 0};
        Timesheet totalTimesheet = new Timesheet();
        for (Timesheet timesheet : timesheets) {
//            int total = 0;
            map.put(projectDao.findProjectNameByTimesheetId(timesheet.getId()), timesheet);
//            totalDay[0] += timesheet.getSu();
//            totalDay[1] += timesheet.getMo();
//            totalDay[2] += timesheet.getTu();
//            totalDay[3] += timesheet.getWe();
//            totalDay[4] += timesheet.getTh();
//            totalDay[5] += timesheet.getFr();
//            totalDay[6] += timesheet.getSa();
        }
//        totalTimesheet.setSu(totalDay[0]);
//        totalTimesheet.setMo(totalDay[1]);
//        totalTimesheet.setTu(totalDay[2]);
//        totalTimesheet.setWe(totalDay[3]);
//        totalTimesheet.setTh(totalDay[4]);
//        totalTimesheet.setFr(totalDay[5]);
//        totalTimesheet.setSa(totalDay[6]);
//        map.put("Total(/day)", totalTimesheet);
//        totalTimesheet = null;
        return map;
    }

    @Override
    public void saveTimesheet(Map<String, Map<String, Object>> timesheetMap) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        java.sql.Date startDate;

        startDate = new java.sql.Date(sdf.parse((String) timesheetMap.get("Info").get("start")).getTime());
        String username = (String) timesheetMap.get("Info").get("username");
        timesheetMap.remove("Info");

        Map<String, Object> createEditTimesheet = null;
        List<Long> savedTimesheetIds = new ArrayList<>();
        savedTimesheetIds = timesheetDao.findIdsByStartDate(startDate, username);
        Boolean createExist = false;
        for (String key : timesheetMap.keySet()) {

            if (key.equals("CreateEditTimesheet")) {
                createExist = true;
                createEditTimesheet = timesheetMap.get("CreateEditTimesheet");
                continue;
            } else {
                Long timeshheetId = new Long((Integer) timesheetMap.get(key).get("id"));
                Timesheet timesheet = timesheetDao.findTimesheetById(timeshheetId);
                if (timesheet != null) {
                    Integer su = (Integer) timesheetMap.get(key).get("su");
                    Integer mo = (Integer) timesheetMap.get(key).get("mo");
                    Integer tu = (Integer) timesheetMap.get(key).get("tu");
                    Integer we = (Integer) timesheetMap.get(key).get("we");
                    Integer th = (Integer) timesheetMap.get(key).get("th");
                    Integer fr = (Integer) timesheetMap.get(key).get("fr");
                    Integer sa = (Integer) timesheetMap.get(key).get("sa");
                    Long timesheetId = new Long((Integer) timesheetMap.get(key).get("id"));
                    timesheetDao.updateTimesheet(su, mo, tu, we, th, fr, sa, timesheetId);
                }
                savedTimesheetIds.remove(timeshheetId);

            }
        }
        if (!savedTimesheetIds.isEmpty()) {
            for (Long id : savedTimesheetIds) {
                timesheetDao.removeTimesheetById(id);
            }
        }
        if (createExist) {
            addTimesheet(createEditTimesheet);
        }
    }
}

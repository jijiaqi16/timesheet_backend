package com.springboot.timesheet.service.dao;

import com.springboot.timesheet.model.Timesheet;

import java.text.ParseException;
import java.util.Map;

public interface TimesheetService {
    void addTimesheet(Map<String, Object> payload) throws ParseException;
    Map<String, Timesheet> showTimesheet(String startDate,String username) throws ParseException;
}

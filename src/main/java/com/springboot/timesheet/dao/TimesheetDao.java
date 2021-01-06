package com.springboot.timesheet.dao;

import com.springboot.timesheet.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

public interface TimesheetDao extends JpaRepository<Timesheet, Long> {

    @Modifying
    @Query(value = "insert into timesheet_project values (?,?)", nativeQuery = true)
    @Transactional
    void addTimesheetProject(Long timesheetId, Long projectId);

    @Modifying
    @Query(value = "insert into timesheet_employee values (?,?)", nativeQuery = true)
    @Transactional
    void addTimesheetEmployee(Long timesheetId, Long employeeId);

    @Query(value =
            "select timesheet.id,timesheet.timesheet_start_date,timesheet.timesheet_end_date,timesheet.timesheet_Sunday,timesheet.timesheet_Monday,timesheet.timesheet_Tuesday,timesheet.timesheet_Wednesday,timesheet.timesheet_Thursday,timesheet.timesheet_Friday,timesheet_Saturday\n" +
            "from timesheet \n" +
            "join timesheet_employee te on timesheet.id=te.timesheet_id\n" +
            "join employee on te.emp_id=employee.id\n" +
            "where (timesheet.timesheet_start_date=? && employee.emp_username=?);", nativeQuery = true)
    List<Timesheet> findByStartDateAndUsername(Date startDate,String username);

    Timesheet findTimesheetById(Long id);

    @Modifying
    @Query(value = "update timesheet set timesheet_Sunday=?,timesheet_Monday=?,timesheet_Tuesday=?,timesheet_Wednesday=?,timesheet_Thursday=?,timesheet_Friday=?,timesheet_Saturday=? where id=?;", nativeQuery = true)
    @Transactional
    void updateTimesheet(Integer su,Integer mo,Integer tu,Integer we,Integer th,Integer fr,Integer sa,Long id);
}

package com.springboot.timesheet.dao;

import com.springboot.timesheet.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee,Long> {
    Employee findEmployeeByUsername(String username);
    Employee saveAndFlush(Employee employee);
}

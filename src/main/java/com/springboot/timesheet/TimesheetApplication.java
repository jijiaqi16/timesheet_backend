package com.springboot.timesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class TimesheetApplication {


    public static void main(String[] args) {
        SpringApplication.run(TimesheetApplication.class, args);
    }

}

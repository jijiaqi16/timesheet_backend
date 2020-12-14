package com.springboot.timesheet.service;

import com.springboot.timesheet.dao.EmployeeDao;
import com.springboot.timesheet.model.Employee;
import com.springboot.timesheet.model.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserService implements UserDetailsService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //if username is empty
        if (username == null || "".equals(username)){
            throw new RuntimeException("username cannot be null");
        }
        //if cannot find username
        Employee employee = employeeDao.findEmployeeByUsername(username);
        if(employee==null){
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
        //generate UserDetail
        String password = new BCryptPasswordEncoder().encode(employee.getPassword());
            //get roles
        String[] roles = employee.getIdentity().split(",");

        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String item:roles){
            authorities.add(new SimpleGrantedAuthority(item));
        }

        JwtUser jwtUser = new JwtUser(username,password,authorities);
        return jwtUser;
    }

}

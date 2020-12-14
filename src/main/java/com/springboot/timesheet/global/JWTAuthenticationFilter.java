package com.springboot.timesheet.global;

import com.alibaba.fastjson.JSON;
import com.springboot.timesheet.model.JwtUser;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //authenticate user
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getParameter("username"), request.getParameter("password")));
    }

    //if success send token back
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        //get roles
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        //create token
        String token = JwtTokenUtil.createToken(jwtUser.getUsername(), authorities.toString());
        //set response encoding/type/header
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("token", JwtTokenUtil.TOKEN_PREFIX + token);
        response.setContentType("text/json;charset=utf-8");
        //send back to frontend
        response.getWriter().write(JSON.toJSONString("login success"));
    }

    //if fail

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        String returnData = "";
        if (failed instanceof AccountExpiredException) {
            returnData = "account is expired";
        } else if (failed instanceof UsernameNotFoundException) {
            returnData = "user does not exist";
        } else if (failed instanceof BadCredentialsException) {
            returnData = "password is wrong";
        } else if (failed instanceof CredentialsExpiredException) {
            returnData = "password is expired";
        } else if (failed instanceof DisabledException) {
            returnData = "account is locked";
        } else {
            returnData = "unknown error";
        }

        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(returnData));
    }
}

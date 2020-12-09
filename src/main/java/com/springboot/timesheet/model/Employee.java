package com.springboot.timesheet.model;

import javax.persistence.*;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "emp_firstname")
    private String firstName;
    @Column(name = "emp_lastname")
    private String lastName;
    @Column(name = "emp_age")
    private String age;
    @Column(name = "emp_birthdate")
    private String birthdate;
    @Column(name = "emp_sex")
    private String sex;
    @Column(name = "emp_email")
    private String email;
    @Column(name = "emp_phone")
    private String phone;
    @Column(name = "emp_department")
    private String department;
    @Column(name = "emp_identity")
    private String identity;

    @Column(name = "emp_username")
    private String username;
    @Column(name = "emp_password")
    private String password;
    @Column(name = "emp_login")
    private Boolean login;


    public Employee() {}

    public Employee(String firstName, String lastName, String age, String birthdate, String sex, String email, String phone, String department, String identity, String username, String password, Boolean login) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthdate = birthdate;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.department = department;
        this.identity = identity;
        this.username = username;
        this.password = password;
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }
}

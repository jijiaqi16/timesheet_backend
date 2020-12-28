package com.springboot.timesheet.model;


import javax.persistence.*;

@Entity
@Table(name="project")
public class Project {

    @Id
    @GeneratedValue
    private long id;
    @Column(name = "project_name")
    private String name;
    @Column(name = "project_client")
    private String client;
    @Column(name = "project_desc")
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

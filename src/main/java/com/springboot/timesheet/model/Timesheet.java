package com.springboot.timesheet.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "timesheet")
public class Timesheet {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "timesheet_start_date")
    private Date startDate;
    @Column(name = "timesheet_end_date")
    private Date endDate;

    @Column(name = "timesheet_Sunday")
    private Integer Su;
    @Column(name = "timesheet_Monday")
    private Integer Mo;
    @Column(name = "timesheet_Tuesday")
    private Integer Tu;
    @Column(name = "timesheet_Wednesday")
    private Integer We;
    @Column(name = "timesheet_Thursday")
    private Integer Th;
    @Column(name = "timesheet_Friday")
    private Integer Fr;
    @Column(name = "timesheet_Saturday")
    private Integer Sa;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getSu() {
        return Su;
    }

    public void setSu(Integer su) {
        Su = su;
    }

    public Integer getMo() {
        return Mo;
    }

    public void setMo(Integer mo) {
        Mo = mo;
    }

    public Integer getTu() {
        return Tu;
    }

    public void setTu(Integer tu) {
        Tu = tu;
    }

    public Integer getWe() {
        return We;
    }

    public void setWe(Integer we) {
        We = we;
    }

    public Integer getTh() {
        return Th;
    }

    public void setTh(Integer th) {
        Th = th;
    }

    public Integer getFr() {
        return Fr;
    }

    public void setFr(Integer fr) {
        Fr = fr;
    }

    public Integer getSa() {
        return Sa;
    }

    public void setSa(Integer sa) {
        Sa = sa;
    }

    @Override
    public String toString() {
        return "Timesheet{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", Su=" + Su +
                ", Mo=" + Mo +
                ", Tu=" + Tu +
                ", We=" + We +
                ", Th=" + Th +
                ", Fr=" + Fr +
                ", Sa=" + Sa +
                '}';
    }
}

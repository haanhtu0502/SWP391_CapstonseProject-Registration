/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Date;

/**
 *
 * @author HLong
 */
public class Semester {
    private int semesterId;
    public String name;
    private Date startDate;
    private Date endDate;
   
    public Semester() {
    }

    public Semester(int semesterId, String name, Date startDate, Date endDate) {
        this.semesterId = semesterId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

   
}

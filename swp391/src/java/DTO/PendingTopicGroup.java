/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author SE161714 Ha Anh Tu
 */
public class PendingTopicGroup {
    private int id;
    private Groups group;
    private Topic topic;
    private int departmentId;  

    public PendingTopicGroup() {
    }

    public PendingTopicGroup(int id, Groups group, Topic topic, int departmentId) {
        this.id = id;
        this.group = group;
        this.topic = topic;
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }


    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

  
}

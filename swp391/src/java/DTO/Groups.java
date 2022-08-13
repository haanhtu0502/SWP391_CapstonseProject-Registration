/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author HLong
 */
public class Groups {
    private int groupId;
    private String groupName;
    private int semId;
    private int groupStatus;
    private int members;    

    public Groups() {
    }

    public Groups(int groupId, int groupStatus) {
        this.groupId = groupId;
        this.groupStatus = groupStatus;
    }

    public Groups(int groupStatus) {
        this.groupStatus = groupStatus;
    }
    
    public Groups(int groupId, String groupName, int semId, int groupStatus, int members) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.semId = semId;
        this.groupStatus = groupStatus;
        this.members = members;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getSemId() {
        return semId;
    }

    public void setSemId(int semId) {
        this.semId = semId;
    }

    public int getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(int groupStatus) {
        this.groupStatus = groupStatus;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

   
    
    
    

   
   
    
}

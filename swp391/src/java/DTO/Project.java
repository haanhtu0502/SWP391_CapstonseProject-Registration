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
public class Project {

    private int projectId;
    private String description;
    private String name;
    private String sourceCode;
    private int topicId;
    private int status;
    private int groupId;
    private Groups group;
    private Users lecturer;

    public Project() {
    }

    public Project(int projectId, String description, String name, String sourceCode, int topicId, int status, int groupId, Users lecturer) {
        this.projectId = projectId;
        this.description = description;
        this.name = name;
        this.sourceCode = sourceCode;
        this.topicId = topicId;
        this.status = status;
        this.groupId = groupId;
        this.lecturer = lecturer;
    }
    

    public Project(int projectId, String description, String name, String sourceCode, int topicId, int status, int groupId) {
        this.projectId = projectId;
        this.description = description;
        this.name = name;
        this.sourceCode = sourceCode;
        this.topicId = topicId;
        this.status = status;
        this.groupId = groupId;
    }

    public Project(int projectId, String description, String name, String sourceCode, int topicId, int status, int groupId, Groups group, Users lecturer) {
        this.projectId = projectId;
        this.description = description;
        this.name = name;
        this.sourceCode = sourceCode;
        this.topicId = topicId;
        this.status = status;
        this.groupId = groupId;
        this.group = group;
        this.lecturer = lecturer;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public Users getLecturer() {
        return lecturer;
    }

    public void setLecturer(Users lecturer) {
        this.lecturer = lecturer;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

}

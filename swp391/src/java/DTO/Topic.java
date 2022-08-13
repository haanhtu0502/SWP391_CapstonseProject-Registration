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
public class Topic {
    private int topicId;
    private String name;
    private int approveStatus;
    private int catergoryId;
    private String description;
    private int businessId;
    private int departmentId;
    private Users user;
    private Department department;
    private Semester semester;
    private int status;

    public Topic(int topicId, String name, int catergoryId, String description, int businessId, int departmentId, Semester semester) {
        this.topicId = topicId;
        this.name = name;
        this.catergoryId = catergoryId;
        this.description = description;
        this.businessId = businessId;
        this.departmentId = departmentId;
        this.semester = semester;
    }

    public Topic(int topicId, String name, int approveStatus, Department department, Semester semester) {
        this.topicId = topicId;
        this.name = name;
        this.approveStatus = approveStatus;
        this.department = department;
        this.semester = semester;
    }

    
    
    

    public Topic(int topicId, String name, int approveStatus, int catergoryId, String description, int businessId, int departmentId, Users user, Department department, Semester semester, int status) {
        this.topicId = topicId;
        this.name = name;
        this.approveStatus = approveStatus;
        this.catergoryId = catergoryId;
        this.description = description;
        this.businessId = businessId;
        this.departmentId = departmentId;
        this.user = user;
        this.department = department;
        this.semester = semester;
        this.status = status;
    }

    
    public Topic(int topicId, String name, int catergoryId, String description, int businessId, int departmentId,Users user, Department department, Semester semester, int status) {
        this.topicId = topicId;
        this.name = name;
        this.catergoryId = catergoryId;
        this.description = description;
        this.businessId = businessId;
        this.departmentId = departmentId;
        this.user = user;
        this.department = department;
        this.semester = semester;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }    
    
    public Topic() {
    }

    public Topic(int topicId, String name, int catergoryId, String description, int businessId, int departmentId, Users user, Department department) {
        this.topicId = topicId;
        this.name = name;
        this.catergoryId = catergoryId;
        this.description = description;
        this.businessId = businessId;
        this.departmentId = departmentId;
        this.user = user;
        this.department = department;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCatergoryId() {
        return catergoryId;
    }

    public void setCatergoryId(int catergoryId) {
        this.catergoryId = catergoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    
}

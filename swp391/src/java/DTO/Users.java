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
public class Users {

    private int userId;
    private String name;
    private String password;
    private int status;
    private int departmentId;
    private String email;
    private int roleId;
    private Department department;

    public Users(int userId, String password, int departmentId, String email, int roleId) {
        this.userId = userId;
        this.password = password;
        this.departmentId = departmentId;
        this.email = email;
        this.roleId = roleId;
    }

    public Users(int userId, String name, String password, String email, int roleId) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
    }

    public Users(int userId, String name, String password, int departmentId, String email, int roleId) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.departmentId = departmentId;
        this.email = email;
        this.roleId = roleId;
    }

    public Users(int userId, String name, String password, int departmentId, String email, int roleId, Department department) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.departmentId = departmentId;
        this.email = email;
        this.roleId = roleId;
        this.department = department;
    }

    public Users(int userId, String name, String password, int status, int departmentId, String email, int roleId, Department department) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.status = status;
        this.departmentId = departmentId;
        this.email = email;
        this.roleId = roleId;
        this.department = department;
    }

    public Users() {
    }

    public Users(int userId, String name, String password, int status, int departmentId, String email, int roleId) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.status = status;
        this.departmentId = departmentId;
        this.email = email;
        this.roleId = roleId;
    }

    public Users(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

}

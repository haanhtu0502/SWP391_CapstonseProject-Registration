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
public class ProjectLecturer {
    private int id;
    private int projectId;
    private int lecturerId;

    public ProjectLecturer() {
    }

    public ProjectLecturer(int id, int projectId, int lecturerId) {
        this.id = id;
        this.projectId = projectId;
        this.lecturerId = lecturerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLectureId(int lecturerId) {
        this.lecturerId = lecturerId;
    }
    
}

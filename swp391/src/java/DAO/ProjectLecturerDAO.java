/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ProjectLecturer;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author SE161740 Pham Nguyen Hung Anh
 */
public class ProjectLecturerDAO {

    public static ArrayList<ProjectLecturer> readAll() {
        Connection cn = null;
        ArrayList<ProjectLecturer> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select *\n"
                        + "from dbo.ProjectLecturer\n";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    int ID = rs.getInt("id");
                    int projectId = rs.getInt("projectId");
                    int lecturerId = rs.getInt("lecturerId");

                    ProjectLecturer proLec = new ProjectLecturer(ID, projectId, lecturerId);
                    list.add(proLec);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public static ProjectLecturer read(Object id) {
        Connection cn = null;
        ProjectLecturer proLec = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select * from dbo.ProjectLecturer where Id=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, id.toString());
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    proLec = new ProjectLecturer();
                    proLec.setId(rs.getInt("id"));
                    proLec.setProjectId(rs.getInt("projectId"));
                    proLec.setLectureId(rs.getInt("lecturerId"));

                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return proLec;
    }

    public static void update(ProjectLecturer proLec) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.ProjectLecturer set ProjectId=?,LecturerId=? where Id=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, proLec.getProjectId());
                stm.setInt(2, proLec.getLecturerId());
                stm.setInt(3, proLec.getId());
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public static void create(ProjectLecturer proLec) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into dbo.ProjectLecturer values(?, ?, ?)";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, proLec.getId());
                stm.setInt(2, proLec.getProjectId());
                stm.setInt(3, proLec.getLecturerId());
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void delete(Object id) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "delete dbo.ProjectLecturer where Id=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, id.toString());
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

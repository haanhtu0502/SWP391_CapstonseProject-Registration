/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.LectureTopic;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author hongq
 */
public class LectureTopicDAO {
    public static ArrayList<LectureTopic> readAll() {
        Connection cn = null;
        ArrayList<LectureTopic> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select *\n"
                        + "from dbo.LecturerTopic\n";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    int ID = rs.getInt("ID");
                    int LecturerId = rs.getInt("LecturerId");
                    int TopicId = rs.getInt("TopicId");
                    LectureTopic group = new LectureTopic(ID, LecturerId, TopicId);
                    list.add(group);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public static LectureTopic read(Object id) {
        Connection cn = null;
        LectureTopic lecTop = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select * from dbo.LecturerTopic where ID=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, id.toString());
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    lecTop = new LectureTopic();
                    lecTop.setId(rs.getInt("ID"));
                    lecTop.setLectureId(rs.getInt("LecturerId"));
                    lecTop.setTopicId(rs.getInt("TopicId"));
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return lecTop;
    }

    public static void update(LectureTopic lecTop) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.LecturerTopic set LecturerId=?,TopicId=? where ID=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, lecTop.getLectureId());
                stm.setInt(2, lecTop.getTopicId());
                stm.setInt(3, lecTop.getId());
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
                String sql = "delete dbo.LecturerTopic where TopicId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, id.toString());
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void create(LectureTopic lecTop) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into dbo.LecturerTopic values(?, ?, ?)";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, lecTop.getId());
                stm.setInt(2, lecTop.getLectureId());
                stm.setInt(3, lecTop.getTopicId());
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}

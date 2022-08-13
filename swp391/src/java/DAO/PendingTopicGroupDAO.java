/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Groups;
import DTO.PendingTopicGroup;
import DTO.Topic;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author SE161714 Ha Anh Tu
 */
public class PendingTopicGroupDAO {

    public static ArrayList<PendingTopicGroup> readAll(int depId,int userId) {
        Connection cn = null;
        ArrayList<PendingTopicGroup> list = new ArrayList<>();
        PendingTopicGroup obj = new PendingTopicGroup();
        GroupsDAO gd = new GroupsDAO();
        TopicDAO td = new TopicDAO();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select pgtt.*\n"
                        + "   from dbo.LecturerTopic lt join (select pgt.* from dbo.PendingGroupTopic pgt join dbo.Topic t on pgt.TopicId=t.TopicId ) pgtt on lt.TopicId=pgtt.TopicId join dbo.Users u on lt.LecturerId=u.UserId\n"
                        + "   where pgtt.DepartmentId=? and u.UserId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, depId);
                stm.setInt(2, userId);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int topicID = rs.getInt("topicId");
                    int groupId = rs.getInt("groupId");
                    int departmentId = rs.getInt("departmentId");
                    Topic topic = td.read(topicID);
                    Groups group = gd.read(groupId);
                    obj = new PendingTopicGroup(id, group, topic, departmentId);
                    list.add(obj);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }
    
    public static void deletebyTopicId(int topicId) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "delete dbo.PendingGroupTopic where TopicId=? ";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, topicId);
                
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    
    public static void deletebyGroupId(int groupId) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "delete dbo.PendingGroupTopic where GroupId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, groupId);
                
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    
    public static void deny(int topicId,int groupId) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "delete dbo.PendingGroupTopic where TopicId=? and GroupId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, topicId);
                stm.setInt(2, groupId);
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    
     public static int checkContainTopic(int topicId) {
        Connection cn = null;
        int status=0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select TopicId from dbo.PendingGroupTopic where TopicId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, topicId);          
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    status=rs.getInt("TopicId");
                    
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return status;
    }
}

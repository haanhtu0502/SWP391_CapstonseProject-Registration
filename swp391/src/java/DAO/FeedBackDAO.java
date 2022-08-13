/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import DTO.FeedBack;
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
public class FeedBackDAO {
    
     public static ArrayList<FeedBack> readAll() {
        Connection cn = null;
        ArrayList<FeedBack> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select * from FeedBack";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    int feedBackId = rs.getInt("FeedBackId");
                    String name = rs.getString("Name");
                    String email = rs.getString("Email");
                    String Subject = rs.getString("Subject");
                    String FeedBackMessage = rs.getString("FeedBackMessage");
                    int stuId = rs.getInt("UserId");
                    
                    FeedBack fb = new FeedBack(feedBackId, name, email, Subject, FeedBackMessage, stuId);
                    
                    list.add(fb);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }
     public static ArrayList<FeedBack> searchStudentByName(String name) {
        Connection cn = null;
        ArrayList<FeedBack> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select * from FeedBack where Name like ? ";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");                
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    int feedBackId = rs.getInt("FeedBackId");                    
                    String StuName = rs.getString("name");
                    String email = rs.getString("Email");
                    String Subject = rs.getString("Subject");
                    String FeedBackMessage = rs.getString("FeedBackMessage");
                    int stuId = rs.getInt("UserId");
                    
                    FeedBack fb = new FeedBack(feedBackId, StuName, email, Subject, FeedBackMessage, stuId);
                    list.add(fb);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }
     
     
     
     public static void delete(Object id) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "delete dbo.FeedBack where FeedBackId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1,  id.toString());
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
     
     
    
    
    public static void  create (FeedBack feedback){
        java.sql.Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into FeedBack(Name,Subject,Email,FeedBackMessage,UserId) values(?, ?, ?, ?, ?)";
                PreparedStatement stm = cn.prepareStatement(sql);                
                stm.setString(1, feedback.getName());
                stm.setString(2, feedback.getEmail());
                stm.setString(3, feedback.getSubject());
                stm.setString(4, feedback.getFeedBackMsg());
                stm.setInt(5, feedback.getUserId());
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    
    
    
    public static int countFeedBackId() {
        java.sql.Connection cn = null;
        int count = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select count(*) as feedBackId from FeedBack";
                PreparedStatement stm = cn.prepareStatement(sql);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    count = rs.getInt("feedBackId");
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return count;
    }
}

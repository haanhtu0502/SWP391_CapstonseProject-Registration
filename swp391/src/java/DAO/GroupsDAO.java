/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Groups;

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
public class GroupsDAO {
    public static ArrayList<Groups> readAll() {
        Connection cn = null;
        ArrayList<Groups> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select *\n"
                        + "from dbo.Groups\n";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    int groupID = rs.getInt("GroupId");
                    String name = rs.getString("GroupName");
                    int semID = rs.getInt("SemID");
                    int groupStatus = rs.getInt("groupStatus");
                    int members = rs.getInt("members");                    
                    Groups group = new Groups(groupID, name, semID, groupStatus, members);
                    list.add(group);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }  

    public static Groups read(Object id) {
        Connection cn = null;
        Groups groups = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select * from dbo.Groups where GroupId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, id.toString());
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    groups = new Groups();
                    groups.setGroupId(rs.getInt("groupId"));
                    groups.setGroupName(rs.getString("groupName"));
                    groups.setSemId(rs.getInt("semId"));
                    groups.setSemId(rs.getInt("groupStatus"));
                    groups.setSemId(rs.getInt("members"));
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return groups;
    }

    public static void update(Groups groups) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Groups set groupName=?,semId=?,groupStatus=?,members=? where GroupId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, groups.getGroupName());
                stm.setInt(2, groups.getSemId());
                stm.setInt(3, groups.getGroupStatus());
                stm.setInt(4, groups.getMembers());                
                stm.setInt(5, groups.getGroupId());
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

    }
         
    
    public static void updatePublicGroup(int id) {
        Connection cn = null;
        int status = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Groups set groupStatus = 1 where GroupId = ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, id);
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    
    public static void updatePrivateGroup(int id) {
        Connection cn = null;
        int status = 0;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Groups set groupStatus = 0 where GroupId = ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, id);
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
                String sql = "delete dbo.Groups where GroupId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, id.toString());
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void create(Groups groups) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into dbo.Groups values(?, ?, ?, ?, ?)";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, groups.getGroupId());
                stm.setString(2, groups.getGroupName());
                stm.setInt(3, groups.getSemId());
                stm.setInt(4, groups.getGroupStatus());
                stm.setInt(5, groups.getMembers());                
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Category;
import Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HLong
 */
public class CategoryDAO {

    public static ArrayList<Category> readAll() {
        Connection cn = null;
        ArrayList<Category> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select *\n"
                        + "from dbo.Category\n";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    int categoryId = rs.getInt("CategoryId");
                    String name = rs.getString("Name");
                    Category category = new Category(categoryId, name);
                    list.add(category);
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return list;
    }

    public static Category read(Object id) {
        Connection cn = null;
        Category category = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select * from dbo.Category where CategoryId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, id.toString());
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    category = new Category();
                    category.setCategoryId(rs.getInt("CategoryId"));
                    category.setCategoryName(rs.getString("Name"));
                }
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return category;
    }

    public static void create(Category category) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "insert into dbo.Category values(?, ?)";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(1, category.getCategoryId());
                stm.setString(2, category.getCategoryName());
                stm.executeUpdate();
                cn.close();
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void update(Category category) {
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Category set Name=? where CategoryId=?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, category.getCategoryName());
                stm.setInt(2, category.getCategoryId());
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
                String sql = "delete dbo.Category where CategoryId=?";
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

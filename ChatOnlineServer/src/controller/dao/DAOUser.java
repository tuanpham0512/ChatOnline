/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dao;

import java.sql.Connection;
import java.sql.SQLException;
import model.User;

/**
 *
 * @author Valdez
 */
public class DAOUser extends IDAO {

    public DAOUser(Connection conn) {
        this.conn = conn;
        try {
            this.statement = this.conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByUsername(User user) {
        try {
            String sql = "SELECT * FROM user WHERE username = ?";
            this.preStatement = this.conn.prepareStatement(sql);
            this.preStatement.setString(1, user.getUsername());
            rs = this.preStatement.executeQuery();
            User temp = null;
            while (rs.next()) {
                temp = new User();
                temp.setId(rs.getInt(1));
                temp.setUsername(rs.getString(2));
                temp.setPassword_hash(rs.getString(3));
                temp.setDisplay_name(rs.getString(4));
                temp.setGender(rs.getInt(5));
                temp.setAvatar_path(rs.getString(6));
                temp.setEmail(rs.getString(7));
                temp.setPhone_number(rs.getString(8));
                temp.setDescription(rs.getString(9));
                temp.setStatus(rs.getInt(10));
                break;
            }
            return temp;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserByUsernameOrEmail(User user) {
        try {
            String sql = "SELECT * FROM user WHERE username = ? OR email = ?";
            this.preStatement = this.conn.prepareStatement(sql);
            this.preStatement.setString(1, user.getUsername());
            this.preStatement.setString(2, user.getEmail());
            rs = this.preStatement.executeQuery();
            User temp = null;
            while (rs.next()) {
                temp = new User();
                temp.setId(rs.getInt(1));
                break;
            }
            return temp;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(User user) {
        try {
            String sql = "SELECT * FROM user WHERE id = ?";
            this.preStatement = this.conn.prepareStatement(sql);
            this.preStatement.setInt(1, user.getId());
            rs = this.preStatement.executeQuery();
            User temp = null;
            while (rs.next()) {
                temp = new User();
                temp.setId(rs.getInt(1));
                temp.setUsername(rs.getString(2));
                temp.setPassword_hash(rs.getString(3));
                temp.setDisplay_name(rs.getString(4));
                temp.setGender(rs.getInt(5));
                temp.setAvatar_path(rs.getString(6));
                temp.setEmail(rs.getString(7));
                temp.setPhone_number(rs.getString(8));
                temp.setDescription(rs.getString(9));
                temp.setStatus(rs.getInt(10));
                break;
            }
            return temp;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean signUpUser(User user) {
        try {
            String sql = "INSERT INTO user (username, password_hash, email, gender) VALUES (?, ?, ?, ?)";
            this.preStatement = this.conn.prepareStatement(sql);
            this.preStatement.setString(1, user.getUsername());
            this.preStatement.setString(2, user.getPassword_hash());
            this.preStatement.setString(3, user.getEmail());
            this.preStatement.setInt(4, user.getGender());
            int check = this.preStatement.executeUpdate();
            return (check != 0);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changeUserInfo(User user) {
        try {
            String sql = "UPDATE user SET display_name = ?, email = ?, phone_number = ? WHERE id = ?";
            this.preStatement = this.conn.prepareStatement(sql);
            this.preStatement.setString(1, user.getDisplay_name());
            this.preStatement.setString(2, user.getEmail());
            this.preStatement.setString(3, user.getPhone_number());
            this.preStatement.setInt(4, user.getId());
            int check = this.preStatement.executeUpdate();
            return (check != 0);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean changeUserAvatar(User user, String avatar_path) {
        try {
            String sql = "UPDATE user SET avatar_path = ? WHERE id = ?";
            this.preStatement = this.conn.prepareStatement(sql);
            this.preStatement.setString(1, avatar_path);
            this.preStatement.setInt(2, user.getId());
            int check = this.preStatement.executeUpdate();
            return (check != 0);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

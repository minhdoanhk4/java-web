/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minhda.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import minhda.utils.DBHelpers;

/**
 *
 * @author msi2k
 */
public class RegistrationDAO implements Serializable {

//    public boolean checkLogin(String username, String password) throws ClassNotFoundException, SQLException {
    public RegistrationDTO checkLogin(String username, String password) throws ClassNotFoundException, SQLException {
        RegistrationDTO result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBHelpers.getConnection();

            if (con != null) {
                //2. Create SQL Statement String
                String sql = "Select lastname, isAdmin from Registration where username = ? and password = ?";
                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. Excute Query 
                rs = stm.executeQuery();
                //5. Process
                if (rs.next()) {
                    String fullName  = rs.getString("lastname");
                    boolean role  = rs.getBoolean("isAdmin");
                    result = new RegistrationDTO(username, null, fullName, role);
                }
            }// end if connection is exited 
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String searchValue) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBHelpers.getConnection();

            if (con != null) {
                //2. Create SQL Statement String
                String sql = "select username, password, lastname, isAdmin "
                        + "from Registration "
                        + "where lastname like ?";
                // liet ke tat ca cac cot trong registration neu gia tri trong do co chua lastname
                //3. Create Statement to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Excute Query 
                rs = stm.executeQuery();
                while (rs.next()) {
                    //2.3.1 model load data from DB
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //2.3.2 model nap data vào
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullname, role);
                    this.accounts.add(dto);
                }
                //5. Process

            }// end if connection is exited 
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public boolean deleteAccount(String username) throws SQLException, ClassNotFoundException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. model Connect DB
            con = DBHelpers.getConnection();

            if (con != null) {
                //2. model query data from DB
                // 2.1. create sql string
                String sql = "delete from Registration "
                        + "where username = ?";
                //2.2. 
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. Excute Query 
                int effectedRow = stm.executeUpdate();
                if (effectedRow > 0) {
                    result = true;
                }
                //5. Process

            }// end if connection is exited 
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    public boolean updateAccount(String username, String password, boolean role) throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. model Connect DB
            con = DBHelpers.getConnection();

            if (con != null) {
                //2. model query data from DB
                // 2.1. create sql string
                String sql = "update Registration "
                        + "set password = ?, isAdmin = ? "
                        + "where username = ?";
                //2.2. 
                stm = con.prepareStatement(sql);

                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                //4. Excute Query 
                int effectedRow = stm.executeUpdate();
                if (effectedRow > 0) {
                    result = true;
                }
                //5. Process

            }// end if connection is exited 
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    public boolean createAccount(RegistrationDTO account) throws ClassNotFoundException, SQLException {
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;
        try {
            //1. model Connect DB
            con = DBHelpers.getConnection();

            if (con != null) {
                //2. model query data from DB
                // 2.1. create sql string
                String sql = "insert into registration ("
                        + "username, password, lastname, isAdmin"
                        + ") values ("
                        + "?, ?, ?, ?"
                        + ")";
                //2.2. load sql into statement
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullname());
                stm.setBoolean(4, account.getRole());
                
                //4. Excute Query 
                int effectedRow = stm.executeUpdate();
                if (effectedRow > 0) {
                    result = true;
                }
                //5. Process

            }// end if connection is exited 
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    
}

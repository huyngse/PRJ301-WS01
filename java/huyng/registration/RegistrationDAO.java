package huyng.registration;

import huyng.database.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAO {
    private List<RegistrationDTO> list;

    public boolean checkLogin(String username, String password) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.createConnection();
            if (con != null) {
                String sql = "SELECT username, password FROM Registration WHERE username = ? and password=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);

                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

        }
        return false;
    }
    
    public List<RegistrationDTO> getAccountList() {
        return list;
    }

    public void searchLastName(String searchValue) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.createConnection();
            if (con != null) {
                String sql = "SELECT username, password, lastName, isAdmin  FROM Registration WHERE lastName LIKE ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    RegistrationDTO o = new RegistrationDTO();
                    o.setUsername(rs.getString("username").trim());
                    o.setPassword(rs.getString("password").trim());
                    o.setLastName(rs.getString("lastName").trim());
                    o.setIsAdmin(rs.getBoolean("isAdmin"));
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(o);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

        }
    }
    
    public boolean deleteUser(String username) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.createConnection();
            if (con != null) {
                String sql = "DELETE FROM Registration WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username );
                int row = stm.executeUpdate();
                if (row != 0) {
                    return true;
                } 
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

        }
        return false;
    }
    
    public boolean updateRecord(String username, String password, String lastName, boolean isAdmin) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBUtils.createConnection();
            if (con != null) {
                String sql = "UPDATE Registration "
                        + "SET password = ?, isAdmin = ?, lastName = ? "
                        + "WHERE username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password );
                stm.setBoolean(2, isAdmin );
                stm.setString(3, lastName );
                stm.setString(4, username );
                int row = stm.executeUpdate();
                if (row != 0) {
                    return true;
                } 
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

        }
        return false;
    }
}

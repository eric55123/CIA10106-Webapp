package com.host;

import java.sql.*;
import java.util.*;

import util.JDBCUtil;

public class HostDaoJDBC implements HostDao {
    private static final String INSERT_STMT = "INSERT INTO host(host_account, host_pwd, host_mail, host_phone, host_status) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_STMT = "UPDATE host SET host_account = ?, host_pwd = ?, host_mail = ?, host_phone = ?, host_status = ? WHERE host_no = ?";
    private static final String DELETE_STMT = "DELETE FROM host WHERE host_no = ?";
    private static final String FIND_BY_PK = "SELECT * FROM host WHERE host_no = ?";
    private static final String GET_ALL = "SELECT * FROM host";

    static {
        try {
            Class.forName(JDBCUtil.DRIVER);
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
    }

    @Override
    public int insert(HostVo host) {
        try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
             PreparedStatement ps = con.prepareStatement(INSERT_STMT)) {

            ps.setString(1, host.getHostAccount());
            ps.setString(2, host.getHostPwd());
            ps.setString(3, host.getHostMail());
            ps.setString(4, host.getHostPhone());
            ps.setByte(5, host.getHostStatus());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int update(HostVo host) {
        try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
             PreparedStatement ps = con.prepareStatement(UPDATE_STMT)) {
            ps.setString(1, host.getHostAccount());
            ps.setString(2, host.getHostPwd());
            ps.setString(3, host.getHostMail());
            ps.setString(4, host.getHostPhone());
            ps.setByte(5, host.getHostStatus());
            ps.setInt(6, host.getHostNo());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int delete(Integer hostNo) {
        try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
             PreparedStatement ps = con.prepareStatement(DELETE_STMT)) {
            ps.setInt(1, hostNo);

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public HostVo findByPK(Integer hostNo) {
        HostVo host = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
            ps = con.prepareStatement(FIND_BY_PK);

            ps.setInt(1, hostNo);
            rs = ps.executeQuery();

            while (rs.next()) {
                host = new HostVo();
                host.setHostNo(rs.getInt("host_no"));
                host.setHostAccount(rs.getString("host_account"));
                host.setHostPwd(rs.getString("host_pwd"));
                host.setHostMail(rs.getString("host_mail"));
                host.setHostPhone(rs.getString("host_phone"));
                host.setHostStatus(rs.getByte("host_status"));
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            closeResources(con, ps, rs);
        }
        return host;
    }

    @Override
    public List<HostVo> getAll() {
        List<HostVo> hosts = new ArrayList<>();
        HostVo host = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
            ps = con.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                host = new HostVo();
                host.setHostNo(rs.getInt("host_no"));
                host.setHostAccount(rs.getString("host_account"));
                host.setHostPwd(rs.getString("host_pwd"));
                host.setHostMail(rs.getString("host_mail"));
                host.setHostPhone(rs.getString("host_phone"));
                host.setHostStatus(rs.getByte("host_status"));

                hosts.add(host);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(con, ps, rs);
        }

        return hosts;
    }

    private void closeResources(Connection con, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException se) {
                se.printStackTrace(System.err);
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException se) {
                se.printStackTrace(System.err);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }
}

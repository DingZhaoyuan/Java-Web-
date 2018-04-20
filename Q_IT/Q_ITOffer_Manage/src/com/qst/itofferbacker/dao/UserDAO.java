package com.qst.itofferbacker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qst.itofferbacker.javabean.User;
import com.qst.itofferbacker.util.DBUtil;

/** 
 * ��̨����Ա�û���Ϣ���ݿ������ 
 */
public class UserDAO {

	/**
	 * �û����
	 * @param user
	 */
	public void save(User user) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO tb_users(user_id,user_logname,user_pwd,user_realname,user_email,user_role,user_state"
				+ ") VALUES(SEQ_ITOFFER_USERS.NEXTVAL,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserLogname());
			pstmt.setString(2, user.getUserPwd());
			pstmt.setString(3, user.getUserRealname());
			pstmt.setString(4, user.getUserEmail());
			pstmt.setInt(5, user.getUserRole());
			pstmt.setInt(6, user.getUserState());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
	/**
	 * �û���¼
	 * @param userLogname
	 * @param userPwd
	 * @return
	 */
	public User login(String userLogname, String userPwd) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		User u = null;
		String sql = "SELECT user_id,user_realname,user_email,user_role,user_state FROM tb_users WHERE user_logname=? and user_pwd=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userLogname);
			pstmt.setString(2, userPwd);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				u = new User();
				u.setUserId(rs.getInt(1));
				u.setUserRealname(rs.getString(2));
				u.setUserEmail(rs.getString(3));
				u.setUserRole(rs.getInt(4));
				u.setUserState(rs.getInt(5));
				u.setUserLogname(userLogname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
		return u;
	}
	/**
	 * �����û��б��ѯ
	 * @return
	 */
	public List<User> list() {
		List<User> list = new ArrayList<User>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT user_id,user_logname,user_realname,user_email,user_role,user_state "
				+ "FROM tb_users";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				User u = new User();
				u.setUserId(rs.getInt(1));
				u.setUserLogname(rs.getString(2));
				u.setUserRealname(rs.getString(3));
				u.setUserEmail(rs.getString(4));
				u.setUserRole(rs.getInt(5));
				u.setUserState(rs.getInt(6));
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return list;
	}
	/**
	 * ��ҳ��ѯ��ҳ����Ҫ��������ҵ��Ϣ��ְλ��Ϣ
	 * @return
	 */
	public List<User> getUserPageList(int pageNo, int pageSize) {
		// ���屾ҳ��¼����ֵ
		int firstIndex = pageSize * (pageNo-1);
		List<User> list = new ArrayList<User>();
		Connection connection = DBUtil.getConnection();
		if (connection == null)
			return null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection
					.prepareStatement("SELECT * FROM ( SELECT a.* , ROWNUM rn FROM ( "
							+ "SELECT user_id,user_logname,user_realname,user_email,user_role,user_state "
							+ "FROM tb_users ) a WHERE ROWNUM<=? ) WHERE rn>? ");
			pstmt.setInt(1, firstIndex+pageSize);
			pstmt.setInt(2, firstIndex);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt(1));
				u.setUserLogname(rs.getString(2));
				u.setUserRealname(rs.getString(3));
				u.setUserEmail(rs.getString(4));
				u.setUserRole(rs.getInt(5));
				u.setUserState(rs.getInt(6));
				list.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, connection);
		}
		return list;
	}
	/**
	 * ��ѯ�����ҳ���ܼ�¼��
	 * @param pageSize
	 * @return
	 */
	public int getRecordCount() {
		int recordCount = 0;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT count(*) FROM tb_users";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				recordCount = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return recordCount;
	}
	/**
	 * �����û���ʶ��ѯ�û���Ϣ
	 * @param uid
	 * @return
	 */
	public User selectById(int uid) {
		User u = new User();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT user_id,user_logname,user_realname,user_email,user_role,user_state "
				+ "FROM tb_users WHERE user_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				u.setUserId(rs.getInt(1));
				u.setUserLogname(rs.getString(2));
				u.setUserRealname(rs.getString(3));
				u.setUserEmail(rs.getString(4));
				u.setUserRole(rs.getInt(5));
				u.setUserState(rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return u;
	}
	/**
	 * �û���Ϣ����
	 * @param user
	 */
	public void update(User user) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE tb_users SET user_realname = ?,user_email = ?,user_role = ?,user_state = ? "
				+ "WHERE user_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserRealname());
			pstmt.setString(2, user.getUserEmail());
			pstmt.setInt(3, user.getUserRole());
			pstmt.setInt(4, user.getUserState());
			pstmt.setInt(5, user.getUserId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}

}

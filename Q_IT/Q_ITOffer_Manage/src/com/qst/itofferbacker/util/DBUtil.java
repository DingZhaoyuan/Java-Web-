package com.qst.itofferbacker.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库连接获取和释放工具类
 */
public class DBUtil {

	static String user = "qstitoffer";
	static String password = "qstitoffer123";
	static String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";//jdbc:mysql://localhost:3306/qst

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//com.mysql.jdbc.Driver
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection conn = DBUtil.getConnection();
		System.out.println("获取的Connection：" + conn);
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeJDBC(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

package com.qst.itofferbacker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qst.itofferbacker.javabean.Company;
import com.qst.itofferbacker.util.DBUtil;

/**
 * ��ҵ��Ϣ���ݿ������
 */
public class CompanyDAO {

	/**
	 * ��ҵ��Ϣ���
	 * @param company
	 */
	public void save(Company company) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO tb_company("
				+ "company_id,company_name,company_area,company_size,company_type,company_brief,company_state,company_sort,company_viewnum,company_pic"
				+ ") VALUES(SEQ_ITOFFER_COMPANY.NEXTVAL,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company.getCompanyName());
			pstmt.setString(2, company.getCompanyArea());
			pstmt.setString(3, company.getCompanySize());
			pstmt.setString(4, company.getCompanyType());
			pstmt.setString(5, company.getCompanyBrief());
			pstmt.setInt(6, company.getCompanyState());
			pstmt.setInt(7, company.getCompanySort());
			pstmt.setInt(8, company.getCompanyViewnum());
			pstmt.setString(9, company.getCompanyPic());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
	/**
	 * ��ҵ�б��ѯ
	 * @return
	 */
	public List<Company> selectAll() {
		List<Company> list = new ArrayList<Company>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT company_id,company_name,company_area,company_size,company_type,company_state,company_sort,company_viewnum "
				+ "FROM tb_company ORDER BY company_id DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Company company = new Company();
				company.setCompanyId(rs.getInt(1));
				company.setCompanyName(rs.getString(2));
				company.setCompanyArea(rs.getString(3));
				company.setCompanySize(rs.getString(4));
				company.setCompanyType(rs.getString(5));
				company.setCompanyState(rs.getInt(6));
				company.setCompanySort(rs.getInt(7));
				company.setCompanyViewnum(rs.getInt(8));
				list.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return list;
	}
	/**
	 * ������ҵ��ʶ��ѯ��ҵ��ϸ��Ϣ
	 * @param companyId
	 * @return
	 */
	public Company selectById(int companyId) {
		Company company = new Company();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM tb_company WHERE company_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, companyId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				company.setCompanyId(rs.getInt(1));
				company.setCompanyName(rs.getString(2));
				company.setCompanyArea(rs.getString(3));
				company.setCompanySize(rs.getString(4));
				company.setCompanyType(rs.getString(5));
				company.setCompanyBrief(rs.getString(6));
				company.setCompanyState(rs.getInt(7));
				company.setCompanySort(rs.getInt(8));
				company.setCompanyViewnum(rs.getInt(9));
				company.setCompanyPic(rs.getString(10));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return company;
	}
	/**
	 * ��ҵ��Ϣ����
	 * @param company
	 */
	public void update(Company company) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE tb_company "
				+ "SET company_name=?,company_area=?,company_size=?,company_type=?,company_brief=?,company_state=?,company_sort=?,company_viewnum=?,company_pic=? "
				+ "WHERE company_id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company.getCompanyName());
			pstmt.setString(2, company.getCompanyArea());
			pstmt.setString(3, company.getCompanySize());
			pstmt.setString(4, company.getCompanyType());
			pstmt.setString(5, company.getCompanyBrief());
			pstmt.setInt(6, company.getCompanyState());
			pstmt.setInt(7, company.getCompanySort());
			pstmt.setInt(8, company.getCompanyViewnum());
			pstmt.setString(9, company.getCompanyPic());
			pstmt.setInt(10, company.getCompanyId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(null, pstmt, conn);
		}
	}
	/**
	 * ��ѯ������ҵ�����ƺͱ�ʶ
	 * @return
	 */
	public List<Company> selectAllCompanyName() {
		List<Company> list = new ArrayList<Company>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT company_id,company_name FROM tb_company ORDER BY company_id DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Company company = new Company();
				company.setCompanyId(rs.getInt(1));
				company.setCompanyName(rs.getString(2));
				list.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(rs, pstmt, conn);
		}
		return list;
	}

}

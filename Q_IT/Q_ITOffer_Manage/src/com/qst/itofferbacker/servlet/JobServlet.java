package com.qst.itofferbacker.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qst.itofferbacker.dao.CompanyDAO;
import com.qst.itofferbacker.dao.JobDAO;
import com.qst.itofferbacker.javabean.Company;
import com.qst.itofferbacker.javabean.Job;

/**
 * ְλ��Ϣ����Servlet
 * 
 */
@WebServlet("/JobServlet")
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JobServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ��ȡ�����������
		String type = request.getParameter("type");
		// ����ְλ����ҵDAO����
		JobDAO jobdao = new JobDAO();
		CompanyDAO companydao = new CompanyDAO();
		// ְλ��Ϣ��ѯҳ�������ְλ��Ϣ����ҵ��Ϣ�Ĳ�ѯ
		if ("list".equals(type)) {
			// �������ְλ��Ϣ
			List<Job> joblist = jobdao.selectAll();
			// ���������ҵ���ƺͱ�ʶ��Ϣ
			List<Company> companylist = companydao.selectAllCompanyName();
			request.setAttribute("joblist", joblist);
			request.setAttribute("companylist", companylist);
			request.getRequestDispatcher("manage/jobList.jsp").forward(request,
					response);
			return;
		}else if("query".equals(type)){
			int companyId = Integer.parseInt(request.getParameter("companyId"));
			String jobName = request.getParameter("jobName");
			List<Job> joblist = jobdao.query(companyId,jobName);
			List<Company> companylist = companydao.selectAllCompanyName();
			request.setAttribute("joblist", joblist);
			request.setAttribute("companylist", companylist);
			request.getRequestDispatcher("manage/jobList.jsp").forward(request,
					response);
			return;
		}
	}

}

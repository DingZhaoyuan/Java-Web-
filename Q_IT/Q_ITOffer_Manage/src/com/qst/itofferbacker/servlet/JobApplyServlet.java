package com.qst.itofferbacker.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qst.itofferbacker.dao.CompanyDAO;
import com.qst.itofferbacker.dao.JobApplyDAO;
import com.qst.itofferbacker.dao.JobDAO;
import com.qst.itofferbacker.javabean.Company;
import com.qst.itofferbacker.javabean.Job;
import com.qst.itofferbacker.javabean.JobApply;

@WebServlet("/JobApplyServlet")
public class JobApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JobApplyServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �����������ݱ���
		request.setCharacterEncoding("UTF-8");
		// ������Ӧ�ĵ����ͺͱ���
		response.setContentType("text/plain;charset=UTF-8");
		// ��ȡ�����������
		String type = request.getParameter("type");
		if("companyNameList".equals(type)){
			// ��ȡ��ҵ��ʶ������
			CompanyDAO dao = new CompanyDAO();
			List<Company> companylist = dao.selectAllCompanyName();
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getWriter(),companylist);
		}else if("jobNameList".equals(type)){
			// ��ȡְλ��ʶ������
			int companyId = Integer.parseInt(request.getParameter("companyId")==null?"0":request.getParameter("companyId"));
			JobDAO dao = new JobDAO();
			List<Job> joblist = dao.selectJobNameByCompany(companyId);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getWriter(),joblist);
		}else if("query".equals(type)){
			// ���ݲ�ѯ��������ְλ������Ϣ��ѯ
			String companyId = request.getParameter("companyId");
			String jobId = request.getParameter("jobId");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			JobApplyDAO dao = new JobApplyDAO();
			List<JobApply> applylist = dao.query(companyId,jobId,startDate,endDate);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getWriter(),applylist);
		}
	}

}

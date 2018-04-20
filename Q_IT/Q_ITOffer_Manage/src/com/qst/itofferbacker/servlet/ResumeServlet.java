package com.qst.itofferbacker.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qst.itofferbacker.javabean.ResumeBasicinfo;
import com.qst.itofferbacker.dao.ResumeDAO;
import com.qst.itofferbacker.javabean.PageBean;

/**
 * ������Ϣ����Servlet
 */
@WebServlet("/ResumeServlet")
public class ResumeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ResumeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ���������ַ�����
		request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		if("list".equals(type)){
			// ��ȡ����ҳ��
			int pageNo = Integer.parseInt(request.getParameter("pageNo")==null?"1":request.getParameter("pageNo"));
			ResumeDAO dao = new ResumeDAO();
			// ��ѯ�ܼ�¼��
			int recordCount = dao.getRecordCount();
			// ��ѯ����ҳ������
			List<ResumeBasicinfo> list = dao.getPageList(pageNo, 10);
			// ����ҳ��Ϣ��װ��PageBean������
			PageBean<ResumeBasicinfo> pageBean = new PageBean<ResumeBasicinfo>();
			pageBean.setPageNo(pageNo);
			pageBean.setRecordCount(recordCount);
			pageBean.setPageData(list);
			// ����ҳ���ݶ���PageBean�������������������
			request.setAttribute("pageBean", pageBean);
			// ������ת���������б�ҳ��
			request.getRequestDispatcher("manage/resumeList.jsp").forward(request, response);
		}else if("select".equals(type)){
			int resumeId = Integer.parseInt(request.getParameter("resumeId"));
			ResumeDAO dao = new ResumeDAO();
			ResumeBasicinfo resume = dao.selectBasicinfoByID(resumeId);
			request.setAttribute("resume", resume);
			request.getRequestDispatcher("manage/resumeView.jsp").forward(request, response);
		}
	}

}

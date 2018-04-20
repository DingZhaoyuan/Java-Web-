package com.qst.itofferbacker.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.qst.itofferbacker.dao.CompanyDAO;
import com.qst.itofferbacker.javabean.Company;
/**
 * ��ҵ��Ϣ����Servlet
 */
@WebServlet("/CompanyServlet")
@MultipartConfig
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CompanyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ���������ַ�����
		request.setCharacterEncoding("UTF-8");
		// ��ȡ�����������
		String type = request.getParameter("type");
		CompanyDAO dao = new CompanyDAO();
		if("list".equals(type)){ //��ҵ�б��ѯ
			List<Company> list = dao.selectAll();
			// ����ѯ������ҵ�б����ݴ���������������
			request.setAttribute("list", list);
			// ����ת������ҵ�б�ҳ��
			request.getRequestDispatcher("manage/companyList.jsp").forward(request, response);
		}else if("updateSelect".equals(type)){ //��ҵ��Ϣ��ѯ
			int companyId = Integer.parseInt(request.getParameter("companyId"));
			Company company = dao.selectById(companyId);
			request.setAttribute("company", company);
			request.getRequestDispatcher("manage/companyUpdate.jsp").forward(request, response);
		}else if("update".equals(type)){ // ��ҵ��Ϣ����
			// ��ȡ��ҵ������Ϣ
			int companyId = Integer.parseInt(request.getParameter("companyId"));
			String companyName = request.getParameter("companyName");
			String companyArea = request.getParameter("companyArea");
			String companySize = request.getParameter("companySize");
			String companyType = request.getParameter("companyType");
			String companyBrief = request.getParameter("companyBrief");
			int companyState = (request.getParameter("companyState") == null) ? 1 : Integer.parseInt(request.getParameter("companyState"));
			int companySort = (request.getParameter("companySort") == null) ? 0 : Integer.parseInt(request.getParameter("companySort"));
			int companyViewnum = (request.getParameter("companyViewnum") == null) ? 0 : Integer.parseInt(request.getParameter("companyViewnum"));
			String companyOldPic = request.getParameter("companyOldPic");
			Part part = request.getPart("companyPic");
			String fileName = part.getSubmittedFileName();
			// �ж��û����޸���ͼƬ
			if(!"".equals(fileName)){
				fileName = System.currentTimeMillis()
						+ fileName.substring(fileName.lastIndexOf("."));
				// ���ϴ����ļ������ڷ�������Ŀ¼�µ�upload/companyPic��Ŀ¼��
				String filepath = getServletContext().getRealPath("/");
				filepath = filepath.substring(0,filepath.indexOf(getServletContext().getServletContextName()));
				filepath = filepath + "upload/companyPic";
				File f = new File(filepath);
				if (!f.exists())
					f.mkdirs();
				part.write(filepath + "/" + fileName);
			}else
				fileName = companyOldPic;
			Company company = new Company(companyId,companyName,companyArea,companySize,companyType,companyBrief,companyState,companySort,companyViewnum,fileName);
			dao.update(company);
			response.sendRedirect("CompanyServlet?type=list");
		}
	}

}

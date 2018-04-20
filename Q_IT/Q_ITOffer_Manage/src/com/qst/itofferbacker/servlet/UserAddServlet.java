package com.qst.itofferbacker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qst.itofferbacker.dao.UserDAO;
import com.qst.itofferbacker.javabean.User;

/**
 * �û���Ϣ���Servlet
 */
@WebServlet("/UserAddServlet")
public class UserAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserAddServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ���������ַ�����
		request.setCharacterEncoding("UTF-8");
		// ��ȡ�û������Ϣ
		String userLogname = request.getParameter("userLogname");
		String userPwd = request.getParameter("userPwd");
		String userRealname = request.getParameter("userRealname");
		String userEmail = request.getParameter("userEmail");
		int userRole = (request.getParameter("userRole") == null) ? 3 : Integer.parseInt(request.getParameter("userRole"));
		int userState = (request.getParameter("userState") == null) ? 1: Integer.parseInt(request.getParameter("userState"));
		// ����һ��������װ�û���Ϣ��JavaBean
		User user = new User(userLogname,userPwd,userRealname,userEmail,userRole,userState);
		// �û���Ϣ���
		UserDAO dao = new UserDAO();
		dao.save(user);
		// ��ӳɹ����ض�����Ӧҳ��
		response.sendRedirect("manage/userList.jsp");
	}

}

package com.qst.itofferbacker.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qst.itofferbacker.dao.UserDAO;
import com.qst.itofferbacker.javabean.User;

/** 
* ��̨ϵͳ�û���Ϣ����Servlet
*/ 
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ���������ַ�����
		request.setCharacterEncoding("UTF-8");
		// ������Ӧ�ַ�����
		response.setContentType("text/html;charset=UTF-8");
		// ��ȡ��Ӧ�ַ������
		PrintWriter out = response.getWriter();
		// ��ȡ��������
		String type = request.getParameter("type");
		if("login".equals(type)){
			// ��ȡ�û��ύ����֤��
			String validateCode = request.getParameter("validateCode");
			// ��ȡ��������Ự�б������֤��
			String sessionValidateCode = (String)request.getSession().getAttribute("SESSION_VALIDATECODE");
			// �ж�����֤�벻һ�£���ʾ���󣬷��ص�½ҳ��
			if(sessionValidateCode==null || !sessionValidateCode.equals(validateCode)){
				out.print("<script type='text/javascript'>");
				out.print("alert('��֤���������');");
				out.print("window.location='login.jsp';");
				out.print("</script>");
				return ;
			}
			// ��ȡ�û���¼��Ϣ
			String userLogname = request.getParameter("userLogname");
			String userPwd = request.getParameter("userPwd");
			// �û���¼�ж�
			UserDAO dao = new UserDAO();
			User user = dao.login(userLogname,userPwd);
			if(user != null){
				// ��½�ɹ���ʹ�ûỰ�����Լ�¼�û���Ϣ���������������
				request.getSession().setAttribute("SESSION_USER", user);
				response.sendRedirect("manage/main.jsp");
			}else{
				// ��¼ʧ�ܣ�������Ϣ��ʾ�����ص�¼ҳ��
				out.print("<script type='text/javascript'>");
				out.print("alert('�û���������������������룡');");
				out.print("window.location='login.jsp';");
				out.print("</script>");
			}
		}else if("logout".equals(type)){ // �û��˳�
			// �û����λỰʧЧ
			request.getSession().invalidate();
			// ���ص�½ҳ��
			out.print("<script type='text/javascript'>");
			out.print("window.location='login.jsp';");
			out.print("</script>");
		}else if("updateSelect".equals(type)){// �û���Ϣ�޸�Ԥ��ѯ
			int uid = Integer.parseInt(request.getParameter("userId"));
			UserDAO dao = new UserDAO();
			User user = dao.selectById(uid);
			request.setAttribute("user", user);
			request.getRequestDispatcher("manage/userUpdate.jsp").forward(request, response);
		}
	}

}

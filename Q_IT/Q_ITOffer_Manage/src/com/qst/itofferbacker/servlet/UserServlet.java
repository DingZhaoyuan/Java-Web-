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
* 后台系统用户信息操作Servlet
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
		// 设置请求字符编码
		request.setCharacterEncoding("UTF-8");
		// 设置响应字符编码
		response.setContentType("text/html;charset=UTF-8");
		// 获取响应字符输出流
		PrintWriter out = response.getWriter();
		// 获取操作类型
		String type = request.getParameter("type");
		if("login".equals(type)){
			// 获取用户提交的验证码
			String validateCode = request.getParameter("validateCode");
			// 获取本次请求会话中保存的验证码
			String sessionValidateCode = (String)request.getSession().getAttribute("SESSION_VALIDATECODE");
			// 判断若验证码不一致，提示错误，返回登陆页面
			if(sessionValidateCode==null || !sessionValidateCode.equals(validateCode)){
				out.print("<script type='text/javascript'>");
				out.print("alert('验证码输入错误！');");
				out.print("window.location='login.jsp';");
				out.print("</script>");
				return ;
			}
			// 获取用户登录信息
			String userLogname = request.getParameter("userLogname");
			String userPwd = request.getParameter("userPwd");
			// 用户登录判断
			UserDAO dao = new UserDAO();
			User user = dao.login(userLogname,userPwd);
			if(user != null){
				// 登陆成功，使用会话域属性记录用户信息，进入管理主界面
				request.getSession().setAttribute("SESSION_USER", user);
				response.sendRedirect("manage/main.jsp");
			}else{
				// 登录失败，错误信息提示，返回登录页面
				out.print("<script type='text/javascript'>");
				out.print("alert('用户名或密码错误，请重新输入！');");
				out.print("window.location='login.jsp';");
				out.print("</script>");
			}
		}else if("logout".equals(type)){ // 用户退出
			// 用户本次会话失效
			request.getSession().invalidate();
			// 返回登陆页面
			out.print("<script type='text/javascript'>");
			out.print("window.location='login.jsp';");
			out.print("</script>");
		}else if("updateSelect".equals(type)){// 用户信息修改预查询
			int uid = Integer.parseInt(request.getParameter("userId"));
			UserDAO dao = new UserDAO();
			User user = dao.selectById(uid);
			request.setAttribute("user", user);
			request.getRequestDispatcher("manage/userUpdate.jsp").forward(request, response);
		}
	}

}

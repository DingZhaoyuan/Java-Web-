package com.qst.itofferbacker.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * �û�����Ȩ�޹�����
 */
@WebFilter(
		urlPatterns = { "/manage/*" }, 
		servletNames = {"com.qst.itofferbacker.servlet.CompanyAddServlet", 
		"com.qst.itofferbacker.servlet.CompanyServlet", 
		"com.qst.itofferbacker.servlet.ResumeServlet",
		"com.qst.itofferbacker.servlet.UserAddServlet",
		"com.qst.itofferbacker.servlet.UserServlet"}, 
		initParams = { @WebInitParam(name = "loginPage", value = "login.jsp")})
public class AuthorityFilter implements Filter {

	private String loginPage;
	
    public AuthorityFilter() {

    }
	public void init(FilterConfig fConfig) throws ServletException {
		// ��ȡ����������ʱת���ҳ��
		loginPage = fConfig.getInitParameter("loginPage");
		if (null == loginPage) {
			loginPage = "login.jsp";
		}
	}
	public void destroy() {
		this.loginPage = null;
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		// �ж�����ǵ�¼���������
		if("/UserServlet".equals(req.getServletPath()) && "type=login".equals(req.getQueryString())){
			chain.doFilter(request, response);
			return;
		}
		// �жϱ����ص������û��Ƿ��ڵ�¼״̬����δ��¼�����ص�¼ҳ��
		if (session.getAttribute("SESSION_USER") == null) {
			resp.sendRedirect(req.getContextPath() + "/" + loginPage);
		} else {
			chain.doFilter(request, response);
		}
	}
}

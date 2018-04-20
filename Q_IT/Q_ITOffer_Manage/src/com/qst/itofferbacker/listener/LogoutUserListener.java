package com.qst.itofferbacker.listener;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.qst.itofferbacker.javabean.User;
/** 
* �����û��˳������� 
*/ 
@WebListener
public class LogoutUserListener implements HttpSessionListener {

    public LogoutUserListener() {
    }

    public void sessionCreated(HttpSessionEvent arg0)  { 
    }

    public void sessionDestroyed(HttpSessionEvent event)  {
    	// �жϼ��������Ƿ�Ϊ��¼�û���Ϣ�ĻỰ������
    	Enumeration<String> attrnames = event.getSession().getAttributeNames();
    	while(attrnames.hasMoreElements()){
    		System.out.println(attrnames.nextElement());
    		if("SESSION_USER".equals(attrnames.nextElement())){
	    		// ��ȡ�Ự������ֵ����ǰ��¼���û�����
	    		User user = (User) event.getSession().getAttribute("SESSION_USER");
	    		// ��ȡӦ�������Ķ���
	    		ServletContext application = event.getSession().getServletContext();
	    		// ��ȡ�����Ӧ���������е������û���Ϣ�б�
	    		Map<String,User> onlineUserMap = (Map<String,User>)application.getAttribute("ONLINE_USER");
	    		if(onlineUserMap != null){
		    		// ����ǰ��¼���û������Ƴ�Ӧ�������ԣ������û���Ϣ�б�
		    		onlineUserMap.remove(user.getUserLogname());
		    		application.setAttribute("ONLINE_USER", onlineUserMap);
		    	}
    		}
    	}
    }
	
}

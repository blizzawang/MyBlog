package com.yxq.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yxq.dao.LogonDao;
import com.yxq.valuebean.MasterBean;

public class LogXServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action==null)			
			action="";
		if(action.equals("islogon")){
			this.isLogon(request,response);
		}
		if(action.equals("logon")){
			this.logon(request,response);
		}
		if(action.equals("logout")){
			this.logout(request,response);
		}
	}
	public void isLogon(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String forward="";
		HttpSession session=request.getSession();
		if(session.getAttribute("logoner")!=null)
			forward="admin/AdminIndex.jsp";
		else
			forward="admin/logon.jsp";
		response.sendRedirect(forward);
	
	}
	public boolean validateLogon(HttpServletRequest request, HttpServletResponse response){
		boolean mark=true;
		String messages="";
		String name=request.getParameter("userName");
		String password=request.getParameter("userPass");
		if(name==null||name.equals("")){
			mark=false;
			messages+="<li>请输入 <b>用户名！</b></li>";
		}
		if(password==null||password.equals("")){
			mark=false;
			messages+="<li>请输入 <b>密&nbsp;&nbsp;码！</b></li>";
		}
		request.setAttribute("messages",messages);	
		return mark;
	}
	public void logon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		boolean flag=validateLogon(request,response);
		RequestDispatcher rd=null;
		if(flag){
			LogonDao masterDao=new LogonDao();
			MasterBean logoner=new MasterBean();
			logoner.setMasterName(request.getParameter("userName"));
			logoner.setMasterPass(request.getParameter("userPass"));
			boolean mark=masterDao.logon(logoner);
			if(!mark){
				request.setAttribute("messages","<li>输入的用户名或密码错误！</li>");
				rd=request.getRequestDispatcher("/admin/logon.jsp");
				rd.forward(request,response);
			}
			else{
				HttpSession session=request.getSession();
				session.setAttribute("logoner",logoner);
				response.sendRedirect("admin/AdminIndex.jsp");
			}			
		}
		else{
			rd=request.getRequestDispatcher("/admin/logon.jsp");
			rd.forward(request,response);
		}
	}
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session=request.getSession();
		session.removeAttribute("logoner");
		RequestDispatcher rd=request.getRequestDispatcher("/front/FrontIndex.jsp");
		rd.forward(request,response);
	}
}

package com.yxq.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yxq.dao.ArticleDao;
import com.yxq.dao.ArticleTypeDao;
import com.yxq.dao.ReviewDao;
import com.yxq.toolsbean.MyTools;
import com.yxq.valuebean.ArticleBean;
import com.yxq.valuebean.ArticleTypeBean;
import com.yxq.valuebean.ReviewBean;

public class ArticleServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action==null)			
			action="";
		if (action.equals("select"))
			this.selectArticle(request, response);					//获取某类别下所有文章
		if (action.equals("adminSelectList"))
			this.adminSelectList(request,response);
		if (action.equals("adminSelectSingle"))
			this.adminSelectSingle(request,response);
		if (action.equals("add"))
			this.addArticle(request, response);						//增加文章
		if (action.equals("delete"))
			this.deleteArticle(request, response);					//删除文章
		if (action.equals("modify"))
			this.modifyArticle(request, response);					//修改文章
		if (action.equals("read"))
			this.readArticle(request, response);					//阅读文章
		if (action.equals("followAdd"))
			this.validateFollow(request, response);					//发表文章回复
		if (action.equals("typeAdd"))
			this.addArticleType(request, response);					//增加文章类别
		if (action.equals("typeSelect"))
			this.selectArticleType(request, response);				//查看文章类别
		if (action.equals("typeModify"))
			this.modifyArticleType(request, response);				//修改文章类别
		if (action.equals("typeDelete"))
			this.deleteArticleType(request, response);				//删除文章类别
	}
	/**
	 * @功能 验证评论信息
	 */
	public void validateFollow(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean mark=true;
		String messages="";
		
		String content=request.getParameter("reContent");
		if(content==null||content.equals("")){
			mark=false;
			messages="<li>请输入 <b>评论内容！</b></li>";
		}
		if(mark){
			content=MyTools.toChinese(content);
			if(content.length()>1000){
				mark=false;
				messages="<li><b>评论内容</b> 最多允许输入1000个字符！</li>";
			}
		}
		if(!mark){
			request.setAttribute("messages",messages);
			RequestDispatcher rd=request.getRequestDispatcher("/front/article/error.jsp");
			rd.forward(request,response);
		}
		else{
			followAdd(request,response);			
		}		
	}
	/**
	 * @功能 添加文章评论 
	 */
	public void followAdd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("articleId"));
		String author=MyTools.toChinese(request.getParameter("reAuthor"));
		String content=MyTools.toChinese(request.getParameter("reContent"));
		String today=MyTools.changeTime(new Date());
		if(author==null||author.equals(""))
			author="匿名好友";		
		
		ReviewBean reviewBean = new ReviewBean();		
		reviewBean.setArticleId(id);		
		reviewBean.setReAuthor(author);
		reviewBean.setReContent(content);
		reviewBean.setReSdTime(today);
		
		ReviewDao reviewDao = new ReviewDao();
		String messages="";
		String forward="";
		boolean mark=reviewDao.operationReview("add",reviewBean);
		if (mark) {
			forward="/front/article/success.jsp";
			messages="<li>发表评论成功！</li>";
			
		} else {
			forward="/front/article/error.jsp";
			messages="<li>发表评论失败！</li>";
		}
		request.setAttribute("messages",messages);
		RequestDispatcher rd=request.getRequestDispatcher(forward);
		rd.forward(request,response);	

	}
	/**
	 * @功能 阅读文章
	 * @实现 1.增加文章阅读次数<br>2.获取指定文章信息<br>3.获取对该文章的所有评论
	 */
	public void readArticle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		ArticleBean articleBean = new ArticleBean();
		ArticleDao articleDao = new ArticleDao();	
		ReviewDao reviewDao=new ReviewDao();
		
		String strId=request.getParameter("id");
		int id=MyTools.strToint(strId);		
		articleBean.setId(id);
		
		articleDao.operationArticle("readTimes", articleBean);				//累加阅读次数
		articleBean=articleDao.queryArticleSingle(id);						//查询指定文章的详细内容
		session.setAttribute("readSingle", articleBean);					//保存articleBean到request对象中
		
		List reviewlist=reviewDao.queryReview(id);
		session.setAttribute("reviewlist",reviewlist);
		
		RequestDispatcher rd = request.getRequestDispatcher("/front/article/ArticleSingle.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * @功能 修改文章
	 */
	public void modifyArticle(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session=request.getSession();
		RequestDispatcher rd=null;
		ArticleDao articleDao=new ArticleDao();		
		String type=request.getParameter("type");
		if(type==null)
			type="";
		if(!type.equals("doModify")){			
			int id=MyTools.strToint(request.getParameter("id"));
			ArticleBean articleBean=articleDao.queryArticleSingle(id);			
			request.setAttribute("modifySingle",articleBean);
			rd=request.getRequestDispatcher("/admin/article/ArticleModify.jsp");
			rd.forward(request,response);
		}
		else{
			String messages="";
			String href="";
			String forward="";
			boolean flag=validateArticle(request,response);
			if(flag){
				ArticleBean articleBean = new ArticleBean();
				articleBean.setId(MyTools.strToint(request.getParameter("id")));
				articleBean.setTypeId(Integer.valueOf(request.getParameter("typeId")));
				articleBean.setTitle(MyTools.toChinese(request.getParameter("title")));
				articleBean.setCreate(MyTools.toChinese(request.getParameter("create")));
				articleBean.setInfo(MyTools.toChinese(request.getParameter("info")));
				articleBean.setContent(MyTools.toChinese(request.getParameter("content")));
				
				boolean mark=articleDao.operationArticle("modify",articleBean);
				if (mark) {
					messages="<li>修改成功！</li>";
					href="<a href='ArticleServlet?action=adminSelectList&typeId="+session.getAttribute("showTypeId")+"'>[继续修改其他文章]</a>";
					forward="/admin/success.jsp";					
				} else {
					messages="<li>修改失败！</li>";
					href="<a href='javascript:window.history.go(-1)'>[返回]</a>";
					forward="/admin/error.jsp";
				}
				request.setAttribute("messages",messages);
				request.setAttribute("href",href);
			}
			else{
				href="<a href='javascript:window.history.go(-1)>[返回]</a>";
				forward="/admin/error.jsp";
				request.setAttribute("href",href);
			}
			rd=request.getRequestDispatcher(forward);
			rd.forward(request,response);			
		}
		
	}

	/**
	 * @功能 后台-删除文章 
	 */
	public void deleteArticle(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		ArticleDao articleDao = new ArticleDao();
		ArticleBean articleBean = new ArticleBean();

		String messages="";
		String href="";
		String forward="";
		articleBean.setId(MyTools.strToint(request.getParameter("id")));		
		boolean mark=articleDao.operationArticle("delete", articleBean);		
		if (mark) {
			String typeId=request.getParameter("typeId");
			messages+="<li>删除文章成功！</li>";
			href="<a href='ArticleServlet?action=adminSelectList&typeId="+typeId+"'>[继续删除其他文章]</a>";
			forward="/admin/success.jsp";
		
		} else {
			messages+="<li>删除文章失败！</li>";
			href="<a href='javascript:window.history.go(-1)'>[返回]</a>";
			forward="/admin/error.jsp";
		}
		request.setAttribute("messages",messages);
		request.setAttribute("href",href);
		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);
	}
	/**
	 * @功能 验证发表文章信息
	 */
	public boolean validateArticle(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		boolean mark=true;
		String messages="";
		
		String typeId=request.getParameter("typeId");
		String title=request.getParameter("title");
		String create=request.getParameter("create");
		String info=request.getParameter("info");
		String content=request.getParameter("content");
		
		if(typeId==null||typeId.equals("")){
			mark=false;
			messages+="<li>请选择 <b>文章类别！</b></li>";
		}
		if(title==null||title.equals("")){
			mark=false;
			messages+="<li>请输入 <b>文章标题！</b></li>";
		}
		if(create==null||create.equals("")){
			mark=false;
			messages+="<li>请选择 <b>文章来源！</b></li>";
		}
		if(info==null||info.equals("")){
			mark=false;
			messages+="<li>请输入 <b>文章描述！</b></li>";
		}
		if(content==null||content.equals("")){
			mark=false;
			messages+="<li>请输入 <b>文章内容！</b></li>";
		}		
		if(mark){
			title=MyTools.toChinese(title);
			content=MyTools.toChinese(content);
			if(title.length()>20){
				mark=false;
				messages+="<li><b>文章标题</b> 最多允许输入20个字符！</li>";
			}
			if(content.length()>4000){
				mark=false;
				messages+="<li><b>文章内容</b> 最多允许输入4000个字符！</li>";
			}
		}
		request.setAttribute("messages",messages);
		return mark;
	}
	/**
	 * @功能 后台-增加文章
	 */
	public void addArticle(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		String messages = "";
		String href="";
		String forward="";
		
		boolean flag=validateArticle(request,response);		
		if(flag){
			ArticleBean articleBean = new ArticleBean();
			articleBean.setTypeId(MyTools.strToint(request.getParameter("typeId")));
			articleBean.setTitle(MyTools.toChinese(request.getParameter("title")));
			articleBean.setContent(MyTools.changeHTML(MyTools.toChinese(request.getParameter("content"))));
			articleBean.setSdTime(MyTools.changeTime(new Date()));
			articleBean.setCreate(MyTools.toChinese(request.getParameter("create")));
			articleBean.setInfo(MyTools.toChinese(request.getParameter("info")));
			articleBean.setCount(0);
			
			ArticleDao articleDao = new ArticleDao();
			boolean mark=articleDao.operationArticle("add", articleBean);
			if(mark) {
				messages = "<li>发表文章成功！</li>";
				href="<a href='admin/article/ArticleAdd.jsp'>[继续发表]</a>";
				forward="/admin/success.jsp";
			}
			else{
				messages="<li>发表文章失败！</li>";
				href="<a href='javascript:window.history.go(-1)'>[返回]</a>";
				forward="/admin/error.jsp";
			}			
			request.setAttribute("messages",messages);
			request.setAttribute("href",href);
		}
		else{
			href="<a href='javascript:window.history.go(-1)'>[返回]</a>";
			forward="/admin/error.jsp";			
			request.setAttribute("href",href);
		}
		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);	
	}
	/**
	 * @功能 实现后台文章管理中的文章浏览功能 
	 */
	public void adminSelectList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session=request.getSession();
		ArticleDao articleDao = new ArticleDao();
		String strId=request.getParameter("typeId");
		if(strId==null||strId.equals(""))
			strId="-1";
		int typeId=Integer.parseInt(strId);
		session.setAttribute("showTypeId",typeId);
		List articleList=articleDao.queryArticle(typeId,"all");
		request.setAttribute("articleList",articleList);
		RequestDispatcher rd=request.getRequestDispatcher("/admin/article/ArticleList.jsp");
		rd.forward(request,response);
	}
	public void adminSelectSingle(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {		
		int id=MyTools.strToint(request.getParameter("id"));		
		ArticleDao articleDao = new ArticleDao();		
		
		ArticleBean articleBean=articleDao.queryArticleSingle(id);						//查询指定文章的详细内容
		request.setAttribute("articleSingle",articleBean);		
		
		RequestDispatcher rd=request.getRequestDispatcher("/admin/article/ArticleSingle.jsp");
		rd.forward(request,response);
	}
	/**
	 * @功能 从数据表中获取某类别下的所有文章 
	 */
	public void selectArticle(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {		
		ArticleDao articleDao = new ArticleDao();
		String strId=request.getParameter("typeId");		
		if(strId==null||strId.equals(""))
			strId="-1";
		int typeId=Integer.parseInt(strId);
		List articleList=articleDao.queryArticle(typeId,"all");
		
		request.setAttribute("articleList",articleList);
		RequestDispatcher rd=request.getRequestDispatcher("/front/article/ArticleList.jsp");
		rd.forward(request,response);
	}
	public boolean validateType(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		boolean mark=true;
		String messages="";
		
		String typeName=request.getParameter("typeName");
		String typeInfo=request.getParameter("typeInfo");
	
		
		if(typeName==null||typeName.equals("")){
			mark=false;
			messages+="<li>请输入 <b>类别名称！</b></li>";
		}
		if(typeInfo==null||typeInfo.equals("")){
			mark=false;
			messages+="<li>请输入 <b>类别介绍！</b></li>";
		}		
		request.setAttribute("messages",messages);
		return mark;
	}
	
	/**
	 * @功能 后台-增加文章类别 
	 */
	public void addArticleType(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		String messages = "";
		String href="";
		String forward="";	
		
		boolean flag=validateType(request,response);
		if(flag){
			ArticleTypeBean typeBean = new ArticleTypeBean();
			typeBean.setTypeName(MyTools.toChinese(request.getParameter("typeName")));
			typeBean.setTypeInfo(MyTools.toChinese(request.getParameter("typeInfo")));
			
			ArticleTypeDao articleTypeDao = new ArticleTypeDao();
			boolean mark=articleTypeDao.operationArticleType("add", typeBean);
			if(mark) {
				messages+="<li>添加文章类别成功！</li>";
				href="<a href='admin/article/ArticleTypeAdd.jsp'>[继续添加文章类别]</a>";
				forward="/admin/success.jsp";
				
			}
			else {
				messages+="<li>添加文章类别失败！</li>";
				href="<a href='javascript:window.history.go(-1)'>[返回]</a>";
				forward="/admin/error.jsp";
			}
			request.setAttribute("messages",messages);
			request.setAttribute("href",href);
		}
		else{
			href="<a href='javascript:window.history.go(-1)'>[返回]</a>";
			forward="/admin/error.jsp";
			request.setAttribute("href",href);			
		}
		RequestDispatcher rd=request.getRequestDispatcher(forward);
		rd.forward(request,response);		
	}
	public void selectArticleType(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		ArticleTypeDao typeDao=new ArticleTypeDao();
		List typelist=typeDao.queryTypeList();
		request.setAttribute("typelist",typelist);
		RequestDispatcher rd=request.getRequestDispatcher("/admin/article/ArticleTypeList.jsp");
		rd.forward(request,response);		
	}
	/**
	 * @功能 后台-修改文章类别 
	 */
	public void modifyArticleType(HttpServletRequest request,	HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd=null;
		ArticleTypeBean typeBean=null;
		ArticleTypeDao typeDao=new ArticleTypeDao();		
		
		String type=request.getParameter("type");
		if(type==null)
			type="";
		if(!type.equals("doModify")){			
			int typeId=MyTools.strToint(request.getParameter("typeId"));
			typeBean=typeDao.queryTypeSingle(typeId);
			request.setAttribute("typeSingle",typeBean);
			rd=request.getRequestDispatcher("/admin/article/ArticleTypeModify.jsp");
			rd.forward(request,response);
		}
		else{
			String messages="";
			String href="";
			String forward="";
			boolean flag=validateType(request,response);
			if(flag){
				typeBean = new ArticleTypeBean();
				typeBean.setId(MyTools.strToint(request.getParameter("typeId")));
				typeBean.setTypeName(MyTools.toChinese(request.getParameter("typeName")));
				typeBean.setTypeInfo(MyTools.toChinese(request.getParameter("typeInfo")));			
				
				boolean mark=typeDao.operationArticleType("modify",typeBean);
				if (mark) {					
					messages="<li>修改类别成功！</li>";
					href="<a href='ArticleServlet?action=typeSelect'>[继续修改其他类别]</a>";
					forward="/admin/success.jsp";
					
				} else {
					messages="<li>修改失败！</li>";
					href="<a href='javascript:window.history.go(-1)>[返回]</a>";
					forward="/admin/error.jsp";
				}
				request.setAttribute("messages",messages);
				request.setAttribute("href",href);
			}
			else{
				href="<a href='javascript:window.history.go(-1)>[返回]</a>";
				forward="/admin/error.jsp";
				request.setAttribute("href",href);
			}
			rd=request.getRequestDispatcher(forward);
			rd.forward(request,response);			
		}
	}
	/**
	 * @功能 后台-删除文章类别
	 */
	public void deleteArticleType(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		ArticleTypeDao typeDao = new ArticleTypeDao();
		ArticleTypeBean typeBean = new ArticleTypeBean();

		String messages="";
		String href="";
		String forward="";
		
		typeBean.setId(MyTools.strToint(request.getParameter("typeId")));		
		boolean mark=typeDao.operationArticleType("delete",typeBean);		
		if (mark) {			
			messages+="<li>删除类别成功！</li>";
			href="<a href='ArticleServlet?action=typeSelect'>[继续删除其他类别]</a>";
			forward="/admin/success.jsp";
		
		} else {
			messages+="<li>删除类别失败！</li>";
			href="<a href='javascript:window.history.go(-1)'>[返回]</a>";
			forward="/admin/error.jsp";
		}
		request.setAttribute("messages",messages);
		request.setAttribute("href",href);
		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);
	}
}

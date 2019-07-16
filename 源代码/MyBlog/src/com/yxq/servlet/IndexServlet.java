package com.yxq.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yxq.dao.ArticleDao;
import com.yxq.dao.ArticleTypeDao;
import com.yxq.dao.FriendDao;
import com.yxq.dao.LogonDao;
import com.yxq.dao.PhotoDao;
import com.yxq.dao.WordDao;
import com.yxq.valuebean.MasterBean;

public class IndexServlet extends HttpServlet {
	private static MasterBean masterBean;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request,response);
	}	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		ArticleDao articleDao = new ArticleDao();
		ArticleTypeDao artTypeDao = new ArticleTypeDao();
		PhotoDao photoDao = new PhotoDao();
		WordDao wordDao=new WordDao();
		FriendDao friendDao=new FriendDao();
		
		/********** 获取在主页面的内容显示区中显示的内容 *********/
		//从tb_article数据表中获取前3篇文章 
		List articleList=articleDao.queryArticle(-1,null);
		request.setAttribute("articleList",articleList);
		//从tb_photo数据表中获取前8张照片
		List photoList=photoDao.queryPhoto("sub");
		request.setAttribute("photoList",photoList);
		
		/********** 获取在页面侧栏中显示的内容 *********/
		/* 从tb_word数据表中获取前5条留言 */
		List wordList=wordDao.queryWord("sub");
		session.setAttribute("wordList",wordList);
		/* 从tb_article数据表中获取前5章推荐文章 */
		List artTJList=articleDao.queryArticle(4,"sub");
		session.setAttribute("artTJList",artTJList);
		/* 从tb_friend数据表中获取前5位好友信息 */
		List friendList=friendDao.queryFriend("sub");
		session.setAttribute("friendList", friendList);		
		
		/********** 获取文章类别 *******************/
		/* 从tb_articleType数据表中获取文章类别 */
		List artTypeList=artTypeDao.queryTypeList();
		session.setAttribute("artTypeList",artTypeList);
		
		/*********** 保存博主信息 *****************/
		session.setAttribute("master",masterBean);
		//response.sendRedirect("/front/FrontIndex.jsp");
		RequestDispatcher rd=request.getRequestDispatcher("/front/FrontIndex.jsp");
		rd.forward(request,response);
	}
	static{
		LogonDao logonDao=new LogonDao();
		masterBean=logonDao.getMaster();		
	}
}

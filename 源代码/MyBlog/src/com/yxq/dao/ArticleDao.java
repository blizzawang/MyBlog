package com.yxq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yxq.toolsbean.DB;
import com.yxq.valuebean.ArticleBean;

public class ArticleDao {
	private DB connection = null;
	private ArticleBean articleBean = null;

	public ArticleDao() {
		connection = new DB();
	}

	/**
	 * @功能 实现对文章进行增、删、改的操作
	 * @参数 oper为一个String类型变量，用来表示要进行的操作；single为ArticleBean类对象，用来存储某个文章的信息
	 * @返回值 boolean型值
	 */
	public boolean operationArticle(String oper, ArticleBean single) {		
		/* 生成SQL语句 */
		String sql = null;
		if (oper.equals("add"))					//发表新文章
			sql = "insert into tb_article(article_typeID,article_title,article_content,article_sdTime,article_create,article_info,article_count) values ('" + single.getTypeId() + "','"+ single.getTitle() + "','" + single.getContent() + "','"+ single.getSdTime()+ "','"+single.getCreate()+"','" + single.getInfo()+"','"+single.getCount() + "')";
		if (oper.equals("modify"))				//修改文章
			sql = "update tb_article set article_typeID='" + single.getTypeId()+ "',article_title='" + single.getTitle() + "',article_content='"+ single.getContent() +"',article_create='"+single.getCreate()+ "',article_info='"+single.getInfo()+"' where id='" + single.getId()+"'";
		if (oper.equals("delete"))				//删除文章
			sql = "delete from tb_article where id='" + single.getId()+"'";
		if (oper.equals("readTimes"))			//累加阅读次数
			sql = "update tb_article set article_count=article_count+1 where id='"+ single.getId()+"'";
		
		/* 执行SQL语句 */	
		boolean flag =connection.executeUpdate(sql);
		return flag;
	}

	/** 
	 * @功能 查询指定类别的文章
	 * @参数 typeId表示文章类别ID值
	 * @返回值 List集合
	 */
	public List queryArticle(int typeId,String type) {
		List articlelist = new ArrayList();
		String sql = "";
		if (typeId <=0)					//不按类别查询，查询出前3条记录
			sql = "select * from tb_article order by article_sdTime DESC LIMIT 0, 2";
		else							//按类别查询
			if(type==null||type.equals("")||!type.equals("all"))
				sql = "select * from tb_article where article_typeID='" + typeId+ "' order by article_sdTime DESC LIMIT 0, 5";
			else
				sql = "select * from tb_article where article_typeID='" + typeId+ "' order by article_sdTime DESC";
		ResultSet rs = connection.executeQuery(sql);
		if(rs!=null){
			try {
				while (rs.next()) {
					/* 获取文章信息 */
					articleBean = new ArticleBean();
					articleBean.setId(rs.getInt(1));
					articleBean.setTypeId(rs.getInt(2));
					articleBean.setTitle(rs.getString(3));
					articleBean.setContent(rs.getString(4));
					articleBean.setSdTime(rs.getString(5));
					articleBean.setCreate(rs.getString(6));
					articleBean.setInfo(rs.getString(7));					
					articleBean.setCount(rs.getInt(8));
					
					/* 查询tb_review数据表统计当前文章的评论数 */
					sql="select count(id) from tb_review where review_articleId='"+articleBean.getId()+"'";
					ResultSet rsr=connection.executeQuery(sql);
					if(rsr!=null){
						rsr.next();
						articleBean.setReview(rsr.getInt(1));
						rsr.close();						
					}					
					articlelist.add(articleBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				connection.closed();
			}			
		}
		return articlelist;
	}

	/**
	 * @功能 查询指定文章的详细内容
	 * @参数 id为文章ID值
	 * @返回值 ArticleBean类对象，封装了文章信息
	 */
	public ArticleBean queryArticleSingle(int id) {
		String sql = "select * from tb_article where id='" + id + "'";
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				articleBean = new ArticleBean();
				articleBean.setId(rs.getInt(1));
				articleBean.setTypeId(rs.getInt(2));
				articleBean.setTitle(rs.getString(3));
				articleBean.setContent(rs.getString(4));
				articleBean.setSdTime(rs.getString(5));
				articleBean.setCreate(rs.getString(6));
				articleBean.setInfo(rs.getString(7));					
				articleBean.setCount(rs.getInt(8));
				
				/* 查询tb_review数据表统计当前文章的评论数 */
				sql="select count(id) from tb_review where review_articleId='"+articleBean.getId()+"'";
				ResultSet rsr=connection.executeQuery(sql);
				if(rsr!=null){
					rsr.next();
					articleBean.setReview(rsr.getInt(1));
					rsr.close();						
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articleBean;
	}
}

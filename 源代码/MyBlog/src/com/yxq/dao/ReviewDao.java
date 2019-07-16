package com.yxq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yxq.toolsbean.DB;
import com.yxq.valuebean.ReviewBean;

public class ReviewDao {
	private DB connection = null;

	public ReviewDao() {
		connection = new DB();
	}

	public boolean operationReview(String operation, ReviewBean single) {
		boolean flag = false;
		String sql = "";
		if (operation.equals("add"))
			sql = "insert into tb_review(review_articleId,review_author,review_content,review_sdTime) values (" + single.getArticleId()+ ",'" + single.getReAuthor()+"','"+single.getReContent()+"','"+single.getReSdTime()+"')";
		if (operation.equals("É¾³ý"))
			sql = "delete from tb_restore where id='" + single.getId() + "'";
		if (connection.executeUpdate(sql)) {
			flag = true;
		}
		return flag;
	}

	public List queryReview(int articleId) {
		List list = new ArrayList();
		String sql = "select * from tb_review where review_articleId='" + articleId + "'order by review_sdTime DESC";
		ReviewBean reviewBean = null;
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				reviewBean = new ReviewBean();
				reviewBean.setId(rs.getInt(1));
				reviewBean.setArticleId(rs.getInt(2));
				reviewBean.setReAuthor(rs.getString(3));
				reviewBean.setReContent(rs.getString(4));
				reviewBean.setReSdTime(rs.getString(5));
				list.add(reviewBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}

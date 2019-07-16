package com.yxq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yxq.toolsbean.DB;
import com.yxq.valuebean.ArticleTypeBean;

public class ArticleTypeDao {
	private DB connection = null;

	public ArticleTypeDao() {
		connection = new DB();
	}

	public boolean operationArticleType(String operation, ArticleTypeBean single) {		
		String sql = null;
		if (operation.equals("add"))
			sql = "insert into tb_articleType(articleType_name,articleType_info) values ('" + single.getTypeName()+ "','" + single.getTypeInfo() + "')";
		if (operation.equals("modify"))
			sql="update tb_articleType set articleType_name='"+single.getTypeName()+"',articleType_info='"+single.getTypeInfo()+"' where articleType_id='"+single.getId()+"'";
		if (operation.equals("delete"))
			sql = "delete from tb_articleType where articleType_id='" + single.getId()+"'" ;
		boolean flag=connection.executeUpdate(sql);
		return flag;
	}
	
	public ArticleTypeBean queryTypeSingle(int id) {
		ArticleTypeBean typeBean=null;
		String sql = "select * from tb_articleType where articleType_id='" + id+"'";
		ResultSet rs = connection.executeQuery(sql);
		if(rs!=null){
			try {
				if(rs.next()){
					typeBean=new ArticleTypeBean();
					typeBean.setId(rs.getInt(1));
					typeBean.setTypeName(rs.getString(2));
					typeBean.setTypeInfo(rs.getString(3));
				}
			} catch (SQLException e) {
				typeBean=null;
				e.printStackTrace();
			}			
		}
		return typeBean;
	}

	public List queryTypeList() {
		List typelist = null;		
		String sql = "select * from tb_articleType";
		ResultSet rs = connection.executeQuery(sql);
		if(rs!=null){
			typelist=new ArrayList();
			try {
				while(rs.next()){
					ArticleTypeBean typeBean=new ArticleTypeBean();
					typeBean.setId(rs.getInt(1));
					typeBean.setTypeName(rs.getString(2));
					typeBean.setTypeInfo(rs.getString(3));
					typelist.add(typeBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return typelist;
	}

}

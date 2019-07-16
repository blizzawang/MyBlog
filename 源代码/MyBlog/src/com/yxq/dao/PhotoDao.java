package com.yxq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yxq.toolsbean.DB;
import com.yxq.valuebean.PhotoBean;

public class PhotoDao {
	private DB connection = null;
	public PhotoDao() {
		connection = new DB();
	}

	public boolean operationPhoto(String operation, PhotoBean single) {
		String sql = "";
		if (operation.equals("delete"))
			sql = "delete from tb_photo where id='" + single.getId()+"'";
		if (operation.equals("upload"))
			sql = "insert into tb_photo(photo_addr,photo_sdTime,photo_desc) values ('"+ 
			single.getPhotoAddr() + "','"+ single.getPhotoTime() + "','"+ 
			single.getPhotoInfo() + "')";
		boolean mark=connection.executeUpdate(sql);			
		return mark;
	}

	public PhotoBean queryPhoto(int id) {
		PhotoBean photoBean = null;
		String sql = "select * from tb_photo where id='" + id+"'";
		ResultSet rs = connection.executeQuery(sql);
		try {
			if(rs.next()) {
				photoBean = new PhotoBean();
				photoBean.setId(rs.getInt("id"));
				photoBean.setPhotoAddr(rs.getString("photo_addr"));
				photoBean.setPhotoInfo(rs.getString("photo_sdTime"));
				photoBean.setPhotoTime(rs.getString("photo_desc"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return photoBean;
	}

	public List queryPhoto(String type) {
		if(type==null||type.equals(""))
			type="sub";		
		String sql = "";
		if(type.equals("all"))
			sql="select * from tb_photo order by photo_sdTime DESC";
		else
			sql="select * from tb_photo order by photo_sdTime DESC LIMIT 0, 8";
		
		List list = null;
		PhotoBean photoBean = null;
		ResultSet rs = connection.executeQuery(sql);
		if(rs!=null){
			list=new ArrayList();
			try {
				while (rs.next()) {					
					photoBean = new PhotoBean();
					photoBean.setId(rs.getInt(1));
					photoBean.setPhotoAddr(rs.getString(2));
					photoBean.setPhotoTime(rs.getString(3));
					photoBean.setPhotoInfo(rs.getString(4));
					list.add(photoBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return list;
	}

	public int queryMaxId() {
		int maxId = 0;
		String sql = "select max(id) from tb_photo";
		ResultSet rs = connection.executeQuery(sql);
		if(rs!=null){
			try {
				if(rs.next())
					maxId = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				connection.closed();
			}
		}
		return maxId;
	}
}

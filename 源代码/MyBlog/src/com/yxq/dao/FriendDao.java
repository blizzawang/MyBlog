package com.yxq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yxq.toolsbean.DB;
import com.yxq.valuebean.FriendBean;

public class FriendDao {
	private DB connection = null;

	public FriendDao() {//1、创建FriendDao对象 2、创建DB对象 3、加载数据库驱动
		connection = new DB();
	}

	// 增、删、改好友
	public boolean operationFriend(String operation,FriendBean single) {
		String sql="";
		if(operation==null)
			operation="";		
		if(operation.equals("add"))
			sql="insert into tb_friend(friend_name,friend_sex,friend_OICQ,friend_blog) values('"+single.getName()+"','"+single.getSex()+"','"+single.getOicq()+"','"+single.getBlog()+"')";		
		if(operation.equals("modify"))
			sql="update tb_friend set friend_name='"+single.getName()+"',friend_sex='"+single.getSex()+"',friend_OICQ='"+single.getOicq()+"',friend_blog='"+single.getBlog()+"' where id='"+single.getId()+"'";
		if(operation.equals("delete"))
			sql="delete from tb_friend where id='"+single.getId()+"'";
		
		boolean flag=connection.executeUpdate(sql);
		return flag;
	}

	/**
	 * @功能 查询所有好友
	 */
	public List queryFriend(String type) {
		String sql="";
		if(type==null||type.equals("")||!type.equals("all"))
			sql="select  * from tb_friend order by friend_name DESC LIMIT 0, 5";
		else
			sql="select * from tb_friend order by friend_name DESC";
		List list = new ArrayList();
		FriendBean friendBean = null;
		ResultSet rs = connection.executeQuery(sql);
		try {
			while (rs.next()) {
				friendBean = new FriendBean();
				friendBean.setId(rs.getInt("id"));
				friendBean.setName(rs.getString("friend_name"));
				friendBean.setSex(rs.getString(3));
				friendBean.setOicq(rs.getString(4));
				friendBean.setBlog(rs.getString(5));
				list.add(friendBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @功能 查询某个好友的详细信息
	 */
	public FriendBean queryFriendSingle(int id) {
		FriendBean friendBean = null;
		String sql = "select * from tb_friend where id='" + id+"'";
		ResultSet rs = connection.executeQuery(sql);
		try {
			if(rs.next()) {
				friendBean = new FriendBean();
				friendBean.setId(rs.getInt(1));
				friendBean.setName(rs.getString(2));
				friendBean.setSex(rs.getString(3));
				friendBean.setOicq(rs.getString(4));
				friendBean.setBlog(rs.getString(5));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			connection.closed();
		}
		return friendBean;
	}
	
	public void closeConnection(){
		connection.closed();
	}
	
	public static void main(String[] args)
	{
		FriendDao fd=new FriendDao();
		FriendBean f=new FriendBean();
		f.setName("cc");
		f.setId(6);
		f.setBlog("blog");
		f.setOicq("456546");
		f.setSex("女");
		boolean flag=fd.operationFriend("add",f);
		//删除
		//修改 
		System.out.print(flag);
		
	}

}

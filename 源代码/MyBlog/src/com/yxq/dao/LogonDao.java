package com.yxq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.yxq.toolsbean.DB;
import com.yxq.valuebean.MasterBean;


public class LogonDao {
	private DB connection = null;

	public LogonDao() {
		connection = new DB();
	}
	
	public MasterBean getMaster(){
		MasterBean master=null;
		String sql="select * from tb_master";
		ResultSet rs=connection.executeQuery(sql);
		try{
			if(rs!=null&&rs.next()){
				master=new MasterBean();	
				master.setMasterName(rs.getString(1));
				master.setMasterSex(rs.getString(3));
				master.setMasterOicq(rs.getString(4));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return master;
	}
	
	public boolean logon(MasterBean logoner){
		boolean mark=false;
		if(logoner!=null){
			String sql="select * from tb_master where master_name='"+logoner.getMasterName()+"' and master_password='"+logoner.getMasterPass()+"'";
			ResultSet rs=connection.executeQuery(sql);
			try {
				if(rs!=null&&rs.next())
					mark=true;
				else
					mark=false;
			} catch (SQLException e) {
				mark=false;
				e.printStackTrace();
			}
			try {
				rs.close();
				connection.closed();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mark;		
	}
}

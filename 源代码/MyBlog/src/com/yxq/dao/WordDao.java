package com.yxq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yxq.toolsbean.DB;
import com.yxq.valuebean.PhotoBean;
import com.yxq.valuebean.WordBean;

public class WordDao {
	private DB connection = null;

	public WordDao() {
		connection = new DB();
	}
	public boolean operationWord(String oper, WordBean single) {		
		/* …˙≥…SQL”Ôæ‰ */
		String sql = null;
		if (oper.equals("add"))					//∑¢±Ì¡Ù—‘
			sql = "insert into tb_word(word_title,word_content,word_sdTime,word_author) values('"+single.getWordTitle()+"','"+single.getWordContent()+"','"+single.getWordTime()+"','"+single.getWordAuthor()+"')";		
		if (oper.equals("delete"))				//…æ≥˝¡Ù—‘
			sql = "delete from tb_word where id='" + single.getId()+"'";

		
		/* ÷¥––SQL”Ôæ‰ */	
		boolean flag =connection.executeUpdate(sql);
		return flag;
	}
	public List queryWord(String type){
		String sql="";
		if(type==null||type.equals(""))
			type="sub";
		if(type.equals("all"))
			sql="select * from tb_word order by word_sdTime DESC";
		else
			sql="select * from tb_word order by word_sdTime DESC";
		List wordlist = null;
		WordBean wordBean = null;
		ResultSet rs = connection.executeQuery(sql);
		if(rs!=null){
			wordlist=new ArrayList();
			try {
				while (rs.next()) {
					wordBean = new WordBean();
					wordBean.setId(rs.getInt(1));
					wordBean.setWordTitle(rs.getString(2));
					wordBean.setWordContent(rs.getString(3));
					wordBean.setWordTime(rs.getString(4));
					wordBean.setWordAuthor(rs.getString(5));
					wordlist.add(wordBean);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return wordlist;
	}
}

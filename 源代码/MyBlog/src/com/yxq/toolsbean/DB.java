package com.yxq.toolsbean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    private final String url = "jdbc:mysql://localhost:3306/db_blog?useUnicode=true&characterEncoding=utf-8";
    private final String userName = "root";
    private final String password = "123456";
    private Connection con = null;
    private Statement stm=null;
    
    /* 通过构造方法加载数据库驱动 */
    public DB(){
    	try {   		
    		//1加载
    		Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("加载数据库驱动成功！");
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("加载数据库驱动失败！");
    	}    	
    }
    /* 创建数据库连接 */
    public void createCon() {
        try {
        	//2、建立连接
            con = DriverManager.getConnection(url, userName, password);
            System.out.println("获取数据库连接成功！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取数据库连接失败！");
        }
    }
    /* 获取Statement对象 */
    public void getStm(){
   		createCon();
    	try {
    		//3、创建Statement对象
			stm=con.createStatement();
			System.out.println("创建Statement对象成功！");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("创建Statement对象失败！");
		}
    }
    /** 
     * @功能 对数据库的增加、修改和删除的操作
     * @参数 sql为要执行的SQL语句
     * @返回值 boolean型值 
     */
    public boolean executeUpdate(String sql) {
    	//System.out.println(sql);
        boolean mark=false;
        getStm();
    	try {   	
    		//4执行操作
            int iCount = stm.executeUpdate(sql);
            if(iCount>0)            	
            	mark=true; 
            else
            	mark=false;
        } catch (Exception e) {
            e.printStackTrace();
		    mark=false;
        }
        return mark;
    }
    /* 查询数据库 */
    public ResultSet executeQuery(String sql) {
        ResultSet rs=null;      
        getStm();
        try {
                rs = stm.executeQuery(sql);
            } 
        catch (Exception e) {
            	e.printStackTrace();
                System.out.println("查询数据库失败！");
            }       
        return rs;
    }
    /* 关闭数据库的操作 */
    public void closed() {
    	if(stm!=null)
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭stm对象失败！");
			}
    	if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭con对象失败！");
			}
    }
    public static void main(String[] args){
		DB d=new DB();
		boolean m=d.executeUpdate("INSERT INTO tb_articleType VALUES(5,'散文','各类散文')");
	    System.out.print(m);
	    boolean n=d.executeUpdate("update tb_articleType set articleType_info='叙事散文' where articleType_id=5");
	    System.out.print(n);
	    //删除
	    //查询
	    //d.closed();
	    
    }
}

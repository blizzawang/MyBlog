package com.yxq.servlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.yxq.dao.PhotoDao;
import com.yxq.toolsbean.MyTools;
import com.yxq.valuebean.PhotoBean;
public class PhotoServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action==null)
			action="";		
		if (action.equals("upload"))
			this.addPhoto(request, response);				//上传图片
		if (action.equals("delete"))
			this.deletePhoto(request, response); 			//删除图片
		if(action.equals("list"))
			this.selectPhoto(request,response);				//查询所有图片
		if(action.equals("single"))
			this.singlePhoto(request,response);				//查看图片详细内容
		if(action.equals("adminList"))
			this.adminSelectPhoto(request,response);		//查询所有图片	
	}
	/**
	 * @功能 前台-查询所有图片
	 */
	public void selectPhoto(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		PhotoDao photoDao=new PhotoDao();
		List photoList=photoDao.queryPhoto("all");
		request.setAttribute("photoList", photoList);	
		RequestDispatcher rd=request.getRequestDispatcher("/front/photo/PhotoList.jsp");
		rd.forward(request,response);
	}
	/**
	 * @功能 后台-查询所有图片
	 */
	public void adminSelectPhoto(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		PhotoDao photoDao=new PhotoDao();
		List photoList=photoDao.queryPhoto("all");
		request.setAttribute("photoList", photoList);		
		RequestDispatcher rd=request.getRequestDispatcher("/admin/photo/PhotoList.jsp");
		rd.forward(request,response);
	}
	/**
	 * @功能 查看某个图片详细内容
	 */
	public void singlePhoto(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		PhotoDao photoDao=new PhotoDao();		
		String strId=request.getParameter("id");
		int id=MyTools.strToint(strId);
		PhotoBean photoSingle=photoDao.queryPhoto(id);
		request.setAttribute("photoSingle",photoSingle);
		
		RequestDispatcher rd=request.getRequestDispatcher("/front/photo/PhotoSingle.jsp");
		rd.forward(request,response);
	}

	/**
	 * @功能 删除图片
	 */
	public void deletePhoto(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String messages="";
		String forward="";
		String href="";		
		RequestDispatcher rd=null;
		PhotoDao photoDao= new PhotoDao();
		int id = MyTools.strToint(request.getParameter("id"));
		String fileAddr=photoDao.queryPhoto(id).getPhotoAddr();
		//获取当前项目在服务器上的绝对路径。参数"\\"会在路径最后加上"\"，第1个"\"代表转义字符
		String photoDir=request.getSession().getServletContext().getRealPath("\\");
		//delFile=F:\Workspaces\MyEclipse 8.5\.metadata\.me_tcat\webapps\MyBlog\front\photo\pic\0.jpg
		String delFile=photoDir+fileAddr;
		//创建File对象，与之关联的文件是delFile表示的文件
		java.io.File file = new java.io.File(delFile);
			
		PhotoBean photoBean = new PhotoBean();
		photoBean.setId(id);
		if (photoDao.operationPhoto("delete", photoBean)) {
			boolean result=file.delete();//删除与file关联的文件
			if(result){
				messages="<li>删除照片成功！</li>";
				forward="/admin/success.jsp";
				href="<a href='PhotoServlet?action=adminList'>[继续删除其他照片]</a>";	
			}
			else{
				messages="<li>删除照片失败！</li>";
				forward="/admin/error.jsp";
				href="<a href='javascript:window.history.go(-1)'>[返回]</a>";
			}				
			
		} else {
			messages="<li>删除照片失败！</li>";
			forward="/admin/error.jsp";
			href="<a href='javascript:window.history.go(-1)'>[返回]</a>";			
		}
		request.setAttribute("messages",messages);
		request.setAttribute("href",href);
		rd=request.getRequestDispatcher(forward);
		rd.forward(request,response);
		
	}
	/**
	 * @功能 上传图片
	 */
	public void addPhoto(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	//	String filePath = "front\\\\photo\\\\pic\\\\";
		String filePath = "front/photo/pic/";
		String messages="";
		String forward="";
		String href="";	
		PhotoDao photoDao = new PhotoDao();
		PhotoBean photoBean = new PhotoBean();
		request.setCharacterEncoding("utf-8");
		SmartUpload su = new SmartUpload();	
		long maxsize = 2 * 1024 * 1024; 									// 设置每个上传文件的大小，为2MB		
		try{
			//初始化上传
			su.initialize(this.getServletConfig(), request, response);
			su.setMaxFileSize(maxsize); //设置上传文件的最大长度									// 限制上传文件的大小
			su.setAllowedFilesList("jpg,gif,bmp");							// 设置允许上传的文件类型
			su.upload();//照片送到程序里来了													// 上传文件
			String photoInfo=su.getRequest().getParameter("info");
			//String photoInfo=su.getRequest().getParameter("info");
			
			if(photoInfo==null||photoInfo.equals("")){						//验证照片描述信息，若没有输入，则提示输入照片描述信息
				messages="请输入照片描述信息！";
				forward="/admin/error.jsp";
				href="<a href='javascript:window.history.go(-1)'>[返回]</a>";
			}
			else{
				File file = su.getFiles().getFile(0);						// 获取上传的文件，因为只上传了一个文件，所以可直接获取			
				if (!file.isMissing()) {// 如果选择了文件 
					//photoAddr是照片完整的名字：路径+名字+扩展名
					String photoAddr=filePath+photoDao.queryMaxId()+"."+file.getFileExt();		//filePath值(front\\photo\\pic\\)+图片信息在数据表中的id字段值+“.”+文件后缀名;最后生成例如“front\photo\pic\12.bmp”路径
					String now=MyTools.changeTime(new Date());				//获取当前时间并格式化为字符串					
					photoBean.setPhotoAddr(photoAddr);
					photoBean.setPhotoTime(now);
					photoBean.setPhotoInfo(photoInfo);		
					boolean mark=photoDao.operationPhoto("upload",photoBean);
					
					if(mark){					
						try {
							file.saveAs(photoAddr,File.SAVEAS_VIRTUAL);
							messages="上传文件成功！";
							forward="/admin/success.jsp";
							href="<a href='admin/photo/PhotoUpload.jsp'>[继续上传]</a>";
						} catch (SmartUploadException ee) {
							messages="上传文件失败！";
							forward="/admin/error.jsp";
							href="<a href='javascript:window.history.go(-1)'>[返回]</a>";
							ee.printStackTrace();
						}
					}
					else{
						messages="保存文件信息失败！";
						forward="/admin/error.jsp";
						href="<a href='javascript:window.history.go(-1)'>[返回]</a>";
					}
				}
				else{
					messages="请选择要上传的文件！";
					forward="/admin/error.jsp";
					href="<a href='javascript:window.history.go(-1)'>[返回]</a>";
				}				
			}
			
		}catch (java.lang.SecurityException e){
			messages="<li>上传文件失败！上传的文件类型只允许为：jpg,gif,bmp</li>";
			forward="/admin/error.jsp";
			href="<a href='javascript:window.history.go(-1)'>[返回]</a>";
			
		}catch (SmartUploadException e) {
			messages="上传文件失败！";
			forward="/admin/error.jsp";
			href="<a href='javascript:window.history.go(-1)'>[返回]</a>";
			e.printStackTrace();
		}
				
		request.setAttribute("messages",messages);
		request.setAttribute("href",href);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward);
		requestDispatcher.forward(request, response);
	}
}

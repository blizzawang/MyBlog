<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
    <head>
    	<title>博客后台-侧栏</title>
    </head>
<body>
    <table width="193" height="401" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
        <tr>
        	<td width="191" height="401" valign="top">
        		<table width="100%" border="0" cellpadding="0" cellspacing="0">
        			<tr><td height="32" colspan="4" align="center" background="<%=path%>/images/adminLmenu_1.jpg" class="word_menuHead">文章管理</td></tr>
        			<tr valign="bottom"><td height="27" colspan="4" bgcolor="#E59FD5" style="text-indent:30" class="tableBorder_B"><a href="<%=path%>/admin/article/ArticleAdd.jsp">★发表文章</a></td>
        			</tr>
        			<tr valign="bottom"><td height="27" colspan="4" bgcolor="#E59FD5" style="text-indent:30"><a href="<%=path%>/admin/article/ArticleList.jsp">★浏览/修改/删除文章</td>
        			</tr>
        			<tr><td height="30" colspan="4" align="center" background="<%=path%>/images/adminLmenu_2.jpg" class="word_menuHead">文章类别管理</td>
        			</tr>
        			<tr valign="bottom"><td height="27" colspan="4" bgcolor="#E59FD5" style="text-indent:30" class="tableBorder_B"><a href="<%=path%>/admin/article/ArticleTypeAdd.jsp">★添加类别</a></td>
        			</tr>
        			<tr valign="bottom"><td height="27" colspan="4" bgcolor="#E59FD5" style="text-indent:30"><a href="<%=path%>/ArticleServlet?action=typeSelect">★浏览/修改/删除类别</a></td>
        			</tr>
        			<tr><td height="30" colspan="4" align="center" background="<%=path%>/images/adminLmenu_2.jpg" class="word_menuHead">相册管理</td>
        			</tr>
        			<tr valign="bottom"><td height="27" colspan="4" bgcolor="#E59FD5" style="text-indent:30" class="tableBorder_B"><a href="<%=path%>/admin/photo/PhotoUpload.jsp">★上传照片</a></td>
        			</tr>
        			<tr valign="bottom"><td height="27" colspan="4" bgcolor="#E59FD5" style="text-indent:30"><a href="<%=path%>/PhotoServlet?action=adminList">★浏览/删除照片</a></td>
        			</tr>
        			<tr><td height="30" colspan="4" align="center" background="<%=path%>/images/adminLmenu_2.jpg" class="word_menuHead">好友管理</td>
        			</tr>
        			<tr valign="bottom"><td height="27" colspan="4" bgcolor="#E59FD5" style="text-indent:30"class="tableBorder_B"><a href="<%=path%>/admin/friend/FriendAdd.jsp">★添加好友</a></td>
        			</tr>
        			<tr valign="bottom"><td height="27" colspan="4" bgcolor="#E59FD5" style="text-indent:30"><a href="<%=path%>/FriendServlet?action=adminList">★浏览/修改/删除好友</a></td>
        			</tr>
        			<tr><td height="30" colspan="4" align="center" background="<%=path%>/images/adminLmenu_2.jpg" class="word_menuHead">留言管理</td>
        			</tr>
        			<tr valign="bottom"><td height="27" colspan="4" bgcolor="#E59FD5" style="text-indent:30"><a href="<%=path%>/WordServlet?action=adminselect">★浏览/删除留言</a></td></tr>
        			</table>
   		    </td>
       	    <td valign="top" bgcolor="#FFFFFF" class="tableBorder_R">&nbsp;</td>
        </tr>
</table> 
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yxq.valuebean.PhotoBean" %>

<html>
<head>
	<title>博客后台-浏览所有照片</title>
</head>
<body>
    <center>
        <table width="778" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style="word-break:break-all">
            <tr><td colspan="2"><%@ include file="/admin/view/AdminTop.jsp" %></td></tr>
            <tr>
                <td width="180"><jsp:include page="/admin/view/AdminLeft.jsp"/></td>
                <td width="598" align="center" valign="top">
                	<table border="0" width="95%" cellspacing="0" style="margin-top:10" >
						<tr height="60"><td colspan="4">【浏览相册】</td></tr>
						<%
							ArrayList photoList=(ArrayList)request.getAttribute("photoList");
                        	if(photoList==null||photoList.size()==0){
                        %>
                        <tr height="100"><td align="center" colspan="4"><li>博主目前还未上传任何照片！</li></td></tr>
                        <%  
                        	}
                        	else{
                        %>                        
                        <tr align="center" height="50">
                        	<td align="left" ><b>照片描述</b></td>
                       		<td><b>上传时间</b></td>
                        	<td colspan="2"><b>操作</b></td>
                        <tr>
                        <% 		
                        		for(int i=0;i<photoList.size();i++){	
                        			PhotoBean single=(PhotoBean)photoList.get(i);
                        %>
                        <tr height="30">
                           	<td><%=single.getPhotoInfo()%></td>
                           	<td align="center" width="30%"><%=single.getPhotoTime()%></td>
                           	<td align="center" width="15%"><a href="<%=path%>/PhotoServlet?action=single&id=<%=single.getId()%>" target="_blank">查看照片</a></td>
                           	<td align="center" width="10%"><a href="<%=path%>/PhotoServlet?action=delete&id=<%=single.getId()%>">×删除</a></td>
                        </tr>
                        <%                        			
                        		}                  				
                        	}
                        %>
                    </table>
                </td>
            </tr>
            <tr><td colspan="2"><%@ include file="/admin/view/AdminEnd.jsp" %></td></tr>
        </table>
    </center>
</body>
</html>
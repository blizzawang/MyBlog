<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.yxq.valuebean.PhotoBean" %>
<html>
<head>
	<title>聆音博客-所有相片</title>
</head>
<body>
    <center>
        <table width="778" height="600" border="0" cellspacing="0" cellpadding="0" bgcolor="#F0EAED">
            <tr height="281"><td colspan="2"><%@ include file="/front/view/FrontTop.jsp"%></td></tr>
            <tr>
                <td width="230" valign="top"><jsp:include page="/front/view/FrontLeft.jsp"/></td>
                <td width="548" align="center" valign="top">
                    <table border="0" width="95%" cellspacing="0" style="margin-top:10" >
						<tr height="60"><td align="center">【我的相册】</td></tr>
						<%
                           	List photoList=(List)request.getAttribute("photoList");
                        	if(photoList==null||photoList.size()==0){
                        %>
                        <tr height="100"><td align="center"><li>博主目前还未上传任何照片！</li></td></tr>
                        <%  
                        	}
                        	else{
                  				out.print("<tr height='120'>");
                  				out.print("<td width='25%' style='padding-left:10'>");
                         		int i=0;
                        		while(i<photoList.size()){
                        			PhotoBean photoSingle=(PhotoBean)photoList.get(i);
                        			out.print("<a href='PhotoServlet?action=single&id="+photoSingle.getId()+"' target='_blank'><img src='"+photoSingle.getPhotoAddr()+"' border='0' width='120' height='120' title='"+photoSingle.getPhotoInfo()+"' style='border:1 solid;border-color:black'></a> ");
                        			i++;
                        		}
                        		out.println("</td>");
                        		out.println("</tr>");
                        	}
                        %>
                    </table>
                </td>
            </tr>
            <tr height="100"><td colspan="2"><%@ include file="/front/view/FrontEnd.jsp" %></td></tr>
        </table>
    </center>
</body>
</html>
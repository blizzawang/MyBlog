<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.yxq.valuebean.ArticleBean" %>
<html>
<head>
	<title>聆音博客-发表评论成功</title>
</head>
<body>
    <center>
        <table width="778" height="600" border="0" cellspacing="0" cellpadding="0" bgcolor="#F0EAED">
            <tr height="281"><td colspan="2"><%@ include file="/front/view/FrontTop.jsp" %></td></tr>
            <tr>
                <td width="230" valign="top"><jsp:include page="/front/view/FrontLeft.jsp"/></td>
                <td width="548" align="center" valign="top">
                	<table border="10" bordercolor="lightgrey" rules="none" width="80%" height="200" cellspacing="0" cellpadding="0" style="margin-top:30" >
                  		<tr height="50"><td align="center">友情提示</td></tr>
                  		<tr><td align="center"><%=request.getAttribute("messages") %></td></tr>
                  		<tr><td align="center"><a href="<%=path%>/ArticleServlet?action=read&id=<%=((ArticleBean)session.getAttribute("readSingle")).getId() %>">查看评论</a></td></tr>
            		</table>            		
                </td>
            </tr>
            <tr height="100"><td colspan="2"><%@ include file="/front/view/FrontEnd.jsp" %></td></tr>
        </table>
    </center>
</body>
</html>
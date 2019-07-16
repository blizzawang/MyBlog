<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.yxq.valuebean.FriendBean" %>
<html>
<head>
	<title>博客-查看好友</title>
</head>
<body>
    <center>
        <table width="778" height="600" border="0" cellspacing="0" cellpadding="0" bgcolor="#F0EAED">
            <tr height="281"><td colspan="2"><%@include file="/front/view/FrontTop.jsp"%></td></tr>
            <tr>
                <td width="230" valign="top"><jsp:include page="/front/view/FrontLeft.jsp"/></td>
                <td width="548" align="center" valign="top">
                	<%
                		FriendBean single=(FriendBean)request.getAttribute("friendSingle");
                		if(single==null)
                			out.print("查看好友信息失败！");
                		else{
                	%>
                	<table border="10" bordercolor="lightgrey" rules="none" width="95%" height="300" cellspacing="0" cellpadding="0" style="margin-top:30" >
                		<tr>
                			<td width="30%" align="right">好友昵称：</td>
                			<td style="text-indent:30"><%=single.getName()%></td>
                		</tr>
                		<tr>
                			<td align="right">性别：</td>
                			<td style="text-indent:30"><%=single.getSex()%></td>
                		</tr>
                		<tr>
                			<td align="right">QQ号码：</td>
                			<td style="text-indent:30"><%=single.getOicq()%></td>
                		</tr>
                		<tr>
                			<td align="right">好友博客：</td>
                			<td style="text-indent:30"><a href="#"><%=single.getBlog()%></a></td>
                		</tr>
                	</table>
                	<%	} %>
                	<br>
                	<a href="javascript:window.history.go(-1)">返回</a>
                </td>
            </tr>
            <tr height="100"><td colspan="2"><%@ include file="/front/view/FrontEnd.jsp" %></td></tr>
        </table>
    </center>
</body>
</html>
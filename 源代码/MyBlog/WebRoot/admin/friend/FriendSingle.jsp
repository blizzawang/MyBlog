<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yxq.valuebean.FriendBean" %>
<html>
<head>
	<title>博客后台-浏览所有好友</title>
</head>
<body>
    <center>
        <table width="778"  border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style="word-break:break-all">
            <tr><td colspan="2"><%@ include file="/admin/view/AdminTop.jsp" %></td></tr>
            <tr>
                <td ><jsp:include page="/admin/view/AdminLeft.jsp"/></td>
                <td align="center" valign="top">
					<%
                		FriendBean single=(FriendBean)request.getAttribute("friendSingle");
                		if(single==null)
                			out.print("查看好友信息失败！");
                		else{
                	%>
                	<table border="0" rules="none" width="530" height="197" cellspacing="0" cellpadding="0" style="margin-top:30" >
                		<tr>
                			<td width="30%" align="right">好友昵称：</td>
                			<td style="text-indent:30"><%=single.getName()%></td>
                		</tr>
                		<tr>
                			<td align="right">性&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
                			<td style="text-indent:30"><%=single.getSex()%></td>
                		</tr>
                		<tr>
                			<td align="right">QQ&nbsp;号&nbsp;码：</td>
                			<td style="text-indent:30"><%=single.getOicq()%></td>
                		</tr>
                		<tr>
                			<td align="right">好友博客：</td>
                			<td style="text-indent:30"><a href="#" class="word_purple"><%=single.getBlog()%></a></td>
                		</tr>
                	</table>
                	<%	} %>
                	<br>
                	<input type="button" value="返&nbsp;回" class="btn_bg" onClick="javascript:window.history.go(-1)">
                </td>
            </tr>
            <tr><td colspan="2"><%@ include file="/admin/view/AdminEnd.jsp" %></td></tr>
        </table>
    </center>
</body>
</html>
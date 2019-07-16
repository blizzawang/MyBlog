<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yxq.valuebean.FriendBean" %>
<html>
<head>
	<title>博客后台-浏览所有好友</title>
</head>
<body>
    <center>
        <table width="778" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style="word-break:break-all">
            <tr><td colspan="2"><%@ include file="/admin/view/AdminTop.jsp" %></td></tr>
            <tr>
                <td width="180"><jsp:include page="/admin/view/AdminLeft.jsp"/></td>
                <td align="center" valign="top">
                	<table border="0" width="540" cellspacing="0" cellpadding="0" rules="none">
						<tr height="60"><td colspan="5">【浏览所有好友】</td></tr>
						<tr height="30" bgcolor="#F5F4F4">
							<td width="30%" style="text-indent:20">好友名称</td>
							<td align="center" width="25%">性别</td>
							<td align="center" width="25%">OICQ</td>
							<td align="center"width="20%" colspan="2">操作</td>
						</tr>
						<%
							ArrayList friendList=(ArrayList)request.getAttribute("friendList");
							if(friendList==null||friendList.size()==0){
						%>
						   <tr height="80"><td colspan="5" align="center"><li>没有好友可显示！</li></td></tr>
						<%	
							} 
							else{
    							for(int i=0;i<friendList.size();i++){
    								FriendBean single=(FriendBean)friendList.get(i);    							
						%>
						   <tr height="35">
							<td style="text-indent:20"><a href="<%=path%>/FriendServlet?action=adminSingle&id=<%=single.getId() %>" class="word_purple "><%=single.getName()%></a></td>
							<td align="center"><%=single.getSex() %></td>
							<td align="center"><%=single.getOicq() %></td>
							<td align="center"><a href="<%=path%>/FriendServlet?action=modify&id=<%=single.getId() %>" class="word_purple ">√修改</a></td>
							<td align="center"><a href="<%=path%>/FriendServlet?action=delete&id=<%=single.getId() %>" class="word_purple ">×删除</a></td>							
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
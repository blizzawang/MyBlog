<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yxq.valuebean.ArticleTypeBean" %>
<html>
<head>
	<title>博客后台-浏览文章类别</title>
</head>
<body>
    <center>
        <table width="778" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style="word-break:break-all">
            <tr><td colspan="2"><%@ include file="/admin/view/AdminTop.jsp" %></td></tr>
            <tr>
                <td><jsp:include page="/admin/view/AdminLeft.jsp"/></td>
                <td align="center" valign="top">
                	<table width="560" border="0" cellspacing="0" cellpadding="0" rules="none">
						<tr height="60"><td colspan="4">【浏览文章类别】</td></tr>
						<tr height="30" bgcolor="#F5F4F4">
							<td width="30%" style="text-indent:20">类别名称</td>
							<td align="center" width="55%">类别描述</td>
							<td align="center"width="15%" colspan="2">操作</td>
						</tr>
						<%
							ArrayList typelist=(ArrayList)request.getAttribute("typelist");
							if(typelist==null||typelist.size()==0){
						%>
						<tr height="80"><td colspan="4" align="center"><li>没有类别可显示！</li></td></tr>
						<%	
							} 
							else{
    							for(int i=0;i<typelist.size();i++){
    								ArticleTypeBean single=(ArticleTypeBean)typelist.get(i);    							
						%>
						<tr height="35">
							<td style="text-indent:20"><%=single.getTypeName()%></td>
							<td align="center"><%=single.getTypeInfo() %></td>
							<td align="center"><a href="<%=path%>/ArticleServlet?action=typeModify&typeId=<%=single.getId() %>" class="word_purple ">√修改</a></td>
							<td align="center"><a href="<%=path%>/ArticleServlet?action=typeDelete&typeId=<%=single.getId() %>" class="word_purple ">×删除</a></td>							
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
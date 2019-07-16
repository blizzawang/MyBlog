<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yxq.valuebean.ArticleTypeBean" %>

<% ArrayList typelist=(ArrayList)session.getAttribute("artTypeList"); %>
<html>
<head>
	<title>博客后台-发表文章</title>
</head>
<body>
    <center>
        <table width="778" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style="word-break:break-all">
            <tr><td colspan="2"><%@ include file="/admin/view/AdminTop.jsp" %></td></tr>
            <tr>
                <td valign="top"><jsp:include page="/admin/view/AdminLeft.jsp"/></td>
                <td align="center" valign="top">
                	<form action="<%=path%>/ArticleServlet" method="post">
                	<input type="hidden" name="action" value="add">
                	<table border="0" width="100%" cellspacing="0" cellpadding="8">
                		<tr height="50"><td colspan="2">【发表文章】</td></tr>
                		<tr>
                			<td align="center" width="20%">文章类别：</td>
                			<td>
                				<select name="typeId" style="width:100">
                					<option value=""/>
                					<% 
                						if(typelist!=null&&typelist.size()!=0){
                							for(int i=0;i<typelist.size();i++){
                								ArticleTypeBean typeBean=(ArticleTypeBean)typelist.get(i);
                					%>
                					<option value="<%=typeBean.getId()%>"><%=typeBean.getTypeName() %></option>
                					<%
                							}
                						}
                					%>
                				</select>
                			</td>
                		</tr>
                		<tr>
                			<td align="center">文章标题：</td>
                			<td><input type="text" name="title" size="77"></td>
                		</tr>
                		<tr>
                			<td align="center">文章来源：</td>
                			<td>
                				<select name="create" style="width:100">
                					<option value=""/>
                					<option value="原创">原创</option>
                					<option value="摘自">摘自</option>
                				</select>
                			</td>
                		</tr>
	               		<tr>
                			<td align="center">文章描述：</td>
                			<td><input type="text" name="info" size="77"></td>
                		</tr>
                		<tr>
                			<td align="center" valign="top">文章内容：</td>
                			<td>
                				<textarea name="content" rows="8" cols="65"></textarea>
                			</td>
                		</tr>
                		<tr height="30">
                			<td colspan="2" align="center">
                				<input type="submit" class="btn_bg" value="保存">
                				<input type="reset"  class="btn_bg" value="重置">
                			</td>
                		</tr>
                	</table>
                	</form>
                </td>
            </tr>
            <tr><td colspan="2"><%@ include file="/admin/view/AdminEnd.jsp" %></td></tr>
        </table>
    </center>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<jsp:useBean id="typeSingle" class="com.yxq.valuebean.ArticleTypeBean" scope="request"/>
<html>
<head>
	<title>博客后台-修改类别</title>
</head>
<body>
    <center>
        <table width="778" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style="word-break:break-all">
            <tr><td colspan="2"><%@ include file="/admin/view/AdminTop.jsp" %></td></tr>
            <tr>
                <td valign="top"><jsp:include page="/admin/view/AdminLeft.jsp"/></td>
                <td align="center" valign="top">
                	<form action="<%=path%>/ArticleServlet" method="post">
                	<input type="hidden" name="action" value="typeModify">
                	<input type="hidden" name="type" value="doModify">
                	<input type="hidden" name="typeId" value="<%=typeSingle.getId() %>">
                	<table border="0" width="99%" cellspacing="0" cellpadding="8">
                		<tr height="60"><td colspan="2">【修改类别】</td></tr>
                		<tr>
                			<td align="center" width="20%">文章ID：</td>
                			<td><%=typeSingle.getId() %></td>
                		</tr>
                		<tr>
                			<td align="center">类别名称：</td>
                			<td><input type="text" name="typeName" value="<%=typeSingle.getTypeName() %>" size="50"></td>
                		</tr>
                   		<tr>
                			<td align="center">类别描述：</td>
                			<td><input type="text" name="typeInfo" value="<%=typeSingle.getTypeInfo()%>" size="50"></td>
                		</tr>
                		<tr height="50">
                			<td colspan="2" align="center">
                				<input type="submit" value="修改" class="btn_bg">
                				<input type="reset" value="重置" class="btn_bg">
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
<%@ page language="java" contentType="text/html; charset=utf-8"%>

<html>
<head>
	<title>博客后台-添加文章类别</title>
</head>
<body>
    <center>
        <table width="778" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style="word-break:break-all">
            <tr><td colspan="2"><%@ include file="/admin/view/AdminTop.jsp" %></td></tr>
            <tr>
                <td><jsp:include page="/admin/view/AdminLeft.jsp"/></td>
                <td align="center" valign="top">
                    <form action="<%=path%>/ArticleServlet" method="post">
                	<input type="hidden" name="action" value="typeAdd">
                	<table border="0" width="80%" cellspacing="0" cellpadding="8">
                		<tr height="60"><td colspan="2">【添加文章类别】</td></tr>
                		<tr>
                			<td align="center">类别名称：</td>
                			<td><input type="text" name="typeName" size="50"></td>
                		</tr>
	               		<tr>
                			<td align="center">类别描述：</td>
                			<td><input type="text" name="typeInfo" size="50"></td>
                		</tr>
                		<tr height="50">
                			<td colspan="2" align="center">
                				<input type="submit" class="btn_bg" value="保存">
                				<input type="reset" class="btn_bg" value="重置">
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
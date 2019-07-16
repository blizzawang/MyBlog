<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
	<title>博客后台-添加好友</title>
</head>
<body>
    <center>
        <table width="778" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style="word-break:break-all">
            <tr><td colspan="2"><%@ include file="/admin/view/AdminTop.jsp" %></td></tr>
            <tr>
                <td width="180" valign="top"><jsp:include page="/admin/view/AdminLeft.jsp"/></td>
                <td align="center" valign="top">
                	<form action="<%=path%>/FriendServlet?action=add" method="post">
                	<!-- <input type="hidden" name="action" value="add"> -->
                	<table border="0" width="90%" height="300" cellspacing="0" cellpadding="8">
                		<tr height="60"><td colspan="2">【添加好友】</td></tr>
                		<tr>
                			<td align="right" width="25%">好友姓名：</td>
                			<td><input type="text" name="name" size="40"></td>
                		</tr>
                		<tr>
                			<td align="right">性&nbsp;&nbsp;别：</td>
                			<td>
								<input type="radio" name="sex" value="男" class="noborder" checked="checked">男
								<input type="radio" name="sex" value="女" class="noborder">女
                			</td>
                		</tr>
	               		<tr>
                			<td align="right">QQ号码：</td>
                			<td><input type="text" name="oicq" size="40"></td>
                		</tr>
                		<tr>
                			<td align="right">BLOG：</td>
                			<td><input type="text" name="blog" size="60"></td>
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
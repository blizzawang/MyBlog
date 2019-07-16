<%@ page language="java" contentType="text/html; charset=utf-8"%>
<jsp:useBean id="modifySingle" class="com.yxq.valuebean.FriendBean" scope="request"/>
<html>
<head>
	<title>博客后台-修改好友</title>
</head>
<body>
    <center>
        <table width="778" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style="word-break:break-all">
            <tr><td colspan="2"><%@ include file="/admin/view/AdminTop.jsp" %></td></tr>
            <tr>
                <td width="180" valign="top"><jsp:include page="/admin/view/AdminLeft.jsp"/></td>
                <td align="center" valign="top">
                	<form action="<%=path%>/FriendServlet" method="post">
                	<input type="hidden" name="action" value="modify">
                	<input type="hidden" name="type" value="doModify">                	
                	<input type="hidden" name="id" value="<%=modifySingle.getId()%>">
                	<table border="0" width="90%" height="300" cellspacing="0" cellpadding="8">
                		<tr height="60"><td colspan="2">【修改好友】</td></tr>
                		<tr>
                			<td align="right" width="25%">好友姓名：</td>
                			<td><input type="text" name="name" value="<%=modifySingle.getName() %>" size="40"></td>
                		</tr>
                		<tr>
                			<td align="right">性&nbsp;&nbsp;别：</td>
                			<td>
                				<%	if(modifySingle.getSex().equals("女")){ %>
								<input type="radio" name="sex" value="男" class="noborder">男
								<input type="radio" name="sex" value="女" class="noborder" checked>女
								<%	
									}
                					else{
                				%>
                				<input type="radio" name="sex" value="男" class="noborder" checked>男
								<input type="radio" name="sex" value="女" class="noborder">女
                				<%	} %>
                			</td>
                		</tr>
	               		<tr>
                			<td align="right">QQ号码：</td>
                			<td><input type="text" name="oicq" value="<%=modifySingle.getOicq()%>" size="40"></td>
                		</tr>
                		<tr>
                			<td align="right">BLOG：</td>
                			<td><input type="text" name="blog" value="<%=modifySingle.getBlog() %>" size="60"></td>
                		</tr>
                		<tr height="50">
                			<td colspan="2" align="center">
                				<input type="submit" value="保存" class="btn_bg">
                				<input type="reset" value="重置" class="btn_bg">
                				<input type="button" value="返回" onClick="javascript:window.history.go(-1)" class="btn_bg">
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
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
	<title>博客后台-上传照片</title>
</head>
<body>
    <center>
        <table width="778"  border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style="word-break:break-all">
            <tr><td colspan="2"><%@ include file="/admin/view/AdminTop.jsp" %></td></tr>
            <tr>
                <td ><jsp:include page="/admin/view/AdminLeft.jsp"/></td>
                <td align="center" valign="top">
                    <form action="<%=path%>/PhotoServlet?action=upload" method="POST" enctype="multipart/form-data">
                	<table border="0" width="100%" cellspacing="0" cellpadding="8">
                		<tr height="60"><td colspan="2">【上传照片】</td></tr>
                		<tr>
                			<td align="center">选择照片：</td>
                			<td><input type="file" name="path" size="50"></td>
                		</tr>
	               		<tr>
                			<td align="center">照片描述：</td>
                			<td><input type="text" name="info" size="63"></td>
                		</tr>
                		<tr height="50">
                			<td colspan="2" align="center">
                				<input type="submit" value="上传" class="btn_bg">
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
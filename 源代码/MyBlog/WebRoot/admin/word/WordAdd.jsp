<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
	<title>博客后台首页</title>
</head>
<body>
    <center>
        <table border="0">
            <tr><td colspan="2"><%@ include file="/admin/view/AdminTop.jsp" %></td></tr>
            <tr>
                <td><jsp:include page="/admin/view/AdminLeft.jsp"/></td>
                <td>后台主页内容</td>
            </tr>
            <tr><td colspan="2"><%@ include file="/admin/view/AdminEnd.jsp" %></td></tr>
        </table>
    </center>
</body>
</html>
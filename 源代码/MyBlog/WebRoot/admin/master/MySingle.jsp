<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
	<title>博客后台首页</title>
</head>
<body>
    <center>
        <table border="1">
            <tr><td colspan="2"><%@ include file="../view/AdminTop.jsp" %></td></tr>
            <tr>
                <td><jsp:include page="../view/AdminLeft.jsp"/></td>
                <td>后台主页内容</td>
            </tr>
            <tr><td colspan="2"><%@ include file="../view/AdminEnd.jsp" %></td></tr>
        </table>
    </center>
</body>
</html>
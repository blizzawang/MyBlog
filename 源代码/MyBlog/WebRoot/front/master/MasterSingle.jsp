<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
	<title>博客-查看博主</title>
</head>
<body>
    <center>
        <table border="1">
            <tr><td colspan="2"><%@ include file="../view/FrontTop.jsp" %></td></tr>
            <tr>
                <td><jsp:include page="../view/FrontLeft.jsp"/></td>
                <td>浏览博主信息</td>
            </tr>
            <tr><td colspan="2"><%@ include file="/front/view/FrontEnd.jsp" %></td></tr>
        </table>
    </center>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.yxq.valuebean.PhotoBean" %>
<html>
<head><title>博客-查看相片</title></head>
<body>
<center>我的相册</center><br>
<center>
<%
   PhotoBean single=(PhotoBean)request.getAttribute("photoSingle");
   if(single==null)
	   out.print("查看图片失败！");
   else{
%>
   <img src="<%=single.getPhotoAddr()%>" >
<% } %>
</center>
</body>
</html>
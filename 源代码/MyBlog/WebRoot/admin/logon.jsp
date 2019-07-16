<%@ page contentType="text/html; charset=utf-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String mess=(String)request.getAttribute("messages");
	if(mess==null||mess.equals(""))
		mess="<li>欢迎登录！</li>";
%>
<html>
	<head>
		<title>博主登录</title>
		<base href="<%=basePath%>">
		<link href="css/style.css" rel="stylesheet">
	</head>
	<body style="BACKGROUND-IMAGE: url(images/bg.jpg)">
		<center>
		<table width="777" height="523" border="1" cellpadding="0" cellspacing="0" background="<%=path%>/images/logonBG.jpg">
    	    <tr>
        	    <td align="center">
			 	<table border="0" cellpadding="0" cellspacing="0" style="margin-top:300">
				<form action="<%=path%>/LogonServlet?action=logon" method="post">				
					<tr><td colspan="2" align="center"><%=mess %></td></tr>
					<tr height="30">
						<td>用户名：</td>
						<td><input type="text" name="userName" style="width:200"></td>
					</tr>
					<tr height="30">
						<td>密&nbsp;&nbsp;码：</td>
						<td><input type="password" name="userPass" style="width:200"></td>
					</tr>				
					<tr>
						<td></td>
						<td>
							<input type="submit" class="btn_bg" value="登 录">
			    			<input type="reset" class="btn_bg" value="重 置">	
		    				<a href="index.jsp">返回首页</a>
		    			</td>
					</tr>
				 </form>
				 </table>
		    	 </td>
		    </tr>
    	</table>
    </center>
	</body>
</html>
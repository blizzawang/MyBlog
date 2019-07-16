<%@ page contentType="text/html; charset=utf-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
    <head>
    	<title>博客-页头</title>
		<base href="<%=basePath%>">	
		<link type="text/css" rel="stylesheet" href="<%=path%>/css/style.css">	    	
    </head>
	<body background="<%=path%>/images/bg.jpg">
    	<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
        	<tr>
            	<td background="<%=path%>/images/top.jpg">
                	<table border="0" cellspacing="10" cellpadding="0" style="margin-top:145;margin-left:350">
                    	<tr>
                        	<td class="topTD"><a href="<%=path%>/index.jsp" class="topA">博客首页</a> |</td>
	                        <td class="topTD"><a href="<%=path%>/front/article/ArticleIndex.jsp" class="topA">我的文章</a> |</td>
    	                    <td class="topTD"><a href="<%=path%>/PhotoServlet?action=list" class="topA">我的相册</a> |</td>
        	                <td class="topTD"><a href="<%=path%>/FriendServlet?action=list" class="topA">我的好友</a> |</td>
            	            <td class="topTD"><a href="<%=path%>/WordServlet?action=select" class="topA">给我留言</a> |</td>
            	            <td class="topTD"><a href="<%=path%>/LogonServlet?action=islogon" class="topA">管理博客</a></td>
                	    </tr>
	                </table>
    	        </td>
        	</tr>
	    </table> 
	
	</body>
</html>
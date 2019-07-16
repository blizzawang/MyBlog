<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="com.yxq.valuebean.ArticleBean" %>
<html>
<head>
	<title>博客后台首页</title>
</head>
<body>
    <center>
        <table width="778" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style="word-break:break-all">
            <tr><td colspan="2"><%@ include file="/admin/view/AdminTop.jsp" %></td></tr>
            <tr>
                <td><jsp:include page="/admin/view/AdminLeft.jsp"/></td>
                <td align="center" valign="top">
                    <% 
                    	ArticleBean single=(ArticleBean)request.getAttribute("articleSingle");
                    	if(single==null)
                    		out.println("阅读文章失败！");
                    	else{
                    %>
                	<table border="0" width="585" rules="all" cellpadding="0" cellspacing="0">
                	    <tr height="35" align="right">
                  	        <td background="<%=path%>/images/admin_sonTop.jpg">
		               	         发表时间：<%=single.getSdTime() %>&nbsp;&nbsp;&nbsp;&nbsp;
                  	        	评论：<%=single.getReview() %> 条&nbsp;&nbsp;&nbsp;&nbsp;
                	        	阅读：<%=single.getCount() %> 次&nbsp;&nbsp;&nbsp;&nbsp;
                	    	</td>
                	    </tr>
                	    <tr height="15"><td></td></tr>
                	    <tr><td align="center"><b><font style="font-size:25px"><%=single.getTitle()%></font></b></td></tr>
                	    <tr><td align="right"><%=single.getCreate()%>：<%=single.getInfo() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
                	    <tr height="15"><td></td></tr>
                	    <tr height="200"><td valign="top" style="padding-left:20;padding-right:20"><%=single.getContent() %></td></tr>
                	</table>
					<input type="button" value="返&nbsp;回" class="btn_bg" onClick="javascript:window.history.go(-1)">
                	<%	} %>
                </td>
            </tr>
            <tr><td colspan="2"><%@ include file="/admin/view/AdminEnd.jsp" %></td></tr>
        </table>
    </center>
</body>
</html>
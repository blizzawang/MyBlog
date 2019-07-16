<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.yxq.valuebean.*" %>
<html>
<head>
	<title>博客首页</title>
</head>
<body>
    <center>
        <table width="778" height="600" border="0" cellspacing="0" cellpadding="0" bgcolor="#F0EAED" style="word-break:break-all">
            <tr height="281"><td colspan="2"><%@ include file="/front/view/FrontTop.jsp"%></td></tr>
            <tr>
                <td width="230" valign="top"><jsp:include page="/front/view/FrontLeft.jsp"/></td>
                <td width="548" align="center" valign="top">
                    <!-- 显示 我的文章 -->
                    <table border="0" width="94%" rules="none" cellspacing="0" cellpadding="8" style="margin-top:8">
                        <tr height="40">
                        	<td>【我的文章】</td>
                        	<td align="right"><a href="<%=path%>/front/article/ArticleIndex.jsp">更多..</a></td>
                        </tr>
                        <%
                           	List articleList=(List)request.getAttribute("articleList");
                        	if(articleList==null||articleList.size()==0){
                        %>
                        <tr height="100"><td align="center"><li>博主目前还未发表任何文章！</li></td></tr>
                        <%  
                        	}
                        	else{
                         		int i=0;
                        		while(i<articleList.size()){
                        			ArticleBean articleSingle=(ArticleBean)articleList.get(i);
                        %>
 						<tr>
 							<td class="tdg" colspan="2">
 								▲
	 							<a href="<%=path%>/ArticleServlet?action=read&id=<%=articleSingle.getId()%>">
 									<b><%=articleSingle.getTitle()%></b>
 								</a>
 								[<%=articleSingle.getCreate()%>]
 							</td>
 						</tr>
 						<tr height="60"><td class="tdg" colspan="2" valign="top"><%=articleSingle.getContent(50) %></td></tr>
 						<tr><td style="text-indent:20" colspan="2"><a href="<%=path%>/ArticleServlet?action=read&id=<%=articleSingle.getId()%>">阅读全文</a></td></tr>
 						<tr>
 							<td align="right" colspan="2">
 								发表时间：<%=articleSingle.getSdTime() %> |
 								评论：<%=articleSingle.getReview() %> |
 								阅读：<%=articleSingle.getCount() %> 次
 							</td>
 						</tr>
		                <tr height="1"><td background="<%=path%>/images/line.jpg" colspan="2"></td></tr>
                        <%
                        			i++;
                        		}
                        	}
                        %>
                    </table>                    
                    <!-- 显示 我的照片 -->
                    <table border="0" width="94%" rules="none" cellspacing="8" style="margin-top:10" >
						<tr height="40">
							<td colspan="2">【我的相册】</td>
							<td colspan="2" align="right"><a href="<%=path%>/PhotoServlet?action=list">更多..</a>
						</tr>
						<%
                           	List photoList=(List)request.getAttribute("photoList");
                        	if(photoList==null||photoList.size()==0){
                        %>
                        <tr height="130"><td align="center"><li>博主目前还未上传任何照片！</li></td></tr>
                        <%  
                        	}
                        	else{
                         		int i=0;
                        		while(i<photoList.size()){
                        			PhotoBean photoSingle=(PhotoBean)photoList.get(i);
                        			if(i%4==0)
                        				out.print("<tr align='center' height='120'>");
			                        out.print("<td width='25%' style='border:1 solid'><a href='PhotoServlet?action=single&id="+photoSingle.getId()+"' target='_blank'><img src='"+photoSingle.getPhotoAddr()+"' title='"+photoSingle.getPhotoInfo()+"' border='0' width='120' height='90'></a></td>");
                        			if((i+1)%4==0)
                        				out.print("</tr>");
                        			i++;
                        		}
                        	}
                        %>
                    </table>
                </td>
            </tr>
            <tr height="70"><td colspan="2"><%@ include file="/front/view/FrontEnd.jsp" %></td></tr>
        </table>
    </center>
</body>
</html>
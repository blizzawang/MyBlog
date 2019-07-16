<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.yxq.valuebean.ArticleBean" %>
<%@ page import="com.yxq.valuebean.ArticleTypeBean" %>
<html>
<head>
	<title>聆音博客-所有文章</title>
</head>
<body>
    <center>
        <table width="778" height="600" border="0" cellspacing="0" cellpadding="0" bgcolor="#F0EAED">
            <tr height="281"><td colspan="2"><%@ include file="/front/view/FrontTop.jsp" %></td></tr>
            <tr>
                <td width="230" valign="top"><jsp:include page="/front/view/FrontLeft.jsp"/></td>
                <td width="548" align="center" valign="top">
                    <table width="100%" height="55" border="0" cellsapcing="0" cellpadding="0" background="images/main_B.jpg">
                        <tr align="center" style="padding-bottom:10">
                        	<%
                        		ArrayList typelist=(ArrayList)session.getAttribute("artTypeList");
                        		if(typelist==null||typelist.size()==0){
                        	%>
                        	<td align="center"><li>没有文章类别可显示！</li></td>
                        	<%	
                        		}
                        		else{
                        			for(int i=0;i<typelist.size();i++){
                        				ArticleTypeBean single=(ArticleTypeBean)typelist.get(i);
                        	%>
                        	<td><a href="<%=path%>/ArticleServlet?action=select&typeId=<%=single.getId() %>"><%=single.getTypeName() %></a></td>
                        	<%
                        			}
                        		}
                        	%>
                        </tr>      
                    </table>
                    <table width="95%" border="0" cellsapcing="0" cellpadding="0">
                  	<% 
			        	ArrayList articlelist=(ArrayList)request.getAttribute("articleList"); 
            			if(articlelist==null||articlelist.size()==0)
			            	out.print("<tr height='60'><td align='center'>选择的文章类别中没有任何文章！</td></tr>");
            			else{            
                     		ArticleBean articleSingle=(ArticleBean)articlelist.get(0);
                     		int books=articlelist.size();
                    		String typeName="推荐文章";
                    		if(articleSingle.getTypeId()==1)
                    			typeName="个人随想";
                    		if(articleSingle.getTypeId()==2)
                    			typeName="个人日记";
                    		if(articleSingle.getTypeId()==3)
                    			typeName="人生感悟";
                    		if(articleSingle.getTypeId()==4)
                    			typeName="文章推荐";
                    		out.print("<tr height='60'><td align='center'>【"+typeName+" 共 "+books+" 篇】</td></tr>");                            
            				int i=0;
                        	while(i<articlelist.size()){
                        		articleSingle=(ArticleBean)articlelist.get(i);            
                        %>
 						<tr height="30">
 							<td colspan="2">
 							    ▲
	 							<a href="<%=path%>/ArticleServlet?action=read&id=<%=articleSingle.getId()%>">
 									<b><%=articleSingle.getTitle() %></b>
 								</a>
 								[<%=articleSingle.getCreate()%>]
 							</td>
 						</tr>
 						<tr height="60"><td colspan="2" valign="top"><%=articleSingle.getContent(100) %></td></tr>
 						<tr height="30"><td style="text-indent:20" colspan="2"><a href="<%=path%>/ArticleServlet?action=read&id=<%=articleSingle.getId()%>">阅读全文</a></td></tr>
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
            			<tr><td>
            		</table>
                </td>
            </tr>
            <tr height="100"><td colspan="2"><%@ include file="/front/view/FrontEnd.jsp" %></td></tr>
        </table>
    </center>
</body>
</html>
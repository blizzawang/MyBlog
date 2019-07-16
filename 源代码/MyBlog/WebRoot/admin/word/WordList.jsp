<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.yxq.valuebean.WordBean" %>

<html>
<head>
	<title>博客后台-查看所有留言</title>
</head>
<body>
    <center>
        <table width="778" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" style="word-break:break-all">
            <tr><td colspan="2"><%@ include file="/admin/view/AdminTop.jsp" %></td></tr>
            <tr>
                <td valign="top" bgcolor="#E59FD5"><jsp:include page="/admin/view/AdminLeft.jsp"/></td>
                <td align="center" valign="top">
                	<table width="560" border="0" cellspacing="0" cellpadding="0" style="word-break:break-all">
                  	<tr height="40"><td colspan="2">【浏览留言】</td></tr>
                  	<% 
			        	List wordlist=(List)request.getAttribute("adminwordList"); 
            			if(wordlist==null||wordlist.size()==0)
			            	out.print("<tr height='60'><td align='center'>暂无留言可显示！</td></tr>");
            			else{
                     		int num=wordlist.size();     
                    		out.print("<tr height='40'><td align='center'>【我的留言 共 "+num+" 条】</td></tr>");                            
            				int i=0;
                        	while(i<wordlist.size()){
                         		WordBean wordSingle=(WordBean)wordlist.get(i);            
                        %>
 						<tr height="40">
 							<td style="text-indent:20">
 							    ▲ <b><%=wordSingle.getWordAuthor() %></b>
 							</td>
 						</tr>
 						<tr align="right" height="20"><td width="40%"><%=wordSingle.getWordTime()%>&nbsp;&nbsp;</td></tr>
 						<tr height="60"><td style="text-indent:20" colspan="2" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;<%=wordSingle.getWordContent()%></td></tr> 						
		                <tr height="30"><td align="right" colspan="2"><a href="WordServlet?action=delete&id=<%=wordSingle.getId() %>">[删除留言]</a></td></tr>
		                <tr height="1"><td background="<%=path%>/images/line.jpg" colspan="2"></td></tr>
                        <%
                        			i++;
                        		}
                        	}
            			%>
            		</table>
                </td>
            </tr>
            <tr><td colspan="2"><%@ include file="/admin/view/AdminEnd.jsp" %></td></tr>
        </table>
    </center>
</body>
</html>
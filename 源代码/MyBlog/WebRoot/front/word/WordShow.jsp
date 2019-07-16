<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.yxq.valuebean.WordBean" %>
<html>
<head>
	<title>聆音博客-查看留言</title>
</head>
<body>
    <center>
        <table width="778" height="600" border="0" cellspacing="0" cellpadding="0" bgcolor="#F0EAED">
            <tr height="281"><td colspan="2"><%@ include file="/front/view/FrontTop.jsp"%></td></tr>
            <tr>
                <td width="230" valign="top"><jsp:include page="/front/view/FrontLeft.jsp"/></td>
                <td width="548" align="center" valign="top">
                	<table width="95%" border="0" cellspacing="0" cellpadding="0" style="word-break:break-all">
                  	<% 
			        	List wordlist=(List)request.getAttribute("wordList"); 
            			if(wordlist==null||wordlist.size()==0)
			            	out.print("<tr height='60'><td align='center'>暂无留言可显示！</td></tr>");
            			else{
                     		int num=wordlist.size();     
                    		out.print("<tr height='60'><td align='center'>【我的留言 共 "+num+" 条】</td></tr>");                            
            				int i=0;
                        	while(i<wordlist.size()){
                         		WordBean wordSingle=(WordBean)wordlist.get(i);            
                        %>
 						<tr height="50">
 							<td style="text-indent:20">
 							    ▲ <b><%=wordSingle.getWordAuthor() %></b>
 							</td>
 						</tr>
 						<tr align="right" height="20"><td width="40%"><%=wordSingle.getWordTime()%>&nbsp;&nbsp;</td></tr>
 						<tr height="60"><td style="text-indent:20" colspan="2" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;<%=wordSingle.getWordContent()%></td></tr> 						
		                <tr height="1"><td background="<%=path%>/images/line.jpg" colspan="2"></td></tr>
                        <%
                        			i++;
                        		}
                        	}
            			%>
            		</table>
            		<form action="<%=path%>/WordServlet" method="post">
            		<input type="hidden" name="action" value="add">
            		<table width="95%" border="0" cellspacing="8" cellpadding="0" style="margin-top:10">
            			<tr height="30"><td colspan="2" align="center"><b>给我留言</b></td></tr>
            			<tr>
            				<td width="25%" align="center">留 言 者：</td>
            				<td><input type="text" name="wordAuthor" size="40" value="匿名好友"></td>
            			</tr>            			
            			<tr>
            				<td width="25%" align="center">留言标题：</td>
            				<td><input type="text" name="wordTitle" size="60"></td>
            			</tr>
            			<tr>
            				<td align="center">留言内容：</td>
            				<td><textarea name="wordContent" rows="10" cols="50"></textarea></td>
            			</tr>
            			<tr>
            			 	<td></td>
            				<td>
            					<input type="submit" value="提交" style="width:50">
            					<input type="reset" value="重置" style="width:50">
            				</td>            				
            			</tr>
            		</table>
            		</form>
                </td>
            </tr>
            <tr height="100"><td colspan="2"><%@ include file="/front/view/FrontEnd.jsp" %></td></tr>
        </table>
    </center>
</body>
</html>
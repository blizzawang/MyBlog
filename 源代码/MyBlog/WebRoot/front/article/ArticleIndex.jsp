<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList" %>
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
                    <table width="100%" height="55" border="0" cellsapcing="0" cellpadding="0" background="<%=path%>/images/main_B.jpg">
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
	                <p>请选择一种文章类别！
                </td>
            </tr>
            <tr height="100"><td colspan="2"><%@ include file="/front/view/FrontEnd.jsp" %></td></tr>
        </table>
    </center>
</body>
</html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.yxq.valuebean.FriendBean" %>
<html>
<head>
	<title>聆音博客-所有好友</title>
</head>
<body>
    <center>
        <table width="778" height="600" border="0" cellspacing="0" cellpadding="0" bgcolor="#F0EAED">
            <tr height="281"><td colspan="2"><%@ include file="/front/view/FrontTop.jsp" %></td></tr>
            <tr>
                <td width="230" valign="top"><jsp:include page="/front/view/FrontLeft.jsp"/></td>
                <td width="548" align="center" valign="top">
	                <table border="0" width="95%" style="margin-top:10" >
						<tr height="60"><td colspan="4">【我的好友】</td></tr>                
                		<% 
					       	List friendlist=(List)request.getAttribute("friendList"); 
        		    		if(friendlist==null||friendlist.size()==0)
			    		       	out.print("<tr height='60'><td align='center'>博主目前还未添加任何好友！</td></tr>");
		            		else{                          
           						int i=0;
                       			while(i<friendlist.size()){
                       				FriendBean friendSingle=(FriendBean)friendlist.get(i);
                       				String sexImg="/MyBlog/front/friend/pic/";
                       				if(friendSingle.getSex().equals("男"))
                       					sexImg+="man.jpg";//sexImg="/MyBlog/front/friend/pic/man.jpg"
                       				else if(friendSingle.getSex().equals("女"))
                       					sexImg+="women.jpg";
	                    %>
 						<tr height="30">
 							<td align="center" width="10%"><img src="<%=sexImg%>" border="1 solid"></td>
 							<td align="center"><a href="FriendServlet?action=single&id=<%=friendSingle.getId()%>"><%=friendSingle.getName()%></a></td>
 							<td align="center" width="5%"><img src="<%=path%>/front/friend/pic/hand.jpg"></td>
 							<td align="center" width="13%"><a href="#">好友博客</a></td>
 						</tr>
                        <%
                        			i++;
                        		}
                        	}
            			%>
            		</table>
                </td>
            </tr>
            <tr height="100"><td colspan="2"><jsp:include page="/front/view/FrontEnd.jsp" /></td></tr>
        </table>
    </center>
</body>
</html>
<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="snippet.Snippet"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><%=Snippet.BBS_NAME%></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
	body {
		width:1003px;
		align:center;
		margin-left:300px;
	}
	</style>
	<link href="css/style_1.css" rel="stylesheet" type="text/css">
  </head>
  <body>
  <center>
  <table align="center">
  <tr>
  <td align="center">
<jsp:include page="top.jsp"/>
   <p align="center"><jsp:include page="middle.jsp"/></p>
   <p>&nbsp;&nbsp;</p>
   <jsp:include page="foot.jsp"/> 
   </td>
   </tr>
   </table> 
   </center>
  </body>
</html>
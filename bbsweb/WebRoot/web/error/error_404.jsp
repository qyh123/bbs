<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="snippet.Snippet"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title><%=Snippet.BBS_NAME%>-������ʾ</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link href="css/style_1.css" rel="stylesheet" type="text/css">
  </head>
  
  <body>
    <br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	

		<div class="box message" align="left" style="width: 700px">
			<h1>
				<%=Snippet.BBS_NAME%>��������Ϣ
			</h1>
			<p>
				��Ҫ���ʵ�ҳ�治���ڣ�
			</p>
			<p>
				<script type="text/javascript">
		        setTimeout("location.href='/bbsweb/index.jsp'",3000);
		        </script>
			</p>
			<p>
				<a href="/bbsweb/index.jsp"> [�������ص���ҳ]</a>
			</p>
		</div><br>
  </body>
</html>

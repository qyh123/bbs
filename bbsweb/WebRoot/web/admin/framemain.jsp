<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="snippet.Snippet" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">



<html>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<meta http-equiv="Cache-Control" content="no-store" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<meta http-equiv="Content-Language" content="zh-cn">
	<head>
		<link rel="stylesheet" type="text/css" id="css"
			href="/bbsweb/css/admincp.css" />

		<title><%=Snippet.BBS_NAME%>－后台管理</title>
	</head>
	<c:if test="${user==null}">
		<script type="text/javascript">
          window.close();
        </script>
	</c:if>
	<c:if test="${user!=null}">
		<frameset framespacing="0" rows="60,100%">
			<frame name="header" frameborder="0" scrolling="no" marginheight="0" noresize
				src="/bbsweb/web/admin/head.jsp">
			<frameset cols="170,*" frameborder="no" border="0" framespacing="0">
				<frame frameBorder=0 name=left
					src="/bbsweb/web/admin/left.jsp" style="HEIGHT: 100%;"
					marginwidth="0" marginheight="0" scrolling="auto" noresize>
				<frame frameBorder=0 name="main"
					src="/bbsweb/web/admin/center.jsp" style="HEIGHT: 100%;"
					marginwidth="0" marginheight="0" scrolling="auto" noresize>
			</frameset>
			<noframes>
				<p>
					此网页使用了框架，但您的浏览器不支持框架。
				</p>
			</noframes>
		</frameset>
	</c:if>
</html>
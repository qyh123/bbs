<%@ page language="java" import="java.util.*" pageEncoding="Gb2312"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="snippet.Snippet"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title><%=Snippet.BBS_NAME%>-搜索</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script language="javascript" src="js/middle.js"></script>
		<link href="css/style_1.css" rel="stylesheet" type="text/css">

	</head>

	<body>
		<center>
			<jsp:include page="/top.jsp"></jsp:include>
			<c:if test="${bunUser.bunState==3||user==null}">
				<jsp:forward page="/web/bbs/pointInfo.jsp"></jsp:forward>
			</c:if>
			<div id="foruminfo" style="width: 1003px">
				<div id="nav">
					<p>
						<a href="index.jsp"><%=Snippet.BBS_NAME%></a>&raquo;搜索
					</p>
				</div>
			</div>
			<div class="mainbox forumbox" id="forumlist" style="width: 1003px;">
				<h1 align="left">
					&nbsp;&nbsp;搜索
				</h1>
				<form action="searchUser.do" name="userForm" method="post">
					<table width="1003" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="270">
								&nbsp;&nbsp;关键字
							</td>
							<td width="733">
								<input type="text" name="userSearch" size="30" />
							</td>
						</tr>

						<tr>
							<td>
								&nbsp;&nbsp;会员搜索
							</td>
							<td>
								<input name="user" type="radio" value="UserName" checked />
								会员名
								<input name="user" type="radio" value="UserId" />
								&nbsp;&nbsp;会员编号
							</td>

						</tr>

						<tr>
							<th>
								&nbsp;
							</th>
							<td>
								<button class="button" onClick="search(0)">
									搜索
								</button>
							</td>

						</tr>
					</table>
				</form>
				<form action="searchTopic.do" name="searchForm" method="post">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="270">
								&nbsp;&nbsp;关键字
							</td>
							<td width="733">
								<input name="sectionSearch" type="text" size="30" />
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;&nbsp;帖子搜索
							</td>
							<td>
								<input name="note" type="radio" value="TopicContent" checked />
								内容
								<input name="note" type="radio" value="TopicTopic" />
								标题
								<input name="note" type="radio" value="TopicAuthor" />
								作者
							</td>

						</tr>
						<tr>
							<th>
								&nbsp;
							</th>
							<td>
								<button class="button" onClick="search(1)">
									搜索
								</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
			<p>
				&nbsp;
			</p>
			<jsp:include page="/foot.jsp"></jsp:include>
		</center>
	</body>
</html>

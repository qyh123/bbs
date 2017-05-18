<%@ page contentType="text/html;charset=GB2312"%>
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
		<meta http-equiv="Content-Type" content="text/html;charset=gb2312">
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<meta http-equiv="Content-Language" content="zh-cn">
		<link href="css/admincp.css" type="text/css" rel="stylesheet">
		<script src="scripts/common.js" type="text/javascript"></script>
		<style type="text/css">
</style>
		<title></title>
	</head>
	<body>
		<center>
			<table width="99%" border="0" cellpadding="2" cellspacing="6">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="guide">
				<tr>
					<td>
						<a href="web/admin/center.jsp">系统后台管理</a>&nbsp;&raquo;&nbsp;后台首页
					</td>
				</tr>
			</table>
			<br />

			<table width="99%" border="0" cellpadding="0" cellspacing="0"
				class="tableborder">
				<tr class="header">
					<td colspan="2">
						消息提示
					</td>
				</tr>
				<tr class="altbg1">
					<td width="25%">
						找回密码的用户量
					</td>
					<td width="75%">

						<font size="3" color="red">${passwordRecorders}</font>
					</td>
				</tr>
			</table>
			<br>
			<form action="displayUser.do?method=show" method="post">
				<table width="99%" border="0" cellpadding="0" cellspacing="0"
					class="tableborder">
					<tr class="header">
						<td colspan="3">
							快捷方式
						</td>
					</tr>
					<tr class="altbg1">
						<td>
							编辑用户
						</td>
						<td>
							<input type="text" size="30" name="title">
						</td>
						<td>
							<input class="button" type="submit" name="searchsubmit"
								value="提交">
						</td>
					</tr>
				</table>
			</form>
			<br />
			 
		 
				<jsp:include page="/web/admin/foot.jsp"></jsp:include>
		</center>
	</body>
</html>
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
	<head>
		<title></title>
		<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html;charset=GBK">
	<meta http-equiv="Cache-Control" content="no-store" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
	<meta http-equiv="Content-Language" content="zh-cn">
	<script src="js/middle.js" type="text/javascript"></script>
	<title><%=Snippet.BBS_NAME%>后台管理</title>
	<link rel="stylesheet" type="text/css" id="css"
		href="css/admincp.css" />
	<base target="main">
	<body>
	<c:if test="${user==null}">
		<script type="text/javascript">
         parent.window.location.href="web/adminlogin.jsp";
        </script>
	</c:if>
		<table cellspacing="3">
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="160" border="0" cellspacing="0" align="center"
			cellpadding="0" class="leftmenulist" style="margin-bottom: 5px;">
			<tr class="leftmenutext">
				<td>
					<div align="center">
						<a href="index.jsp" target="_blank">论坛首页</a>&nbsp;&nbsp;
						<a href="web/admin/center.jsp">后台首页</a>
					</div>
				</td>
			</tr>
		</table>
		<table width="160" class="leftmenulist" align="center" border="0"
			cellspacing="0" cellpadding="0" style="margin-bottom: 5px;">
			<tr onClick="display1(truser,imguser);">
				<td class="leftmenutext">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr class="leftmenutext">
							<td>
								<img src="images/admincp/menu_reduce.gif" id="imguser">
							</td>
							<td>
								用户信息管理
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr class="leftmenutd" id="truser">
				<td>
					<table border="0" cellspacing="0" cellpadding="0"
						class="leftmenuinfo">
						<tr>
							<td>
								<a href="saveToken.do?path=addUser">添加用户</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="displayUser.do?method=show">编辑用户</a>
							</td>
						</tr>
						<!-- <tr>
							<td>
								<a href="findPasswordManage.do?method=show">找回密码管理</a>
							</td>
						</tr> -->
						<tr>
							<td>
								<a href="saveToken.do?path=bunUser">禁止用户</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table width="160" class="leftmenulist" align="center" border="0"
			cellspacing="0" cellpadding="0" style="margin-bottom: 5px;">
			<tr onClick="display1(trsection,imgsection)">
				<td>
					<table width="100%" border="0" cellspacing="0" align="center"
						cellpadding="0">
						<tr class="leftmenutext">
							<td>
								<img src="images/admincp/menu_reduce.gif" id="imgsection">
							</td>
							<td>
								版块管理
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr id="trsection" class="leftmenutd">
				<td>
					<table border="0" cellspacing="0" cellpadding="0"
						class="leftmenuinfo">
						<tr>
							<td>
								<a href="displaySection.do?method=show">编辑版块</a>
							</td>
						</tr>
						<tr>
							<td>
								<a href="saveToken.do?path=add_section">添加版块</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table width="160" class="leftmenulist" align="center" border="0"
			cellspacing="0" cellpadding="0" style="margin-bottom: 5px;">
			<tr onClick="display1(trtopic,imgtopic)" class="leftmenutext">
				<td class="leftmenutext">
					<table width="100%" border="0" cellspacing="0" align="center"
						cellpadding="0">
						<tr class="leftmenutext">
							<td>
								<img src="images/admincp/menu_reduce.gif" id="imgtopic">
							</td>
							<td>
								帖子管理
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr id="trtopic" class="leftmenutd">
				<td>
					<table border="0" cellspacing="0" cellpadding="0"
						class="leftmenuinfo">
						<tr>
							<td>
								<a
									href="displayTopic.do?method=show&resultState=0">批量主题管理</a>
							</td>
						</tr>
						<tr>
							<td>
								<a
									href="deletePosts.do?method=show&resultState=0">批量删帖</a>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

		<table width="160" border="0" cellspacing="0" align="center"
			cellpadding="0" class="leftmenulist">
			<tr class="leftmenutext">
				<td>
					<div style="margin-left: 48px;">
						<a href="adminlogin.do?flag=out">退出</a>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>

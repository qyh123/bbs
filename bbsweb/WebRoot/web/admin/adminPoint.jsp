<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
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

		<title>后台信息提示</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="css/admincp.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<center>
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<div style="width: 500px;height:400px">
				<table border="0" cellpadding="0" cellspacing="0" align="center"
					class="tableborder">
					<tr class="header">
						<td>
							<%=Snippet.BBS_NAME%>提示
						</td>
					</tr>
					<tr>
						<td class="altbg2">
							<div align="center">
								<c:if test="${message=='addsuccess'}">
									<p>
										添加用户成功。
									</p>
									<p>
									  <a href="displayUser.do?method=show">[ 点击这里回到编辑用户页面]</a>
									</p>
								</c:if>
								<c:if test="${message=='adderror'}">
									<p>
										添加用户失败。
									</p>
									<p>
										<a href="saveToken.do?path=addUser">[ 点击这里返回上一页 ]</a>
									</p>
								</c:if>
								<c:if test="${message=='sectionSuccess'}">
									<p>
										添加版块成功,。
									</p>
									<p>
		                              <a href="displaySection.do?method=show">[ 点击这里回到编辑版块页面]</a>

									</p>
								</c:if>
								<c:if test="${message=='sectionError'}">
									<p>
										添加版块失败。
									</p>
									<p>
										<a href="saveToken.do?path=add_section">[ 点击这里返回上一页 ]</a>
									</p>
								</c:if>
								<c:if test="${message=='bunError'}">
									<p>
										禁止用户操作失败！
									</p>
									<p>
										<a href="saveToken.do?path=bunUser">[ 点击这里返回上一页 ]</a>
									</p>
								</c:if>
								<c:if test="${message=='bunSuccess'}">
									<p>
										禁止用户操作成功！
									</p>
									<p>
										<script type="text/javascript">
		         setTimeout("location.href='saveToken.do?path=bunUser'",2000);
		        </script>
										<a href="saveToken.do?path=bunUser">[ 点击立即返回上一页 ]</a>
									<p>
								</c:if>
							</div>
							<br />
							<br />
						</td>
					</tr>
				</table>
			</div>
			<br />
			<br />
			<br />
			<br />
			<br />
			<jsp:include page="/web/admin/foot.jsp"></jsp:include>
		</center>
	</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
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

		<title>登录</title>
		<style type="text/css">
<!--
.STYLE2 {
	font-size: 15px;
	font-weight: bold;
	color: #FF0000;
}
-->
</style>
		<script type="text/javascript">
 function validate(){
     if(document.getElementsByName("userName")[0].value=="")
	  {
		  alert("请输入用户名！");
		  document.getElementsByName("userName")[0].focus();
		  return false;
	  }
  
	  if(document.getElementsByName("userPassword")[0].value=="")
	  {
		  alert("请输入密码！");
		  document.getElementsByName("userPassword")[0].focus();
		  return false;
	  }
}
  </script>


		<link href="css/style_1.css" rel="stylesheet" type="text/css">
		<style type="text/css">
<!--
.STYLE5 {
	color: #006699
}

-->
a:visited {
	text-decoration: none;
	color: #006699;
}

a:hover {
	text-decoration: underline;
	color: #006699;
}

a:active {
	text-decoration: none;
	color: #006699;
}
</style>
	</head>
	<body>
		<jsp:include page="/top.jsp" />
		<center>

			<div id="foruminfo" style="width: 1003px">
				<div id="nav">
					<p>
						<a id="forumlist" href="index.jsp"><%=Snippet.BBS_NAME%></a>&raquo;会员登录
					</p>
				</div>
			</div>
			<div class="mainbox forumbox" id="forumlist" style="width: 1003px;">
				<h1 align="left">
					&nbsp;&nbsp;会员登录
				</h1>
				<form name="userForm" action="login.do?flag=login" method="post">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td width="260" height="35">
								&nbsp;用户名:
							</td>
							<td width="200">
								<input type="text" name="userName" size="30">
							</td>
							<td width="543">
								<a href="saveToken.do?path=register">&nbsp; 立即注册</a>
								<font color="red">${userWrong}</font>
							</td>
						</tr>
						<tr>
							<td height="35">
								&nbsp;密码:
							</td>
							<td>
								<input type="password" name="userPassword" size="30">
							</td>
							<td>
								<!-- <a href="saveToken.do?path=findpassword">&nbsp; 忘记密码</a> -->
								<font color="red">${passwordWrong}</font>
							</td>
						</tr>
						<tr>
							<td height="35" align="center"></td>
							<td>
								<input type="submit" onClick="return validate();" class="button"
									value="提交">
							</td>
							<td>
								&nbsp;
							</td>
						</tr>
					</table>
				</form>
			</div>
			<p>
				&nbsp;&nbsp;
			</p>
		</center>
		<jsp:include page="/foot.jsp" />
	</body>
</html>

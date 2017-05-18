<%@ page contentType="text/html; charset=gb2312" language="java"
	errorPage=""%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>top</title>
		<link href="css/style_1.css" rel="stylesheet" type="text/css" />
	</head>
	<body onkeydown="if(event.keyCode==27) return false;">
		<center>
			<div class="wrap"
				style="width: 1003px; margin-bottom: 5px; margin-top: 5px;"
				align="left"  >
				<img src="images/11.jpg" width="1003px" /> 
				<!-- <h2>
					<a href="index.jsp"><img src="images/default/logo.gif" /> </a>
				</h2> -->
			</div>
			<div class="wrap" style="width: 1003px;">
				<div id="menu" align="right">
					<ul style="margin-top: 2px;">
						<c:if test="${user==null}">
							<li>
								<a href="index.jsp">论坛首页</a><span style="color: #dddddd">|</span>
							</li>
							<li>
								<a href="web/login.jsp">登录</a><span style="color: #dddddd">|</span>
							</li>
							<li>
								<a href="saveToken.do?path=register" class="notabs">注册</a><span
									style="color: #dddddd">|</span>
							</li>
						</c:if>
						<c:if test="${user!=null}">
							<li>
								<h5>
									<a href="#">${user.userNName}</a>
								</h5>
							</li>
							<li>
								<a href="login.do?flag=out">退出</a><span style="color: #dddddd">|</span>
							</li>
							<li>
								<a href="index.jsp">论坛首页</a><span style="color: #dddddd">|</span>
							</li>
							<li>
								<a href="web/bbs/search.jsp">搜索</a><span style="color: #dddddd">|</span>
							</li>
							<li>
								<a href="saveToken.do?path=edituser">修改用户资料</a>
							</li>
							<c:if test="${user.userClass==3}">
								<li>
									<a href="web/adminlogin.jsp" >后台管理</a>
								</li>
							</c:if>
						</c:if>
					</ul>
				</div>
			</div>
		</center>
	</body>
</html>

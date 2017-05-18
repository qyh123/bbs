<%@ page language="java" pageEncoding="gbk"%>
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

		<title><%=Snippet.BBS_NAME%>-信息提示</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>
		<center>
			<jsp:include page="/top.jsp" />
			<%
				request.getAttribute("userPassword");
			%>
			<div id="foruminfo" style="width: 1003px">
				<div id="nav" align="left" style="width: 1003px">
					<a href="index.jsp"><%=Snippet.BBS_NAME %></a>&raquo; 提示信息
				</div>
			</div>
			<div class="box message" align="center" style="width:700px">
				<h1>
					<%=Snippet.BBS_NAME %>！ 提示信息
				</h1>
				<c:if test="${message=='success'}">

					<p>
						密码找回成功！
						<br />
						在一个工作日内到注册的邮箱内领取新的密码！
					</p>
				</c:if>
				<c:if test="${message =='error'}">
					<p>
						用户名，Email 地址或安全提问不匹配，请返回修改。
					</p>
					<p>
						<a href="saveToken.do?path=findpassword">[ 点击这里返回上一页 ]</a>
					</p>

				</c:if>
				<c:if test="${message=='editsuccess'}">
					<p>
						修改用户信息成功，3秒后跳转到论坛首页。
					</p>
					<script type="text/javascript">
		        setTimeout("location.href='index.jsp'",3000);
		        </script>
					<div id="time"></div>
				</c:if>
				<c:if test="${message=='editediterror'}">
					<p>
						修改用户信息失败。
					</p>
					<p>
						<a href="javascript:history.back()">[ 点击这里返回上一页 ]</a>
					</p>
				</c:if>
				<c:if test="${message=='guanshui'}">
					<p>
						禁止灌水！请您在15秒以后再进行此操作！
					</p>
					<p>
						<a href="javascript:history.back()">[ 点击这里返回上一页 ]</a>
					</p>
				</c:if>
				<c:if test="${message=='addsuccess'}">
					<p>
						用户注册成功，3秒后跳转到论坛首页。
					</p>
					<p>
						<script type="text/javascript">
						//location.href="login.do?flag=login&userName=${userName}&userPassword=${userPassword}&pointInfo=register";
		               setTimeout("location.href='login.do?flag=login&userName=${userName}&userPassword=${userPassword}&pointInfo=register'",3000);
		        </script>
					</p>
				</c:if>
				<c:if test="${message=='adderror'}">
					<p>
						用户注册失败。
					</p>
					<p>
						<a href="savaToken.do?path=register">[ 点击这里返回注册页 ]</a>
					</p>
				</c:if>

				<c:if test="${bunUser.bunState==3&&out==null}">
					<p>
						该用户并禁止访问。禁止访问时间到${bunUser.overTime}结束！
					</p>
					<p>
						<a href="login.do?flag=out">[ 点击这里立即返回首页 ]</a>
					</p>
				</c:if>
				<c:if test="${out!=null}">
					<p>
						退出成功。3秒后跳转到论坛首页！${bunUser.bunState}
					</p>
					<script type="text/javascript">
		           setTimeout("location.href='index.jsp'",3000);
		        </script>
					<p>
						<a href="web/login.jsp">[ 点击这里立即返回登录页 ]</a>
					</p>
				</c:if>
				<c:if test="${bunUser.bunState!=3&&bunUser.bunState!=null&&out==null&&param.post==null&&message==null}">
					<p>
						登录成功。3秒后跳转到论坛首页!
					</p>
					<script type="text/javascript">
		           setTimeout("location.href='index.jsp'",3000);
		        </script>
					<p>
						<a href="index.jsp">[ 点击这里立即进入首页 ]</a>
					</p>
				</c:if>
				<c:if test="${bunUser.bunState==2&&param.post!=null}">
					<p>
						您无权进行当前操作，这可能因以下原因之一造成
					</p>
					<p>
						<b>您所在的用户组(禁止发言)无法进行此操作。解禁时间为${bunUser.overTime}</b>
					</p>
					<p>
						您已经登录，但您的帐号或其所在的用户组无权访问当前页面。
					</p>
				</c:if>
			</div>
			<jsp:include page="/foot.jsp" />
		</center>
	</body>
</html>

<%@ page contentType="text/html; charset=gb2312" language="java"%>
<%@ page import="snippet.Snippet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>

		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>无标题文档</title>
		<script language="javascript" src="js/middle.js"></script>
		<script language="javascript" src="js/ajax_func.js"></script>
		<script type="text/javascript">
          function showMenu(trid){
             trid.style.display="";
          }
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
		<link href="css/style_1.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
</style>
	</head>
	<body>
		<center>
			<logic:notPresent name="sectionList" scope="request">
				<jsp:forward page="/index.do"></jsp:forward>
			</logic:notPresent>
			<c:if test="${bunUser.bunState==3}">
				<jsp:forward page="/web/bbs/pointInfo.jsp"></jsp:forward>
			</c:if>
			<c:if test="${user==null}">
				<table width="1003">
					<tr>
						<td valign="middle" class="headf" height="60">
							<div>
								<%=Snippet.BBS_NAME%>
							</div>
							<form action="login.do?flag=login" id="loginForm" method="post">
								<input type="text" id="username" name="userName" size="15"
									maxlength="40" tabindex="1" value="用户名"
									onclick="this.value = ''" />
								<input type="password" id="password" name="userPassword"
									size="10" />
								<button name="userlogin" type="submit" value="true">
									登录
								</button>
							</form>
						</td>
						<td>
							<div id="forumstats">
								<p>
									主题:
									<em>${topicsize}</em> 会员:
									<em>${usersize}</em>
									<a href="showTopicList.do?method=showGoodTopic">精华区</a>
									<a href="showTopicList.do?method=showNewTopic" class="dropmenu">查看新帖</a>
								</p>
							</div>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${user != null&&bunUser.bunState!=3}">
				<table width="1003">
					<tr>
						<td valign="middle" align="left" height="60">
							<div id="foruminfo" style="width: 300px;">
								<div id="userinfo">
									<div id="nav">
										<a href="#" class="dropmenu">${user.userNName} </a>
									</div>
									<a href="showTopicList.do?method=showNewTopic" class="dropmenu">查看新帖</a>
						</td>
						<td align="right">
							<div id="forumstats">
								<p>
									主题:
									<em>${topicsize}</em> 会员:
									<em>${usersize}</em><a
										href="showTopicList.do?method=showGoodTopic"> 精华区</a>
								</p>
							</div>
						</td>
					</tr>
				</table>
			</c:if>
			<div class="mainbox forumlist" id="forumlist" style="width: 1003px;">
				<span class="headactions"><input type="hidden" id="collapsed" value="1" /> <img
						id="category_1_img" src="images/default/collapsed_no.gif"
						title="收起/展开" alt="收起/展开"
						onclick="collapsed(category_1,category_1_img)" />
				</span>
				<h3 align="left">
					<%=Snippet.BBS_NAME%>
				</h3>
				<table width="1003" align="center" cellpadding="0" cellspacing="0"
					id="category_1" summary="category1">
					<thead class="category">
						<tr>
							<th width="323" style="font-size: 12px" align="center">
								版块
							</th>
							<td width="218" align="center">
								版主
							</td>
							<td width="195" align="center">
								主题帖数
							</td>
							<td width="265" align="center">
								版块简介
							</td>
						</tr>
					</thead>
					<logic:present name="sectionList">
						<logic:iterate id="sectionList" name="sectionList" indexId="i">
							<tbody>
								<tr>
									<th style="font-size: 14px" align="center">
										<a
											href="showTopicList.do?method=showTopic&sectionid=${sectionList.sectionId}">${sectionList.sectionName}</a>
									</th>
									<td align="center">
										${sectionList.sectionMasterName}
									</td>
									<td align="center">
										${sectionList.sectionTopicCount}
									</td>
									<td align="center">
										${sectionList.sectionProfile}
									</td>
								</tr>
							</tbody>
						</logic:iterate>
					</logic:present>
				</table>
			</div>
		</center>
	</body>
</html>

<%@ page language="java" pageEncoding="gb2312"%>
<%@ page import="snippet.Snippet"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

		<title><%=Snippet.BBS_NAME%>-回复</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script language="javascript">
    function validate(){
        var editor = FCKeditorAPI.GetInstance("topicContent");
        var chk = editor.GetXHTML();
        if(chk=="") {
           alert("内容不能为空");
           editor.Focus();
           return false;
 		} 
 	}
	</script>
		<style type="text/css">
</style>
	</head>

	<body>
		<jsp:include page="/top.jsp"></jsp:include>
		<center>
			<logic:notPresent name="user" scope="session">
				<jsp:forward page="/web/login.jsp"></jsp:forward>
			</logic:notPresent>
			<c:if test="${bunUser.bunState!=1}">
				<jsp:forward page="/web/bbs/pointInfo.jsp?post=reply"></jsp:forward>
			</c:if>
			<div id="foruminfo" style="width: 1003px">
				<div id="nav">
							<a href="index.jsp"><%=Snippet.BBS_NAME%> </a>&raquo;
							<a href="showTopicList.do?method=showTopic&sectionid=${section.sectionId}">
								${section.sectionName}</a>&raquo;
							<a href="show.do?method=show&topicid=${topicId}&author=${author}">
								${topic}</a>&raquo;发表回复
					</div>
				</div>
				<html:form action="show.do?method=replyTopic">
				<div class="mainbox formbox" style="width: 1003px">
					<h1 align="left">
								&nbsp;&nbsp;发表回复
					</h1>
					<table width="100%" border="0" cellSpacing=0 cellPadding=0>
						<thead >
							<tr>
								<td width="20%" height="30">
									用户名
								</td>
								<td width="80%">
									${ user.userName}[
									<a href="loginForm.do?flag=out">退出系统</a>]
								</td>
							</tr>
						</thead>
						<tr>
							<td valign="top">
								内容
							</td>
							<td height="200">
								<input type="hidden" name="topicid" value="${topicId}">
								<input type="hidden" name="author" value="${author}">
								<FCK:editor instanceName="topicContent" toolbarSet="easy"></FCK:editor>
						        <!-- <textarea rows="13" cols="160" name ="topicContent "></textarea>-->
						</tr>
						<tr class="btns">
							<td height="35">
							</td>
							<td align="left">
								<input type="submit" class="button" value="发表回复"
									onclick="validate()" />
							</td>
						</tr>
					</table>
				</div>
			</html:form>
		</center>
		<p>
			&nbsp;&nbsp;
		</p>
		<jsp:include page="/foot.jsp"></jsp:include>
	</body>

</html>
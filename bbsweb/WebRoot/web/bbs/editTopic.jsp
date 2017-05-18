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
		<title><%=Snippet.BBS_NAME%></title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script language="javascript">
  function validate(opration){
	        //var editor = FCKeditorAPI.GetInstance("topicContent");
	       // var chk = editor.GetXHTML();
	    var chk = document.forms[0].topicContent.value;
	    if(chk=="") {
	          alert("内容不能为空");
	           //editor.Focus();
	           document.all.topicContent.focus();
	           //return false;
	 	} 
 		if(opration==1) {
 		  document.forms[0].action="show.do?method=editPosts#r${replyid}";
 		   
 		}else {
 		   document.forms[0].action="show.do?method=replyTopic";
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
				<jsp:forward page="/web/bbs/pointInfo.jsp?post=edit"></jsp:forward>
			</c:if>
			<div id="foruminfo" style="width: 1003px">
				<div id="nav">
					<p>
						<a href="index.jsp"><%=Snippet.BBS_NAME%></a>&raquo;
						<a href="showTopicList.do?method=showTopic&sectionid=${section.sectionId}">${section.sectionName}</a>&raquo;
						<a href="show.do?method=show&topicid=${topicid}&author=${author}">${topictopic}</a>&raquo;
						<c:if test="${opration==1}">
							编辑帖子
						</c:if>
						<c:if test="${opration!=1}">
							发表帖子
						</c:if>
					</p>
				</div>
			</div>
			<html:form action="show.do?method=editPosts">
				<div class="mainbox formbox" style="width: 1003px">
					<h1 align="left">
						<c:if test="${opration==1}">
									&nbsp;&nbsp;编辑帖子
						</c:if>
						<c:if test="${opration!=1}">
						
									&nbsp;&nbsp;发表帖子
						</c:if>
					</h1>
					<table width="100%" border="0" cellSpacing=0 cellPadding=0>
						<thead >
							<tr>
								<td width="20%" height="30">
									用户名
								</td>
								<td width="80%">
									${ user.userName}
									<a href="login.do?flag=out">[退出系统]</a>
								</td>
							</tr>
						</thead>
						<tr>
							<td valign="top">
								内容
							</td>
							<td height="200">
								<input type="hidden" name="topicid" value="${topicid }">
								<input type="hidden" name="author" value="${author}">
								<input type="hidden" name="topicSign" value="${topicSign}">

								<c:if test="${topicSign==1}">
									<!--<FCK:editor instanceName="topicContent" toolbarSet="easy">
										<jsp:attribute name="value">
										     ${bbsreply}
										</jsp:attribute>
									</FCK:editor>-->
									<textarea rows="13" cols="160" name ="topicContent">${bbstopic}</textarea>
								</c:if>
								<c:if test="${topicSign==0}">
									<input type="hidden" name="replyid" value="${replyid}">
									<!--<FCK:editor instanceName="topicContent" toolbarSet="easy">
										<jsp:attribute name="value">
										     ${bbsreply}
										</jsp:attribute>
									</FCK:editor>-->
									<textarea rows="13" cols="160" name ="topicContent"> ${bbsreply}</textarea>
								</c:if>
							</td>
						</tr>
						<tr class="btns">
							<td height="35"></td>
							<c:if test="${opration==1}">
								<td align="left">
									<input type="hidden" name="pages" value="${page}">
									<input type="submit" class="button" value="编辑帖子"
										onclick="validate('${opration}')" />
								</td>
							</c:if>
							<c:if test="${opration!=1}">
								<td align="left">
									<input type="submit" class="button" value="发表帖子"
										onclick="validate('${opration}')" />
								</td>
							</c:if>
						</tr>
					</table>
				</div>
			</html:form>
			<p>
				&nbsp;&nbsp;
			</p>
		</center>

		<jsp:include page="/foot.jsp"></jsp:include>
	</body>

</html>


<%@ page language="java" pageEncoding="gbk"%>
<%@ page import="snippet.Snippet"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
		<title><%=Snippet.BBS_NAME%>-发表帖子</title>
		<link href="css/style_1.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="FCKeditor/fckeditor.js"></script>
		<script type="text/javascript">
 	function validate(){
 	    var editor = FCKeditorAPI.GetInstance("topicContent");
        var chk = editor.GetXHTML();
 		if(document.all.topicTopic.value==""){
 		   alert('主体不能为空！！');
 		   document.all.topicTopic.focus();
 		   return false;
 		}
        //else if(chk==""){
 		  // alert("内容不能为空");
           //editor.Focus();
           //return false;
 		}
 	}
 </script>


	</head>
	<body>
		<jsp:include page="/top.jsp"></jsp:include>
		<center>
			<logic:notPresent name="user" scope="session">
				<jsp:forward page="/web/login.jsp"></jsp:forward>
			</logic:notPresent>
			<c:if test="${bunUser.bunState!=1}">
				<jsp:forward page="/web/bbs/pointInfo.jsp?post=post"></jsp:forward>
			</c:if>
			<div id="foruminfo" style="width: 1003px">
				<div id="nav">
					<a href="index.jsp"><%=Snippet.BBS_NAME%> </a>&raquo;
					<a
						href="showTopicList.do?method=showTopic&sectionid=${section.sectionId}">
						${section.sectionName}</a>&raquo;发表帖子
				</div>
			</div>
			<html:form action="show.do?method=addTopic">
				<div class="mainbox formbox" style="width: 1003px">
					<h1 align="left">
						&nbsp;&nbsp;发表帖子
					</h1>
					<table width="100%" border="0" cellSpacing=0 cellPadding=0>
						<thead>
							<tr>
								<td width="20%" height="30">
									&nbsp;&nbsp;用户名
								</td>
								<td width="80%">
									${ user.userName}
									<input type="hidden" name="sectionId" value="${id}">
								</td>
							</tr>
						</thead>
						<tr>
							<td height="31">
								&nbsp;&nbsp;标题
							</td>
							<td height="31">
								<input type="text" name="topicTopic" size="50" maxlength="200">
							</td>
						</tr>
						<tr>
							<td valign="top">
								&nbsp;&nbsp;内容
							</td>
							<td height="200">

								<!-- <FCK:editor instanceName="topicContent" toolbarSet="easy">
								</FCK:editor>-->
								<textarea rows="13" cols="160" name ="topicContent"></textarea>
							</td>
						</tr>
						<tr class="btns">
							<td height="35"></td>
							<td align="left">
								<input type="submit" class="button" value="发表新贴"
									onClick="return validate()">
							</td>
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

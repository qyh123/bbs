<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="snippet.Snippet" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
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

		<title><%=Snippet.BBS_NAME%>-�һ�����</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="css/style_1.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		<Script language="javascript">
   function validate()
  {
  	  if(document.getElementsByName("userName")[0].value==""){
	  document.getElementById("nameInfo").innerText="��¼�ʺŲ���Ϊ�գ�";
	  document.getElementsByName("userName")[0].focus();
	  return false;
     }
  	  if(document.getElementsByName("userEmail")[0].value==""){
	   document.getElementById("emailInfo").innerText="�����ʼ���ַ����Ϊ�գ�";
	   document.getElementsByName("userEmail")[0].focus();
	  return false;
     }
      re=/^\w+@\w+(\.\w+)+$/;
	  if(re.test(document.getElementsByName("userEmail")[0].value)==false){
	   document.getElementById("emailInfo").innerText="��������Ч�ĵ����ʼ���ַ";
	  document.getElementsByName("userEmail")[0].focus();
	  return false;
    }
    return true;
}
p</Script>
		<center>
			<jsp:include page="/top.jsp" />
			<div id="foruminfo" style="width: 1003px">
				<div id="nav" align="left">
					<a href="index.jsp"><%=Snippet.BBS_NAME%></a>&raquo;��������
				</div>
			</div>
				<div class="mainbox forumbox" id="forumlist" style="width: 1003px;">
				<h1 align="left">
					&nbsp;&nbsp;��������
				</h1>
				<html:form action="findPassword.do" method="post">
				
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td width="270">
								&nbsp;�û���:
							</td>
							<td width="733">
								<input type="text" name="userName" size="40">
								<span id="nameInfo"></span>
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;Email:
							</td>
							<td>
								<input type="text" name="userEmail" size="40">
								<span id="emailInfo"></span>
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;
							</td>
							<td>
								<input type="submit" class="button" value="�ύ"
									onClick="return validate();" />
							</td>
						</tr>
					</table>
				</html:form>
			</div>
			<p>
				&nbsp;
			</p>
			<jsp:include page="/foot.jsp" />
		</center>
	</body>
</html>

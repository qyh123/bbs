<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<link rel="SHORTCUT ICON" href="favicon.ico" />
		<link href="css/admincp.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/ajax_func.js"></script>
		<script type="text/javascript">
function zoomtextarea(objname, zoom) {
	zoomsize = zoom ? 10 : -10;
	var obj = document.getElementById(objname);
	if(obj.rows<=36||(obj.rows==46&&zoom==0)){
	   if(obj.rows + zoomsize > 0 && obj.cols + zoomsize * 3 > 0) {
		  obj.rows += zoomsize;
		  obj.cols += zoomsize * 3;
	   }
	}
}
function validate(){
   if(document.all.username.value=="") {
      document.all.name.innerText= "������Ϸ����û�����";
      return false;
   }else {
      document.all.name.innerText= "";
   }
   if(document.all.reason.value=="") {
      document.all.bunReason.innerText= "���������ɣ�";
      return false;
   }else {
      document.all.bunReason.innerText= "";
   }
}

function go(){
       var username = document.all.username.value;
       send_request("POST","bunUser.do?method=validate&username="+username,null,"text", showCheck); 
   }
   
   function showCheck() { 
		if (http_request.readyState == 4) { // �ж϶���״̬
			if (http_request.status == 200) { // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
			    var text = http_request.responseText;
			    document.getElementById("message").innerText = http_request.responseText;
			    if(text.indexOf("��")!=-1) {
			       document.all.username.value="";
			    }   
			}else{
				alert("���������ҳ�����쳣��");
			}
		}
}

</script>
	</head>
	<body leftmargin="10" topmargin="10">
		<center>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="guide">
				<tr>
					<td>
						<a href="web/admin/center.jsp">ϵͳ��̨����</a>&nbsp;&raquo;&nbsp;��ֹ�û�
					</td>
				</tr>
			</table>
			<br>
			<html:form method="post" action="bunUser.do?method=bun">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="tableborder">
					<tr class="header">
						<td colspan="2">
							��ֹ�û�
						</td>
					</tr>
					<tbody>
						<tr>
							<td width="45%" class="altbg1">
								<b>�û���:</b><p id ="name"></p>
							</td>
							<td class="altbg2">
								<input type="text" size="50" name="username" value="" onBlur="go()">
								&nbsp;&nbsp;&nbsp;
							<span style="color: #666666" id="message"></span>
							</td>
						</tr>
						<tr>
							<td width="45%" class="altbg1">
								<b>��ֹ����:</b>
								<br />
								<span class="smalltxt">ѡ���ֹ�����ͻ��߻ָ����û�����ͨ���</span>
							</td>
							<td class="altbg2">
								<input type="radio" name="type" value="1" class="radio"
									checked>
								����״̬
								<br />
								<input type="radio" name="type" value="2" class="radio">
								��ֹ����
								<br />
								<input type="radio" name="type" value="3" class="radio">
								��ֹ����
							</td>
						</tr>
						<tr>
							<td width="45%" class="altbg1">
								<b>��ֹ�û���Ч��</b>
								<br />
								<span class="smalltxt">����Ч�ڹ�����û������Զ�������ƣ���Ϊ��ͨ�û���</span>
							</td>
							<td class="altbg2">
								<select name="bunOverTime">
								    <option value=999999>
										����&nbsp;
									<option value=1>
										һ��&nbsp;
									<option value=3 >
										����&nbsp;
									<option value=7>
										һ��&nbsp;
									<option value=14>
										����&nbsp;
									<option value=30 >
										һ����&nbsp;
									<option value=60>
										������&nbsp;
									<option value=180>
										����&nbsp;
									<option value=365>
										һ��&nbsp;
								</select>
							</td>
						</tr>

						<tr>
							<td width="45%" class="altbg1">
								<b>ɾ�����û�����:</b>
								<br />
								<span class="smalltxt">�ڽ�ֹ��������û���ͬʱ���Ƿ�ɾ�����û����е����������</span>
							</td>
							<td class="altbg2">
								<input class="radio" type="radio" name="delpost" value="1">
								ɾ�� &nbsp; &nbsp;
								<input class="radio" type="radio" name="delpost" value="0">
								�ָ�
								<input class="radio" type="radio" name="delpost" value="2" checked>
								����
							</td>
							
						</tr>
						<tr>
							<td width="45%" class="altbg1" valign="top">
								<b>����:</b>
								<br />
								<span class="smalltxt">��ֹ�����û�������</span>
							</td>
							<td class="altbg2">
								<img src="images/admincp/zoomin.gif"
									onmouseover="this.style.cursor='pointer'"
									onclick="zoomtextarea('reason', 1)">
								<img src="images/admincp/zoomout.gif"
									onmouseover="this.style.cursor='pointer'"
									onclick="zoomtextarea('reason', 0)">
								<br />
								<textarea rows="6" name="bunReason" id="reason" cols="50"></textarea>
								<p id ="bunReason"></p>
							</td>
						</tr>
					</tbody>
				</table>
				<br />
				<center>
					<input class="button" type="submit" name="bansubmit" value="�� ��" onclick="return validate()">
				</center>
			</html:form>
			<br />
			<br />
				<jsp:include page="/web/admin/foot.jsp"></jsp:include>
		</center>
	</body>
</html>

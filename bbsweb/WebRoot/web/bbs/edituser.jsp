<%@ page language="java" pageEncoding="gb2312"%>
<%@ page import="snippet.Snippet"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

		<title><%=Snippet.BBS_NAME%>-�޸��û�����</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="css/style_1.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="js/ajax_func.js"></script>
		<script type="text/javascript">	
 function showPassword(trid){
	 if(trid.style.display == ""){
		   trid.style.display = "none";
		   document.all.chkbox.value = 0;
		   document.all.oldPassword.value="${user.userPassword}";
		   document.all.newPassword.value="${user.userPassword}";
		   document.all.passwordInfo.innerText="";
	  }else{
		  trid.style.display = ""; 
		   document.all.chkbox.value = 1;
		   document.all.oldPassword.value="";
		   document.all.newPassword.value="";
		   document.all.passwordInfo.innerText="";
		   document.all.oldPassword.focus(); 
	  } 
 }
 function showImage(trid){
      if(document.getElementById("userImage").value == 0){
		   trid.style.display = "none";
		   document.getElementById("userImage").value = 1;
	  }else{
		  trid.style.display = ""; 
		   document.getElementById("userImage").value = 0;
	  }
 }
   function validateOldPword(){
       var username = document.all.userName.value;
       var password = document.all.oldPassword.value;
       send_request("POST","validateOldPassword.do?userName="+username+"&userPassword="+password ,null,"text", showCheck); 
   }
   function showCheck() { 
		if (http_request.readyState == 4) { // �ж϶���״̬
			if (http_request.status == 200) { // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
			    var text = http_request.responseText;
			    if(text.length!=0) {
			         document.getElementById("passwordInfo").innerText=http_request.responseText;
			         document.all.oldPassword.value="";
                     //document.all.oldPassword.focus();
			    } else {
			         document.getElementById("passwordInfo").innerText="";
			    }
			}else{
				alert("���������ҳ�����쳣��");
			}
		}
}
function validate(){
	  if(document.all.chkbox.value==1){
	     if(document.all.newPassword.value.length<6) {
		     document.all.newpadInfo.innerText="����̫��";
		     document.getElementsByName("newPassword")[0].focus();
		     return false;
	     }else{
	        document.all.newpadInfo.innerText="";
	     }
	     if(document.all.newPassword.value.length>30){
		    document.all.newpadInfo.innerText="����̫��";
		    document.getElementsByName("newPassword")[0].focus();
		    return false;
	     } else {
	       document.all.newpadInfo.innerText="";
	     }
		 document.all.isPassword.value=1;  
	  } else {
	       document.all.isPassword.value=0;
	  }
	  if(document.getElementById("image").value!='') {
	        var fileName = document.getElementById("image").value;
	        if( isPicture(fileName) == false) {
	           document.all.picture.innerText="�ļ���ʽ�Ƿ���ֻ����jpeg��jpg��bmp��png��gif��ʽ�ļ�";
	           return false;
	        }else {
	          document.all.picture.innerText="";
	        }
	   }
	  re=/^\w+@\w+(\.\w+)+$/;
	  if(re.test(document.getElementsByName("userEmail")[0].value)==false){
		   alert("��������Ч�ĵ����ʼ���ַ");
		   document.getElementsByName("userEmail")[0].focus();
		   return false;
	   }
}
//�ж��û��ǲ����ϴ���ͼƬ
   function isPicture(fileName) {
       if(fileName.lastIndexOf(".")!=-1) {
         var type = (fileName.substring(fileName.lastIndexOf(".")+1,fileName.length)).toLowerCase();
         var suppotFile = new Array();
         suppotFile[0] = "jpg";
         suppotFile[1] = "jpeg";
         suppotFile[2] = "png";
         suppotFile[3] = "gif";
         suppotFile[4] = "bmp";
         for(var i =0; i<suppotFile.length;i++ ){
            if(suppotFile[i]==type){
              return true;
            } else {
               continue;
            }
         }
         return false;
       }
   }
 </script>
	</head>

	<body>
		<jsp:include page="/top.jsp"></jsp:include>
		<c:if test="${bunUser.bunState==3||user==null}">
			<jsp:forward page="/web/bbs/pointInfo.jsp"></jsp:forward>
		</c:if>
		<center>
			<div id="foruminfo" style="width: 1003px">
				<div id="nav">
					<p>
						<a href="index.jsp"><%=Snippet.BBS_NAME%></a>&raquo;�޸��û�����
					</p>
				</div>
			</div>
			<div class="mainbox forumbox"  style="width: 1003px;">
				<h1 align="left">
					&nbsp;&nbsp;�޸�����
				</h1>
				<html:form action="editUser.do" enctype="multipart/form-data">
					<table cellpadding="0" cellspacing="0">
						<tr>
							<td width="250" height="30">
								&nbsp;&nbsp;����ͷ��
								<input type="hidden" name="userName" value="${user.userName}" />
								
							</td>
							<td width="753">
								<!--<img class="avatar" src="OutPicture?userId=${user.userId}"
									width="30" height="30"
									onerror="this.src='images/default/noavatar.gif'">-->
								<c:if test="${user.headImage==null||user.headImage==''}">
								<img class="avatar" src="images/default/noavatar.gif"
									width="30" height="30"
									onerror="this.src='images/default/noavatar.gif'">
								</c:if>
								<c:if test="${user.headImage!=null}">
								<img class="avatar" src="${user.headImage}"
									width="30" height="30"
									onerror="this.src='"+${user.headImage}+"'">
								</c:if>
								<input type="checkBox" value="1" id="userImage"
									onClick="showImage(trimage)" />
								����
							</td>
						</tr>
						<tr id="trimage" style="display: none">
							<td height="30">
								&nbsp;&nbsp;������ͷ��
							</td>
							<td>
								<html:file property="image" size="55%" styleId="image"></html:file>
								&nbsp;&nbsp;&nbsp;
								<span style="color: #666666" id="picture"></span>
							</td>
						</tr>
						<tr>
							<td height="30">
								&nbsp;&nbsp;�����ʼ���
							</td>
							<td>
								<input type="text" name="email" size="30"
									value="${user.userEmail}">
								&nbsp;&nbsp;
								<span style="color: #666666">�磺jianfeng@163.com</span>
							</td>
						</tr>
						<tr>
							<td height="30" colspan="2">
								&nbsp;&nbsp;�޸�����&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="checkBox" value="0" name="chkbox"
									onClick="showPassword(trpassword)" />
								<input type="hidden" name="isPassword" value="" />
							</td>
						</tr>
						<tbody id="trpassword" style="display: none">
							<tr>
								<td>
									&nbsp;&nbsp;�����룺
									<br>
								</td>
								<td>
									<input type="password" name="oldPassword" size="30"
										value="${user.userPassword}" onBlur="validateOldPword()">
									&nbsp;
									<span style="color: #666666" id="passwordInfo"></span>
									<br>
								</td>
							</tr>
							<tr>
								<td height="30">
									&nbsp;&nbsp;�����룺
									<br>
								</td>
								<td>
									<input type="password" name="newPassword" size="30"
										value="${user.userPassword}">&nbsp;&nbsp;
									<span style="color: #666666">���볤������6λ���ó���30λ</span>
									<span style="color: #666666" id="newpadInfo"></span>
									<br>
								</td>
							</tr>
						</tbody>
						<tr>
							<td colspan="2" align="center" height="30">
								<input type="submit" class="button" onClick="return validate()"
									value="��&nbsp;&nbsp;&nbsp;��">
								&nbsp;&nbsp;&nbsp;
								<input type="reset" class="button" value="��&nbsp;&nbsp;&nbsp;��">
							</td>
						</tr>
					</table>
				</html:form>
			</div>
			<p>
				&nbsp;&nbsp;
			</p>
		</center>
		<jsp:include page="/foot.jsp"></jsp:include>
	</body>
</html>

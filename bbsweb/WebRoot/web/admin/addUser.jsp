<%@ page language="java" import="java.util.*" pageEncoding="gbk"
	isELIgnored="false"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>register.jsp</title>
		<base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="js/ajax_func.js"></script>
		<link href="css/admincp.css" type="text/css" rel="stylesheet">
		<Script language="javascript">
		
  function validate(){
     
	   if(document.getElementsByName("userName")[0].value=='') {
	      document.all.message.innerText="������Ϸ����û���";
		  document.getElementsByName("userName")[0].focus();
		  return false;
	   }
	   if(document.getElementsByName("userNName")[0].value.length>15) {
		  document.all.nname.innerText="�ǳ�����15���ַ�";
		  document.getElementsByName("userNName")[0].focus();
		  return false;
	   } 
	   if(document.getElementsByName("userNName")[0].value=='') {
		  document.all.nname.innerText="������Ϸ����ǳ�";
		  document.getElementsByName("userNName")[0].focus();
		  return false;
	   }else {
	     var  nname =  document.getElementsByName("userNName")[0].value;
	     if(isChar(nname)==true){
	        document.all.nname.innerText="";
	     } else {
	       document.all.nname.innerText="�ǳƺ��зǷ��ַ�,���ܺ��� ',<,\",>,/,�ַ�";
	       return false;
	     }
	     
	   }
	  
	   if(document.getElementsByName("userPassword")[0].value.length<6||document.getElementsByName("userPassword")[0].value.length>30) {
		  document.all.password.innerText="���볤�Ȳ�С��6λ������30λ";
		  document.getElementsByName("userPassword")[0].focus();
		  return false;
	   }else {
	      document.all.password.innerText="";
	   }
	   if(document.getElementsByName("confirmpassword")[0].value!=document.getElementsByName("userPassword")[0].value) {
		  document.all.cpassword.innerText="���벻һ��";
		  document.getElementsByName("confirmpassword")[0].focus();
		  return false;
	   } else {
	      document.all.password.innerText="";
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
		  document.all.email.innerText="��������Ч�ĵ����ʼ���ַ";
		  document.getElementsByName("userEmail")[0].focus();
		  return false;
	   } else {
	       document.all.email.innerText="";
	   }
}
	 function go(){
      if(document.getElementsByName("userName")[0].value=='')
	  {
		  document.all.message.innerText="������Ϸ����û���";
		  return false;
	  } else {
       //document.forms[0].action="register.do?page=adduser";
       var username = document.all.userName.value;
       send_request("POST","register.do?method=validateUser&userName="+username,null,"text", showCheck); 
      }
   }
    function showCheck() { 
		if (http_request.readyState == 4) { // �ж϶���״̬
			if (http_request.status == 200) { // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
			    var text = http_request.responseText;
			    document.getElementById("message").innerText=http_request.responseText;
			    if(text.indexOf("�ѱ�")!=-1||text.indexOf("����")!=-1) {
			       document.all.userName.value="";
			    } 
			}else{
				alert("���������ҳ�����쳣��");
			}
		}
}
   function isChar(nname) {
         var s = new Array();
          s[0] = "'";
         s[1] = "\"";
          s[2] = "<";
         s[3] = ">";
          s[4] = "/";
         for(var i =0;i<s.length;i++ ){
            if(nname.indexOf(s[i])!=-1) {
		    	return false;
		    }else {
		      continue; 
		    }
         }
         return true;
   }
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
 
  
	</Script>

	</head>


	<body>
		<center>
			<table width="100%" border="0" cellpadding="2" cellspacing="6">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="guide">
				<tr>
					<td>
						<a href="web/admin/center.jsp">ϵͳ��̨����</a>&nbsp;&raquo;&nbsp;����û�
					</td>
				</tr>
			</table>
			<br />
			<html:form action="adduser.do" enctype="multipart/form-data">
				<table class="tableborder" cellpadding="0" cellspacing="0">
					<tr class="header">
						<td colspan="2">
							������û�
						</td>
					</tr>
					<tbody id="menu_846ed2c056ad9a1f">
						<tr>
							<td width="244" class="altbg1" height="30">
								&nbsp;&nbsp;�û�����
							</td>
							<td width="757" class="altbg2">
								<input type="text" size="65%" name="userName" onBlur="go()"
									value="${addUserName}">
								&nbsp;&nbsp; &nbsp;
								<span style="color: #666666" id="message"></span>
							</td>
						</tr>
						<tr>
							<td class="altbg1" height="30">
								&nbsp;&nbsp;�û��ǳƣ�
								
							</td>
							<td class="altbg2">
								<input type="text" name="userNName" size="65%">
								<input type="hidden" name="userClass" value="1" size="60%">
								&nbsp;&nbsp;&nbsp;
								<span style="color: #666666" id="nname"></span>
							</td>
						</tr>
						<tr>
							<td class="altbg1" height="30">
								&nbsp;&nbsp;���룺
								
							</td>
							<td class="altbg2">
								<input type="password" name="userPassword" size="65%">
								&nbsp;&nbsp;&nbsp;
								<span style="color: #666666" id="password"></span>
							</td>
						</tr>
						<tr>
							<td class="altbg1" height="30">
								&nbsp;&nbsp;����ȷ�ϣ�
							</td>
							<td class="altbg2">
								<input type="password" name="confirmpassword" size="65%">
								&nbsp;&nbsp;&nbsp;
								<span style="color: #666666" id="cpassword"></span>
							</td>
						</tr>
						<tr>
							<td class="altbg1" height="30">
								&nbsp;&nbsp;�ϴ�����ͷ��:
							</td>
							<td class="altbg2">
								<html:file property="image" size="65%" styleId="image"></html:file>
								&nbsp;&nbsp;&nbsp;
								<span style="color: #666666" id="picture"></span>
							</td>
						</tr>
						<tr>
							<td class="altbg1" height="30">
								&nbsp;&nbsp;�Ա�
							</td>
							<td class="altbg2">
								<input type="radio" name="userSex" class="radio" value="1"
									checked>
								<img src="images/default/boy.gif" width="24" height="24">
								��
								<input type="radio" name="userSex" class="radio" value="0">
								<img src="images/default/girl.gif" width="26" height="26">
								Ů
							</td>
						</tr>
						<tr>
							<td class="altbg1" height="30">
								&nbsp;&nbsp;�����ʼ���
								
							</td>
							<td class="altbg2">
								<input type="text" name="userEmail" size="65%">
								&nbsp;&nbsp;&nbsp;
								<span style="color: #666666" id="email"></span>
							</td>
						</tr>
				</table>
				<center>
					<input type="submit" class="button" value="�ύ"
						onclick="return validate()">
					&nbsp;&nbsp;&nbsp;
					<input type="reset" class="button" value="����">
				</center>
			</html:form>
			<br />
			<br />
			<jsp:include page="/web/admin/foot.jsp"></jsp:include>
		</center>
	</body>
</html>

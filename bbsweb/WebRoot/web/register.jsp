<%@ page language="java" pageEncoding="gbk"%>
<%@ page import="snippet.Snippet"%>
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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>ע��</title>
		<base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="css/style_1.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="js/ajax_func.js"></script>
		
	</head>
	<Script language="javascript">
  //��֤����û����û�ע��ı�
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
	   if(document.all.law.value==1) {
         document.all.lawMsg.innerText="ͬ�Ȿ��̳����󷽿�ע��";
	     return false;
	   }else {
	      document.all.lawMsg.innerText="";
	   }
	   
}
//�ж��û��ǳƵĺϷ���
   function isChar(nname) {
         var s = new Array();
          s[0] = "'";
          s[1] = "\"";
          s[2] = "<";
          s[3] = ">";
          s[4] = "/";
         for(var i =0;i<s.length;i++){
            if(nname.indexOf(s[i])!=-1) {
		    	return false;
		    }else {
		      continue; 
		    }
         }
         return true;
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
   
   
  function isLaw() {
    if(document.all.law.value==1) {
       document.all.law.value=0;
    }else {
       document.all.law.value=1;
    }
  }
    function go(){
       //document.forms[0].action="register.do?page=adduser";
       var username = document.all.userName.value;
       send_request("POST","register.do?method=validateUser&userName="+username,null,"text", showCheck); 
   }
   
   function showCheck() { 
		if (http_request.readyState == 4) { // �ж϶���״̬
			if (http_request.status == 200) { // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
			    var text = http_request.responseText;
			    document.getElementById("message").innerText = http_request.responseText;
			    if(text.indexOf("�ѱ�")!=-1||text.indexOf("20��")!=-1) {
			       document.all.userName.value="";
			    }   
			}else{
				alert("���������ҳ�����쳣��");
			}
		}
}
 
 
</Script>
	<body>
		<jsp:include page="/top.jsp"></jsp:include>

		<center>
			<div id="foruminfo" style="width: 1003px">
				<div id="nav">
					<p>
						<a id="forumlist" href="index.jsp"><%=Snippet.BBS_NAME%></a>&raquo;��Աע��
					</p>
				</div>
			</div>
			<html:form action="register.do?method=register" enctype="multipart/form-data">
			<div class="mainbox forumbox" id="forumlist" style="width: 1003px;">
				<h1 align="left">
					&nbsp;&nbsp;�û�ע��
				</h1>
			
				<table cellpadding="0" cellspacing="0">
					<thead><tr><td>������Ϣ ( * Ϊ������)</td><td>&nbsp;</td></tr></thead>
					<tr>
						<td width="240"  height="30">
							&nbsp;&nbsp;*�û�����
						</td>
						<td width="763" >
							<input type="text" size="60%" name="userName" onBlur="go()"
								value=${addUserName}>&nbsp;&nbsp;&nbsp;
							<span style="color: #666666" id="message"></span>
						</td>
					</tr>
					<tr>
						<td  height="30">
							&nbsp;&nbsp;*�û��ǳƣ�
						</td>
						<td >
						    <input type="text" name="userNName" size="60%">
							<input type="hidden" name="userClass" value="1" size="60%">
							&nbsp;&nbsp;&nbsp;
							<span style="color: #666666" id="nname"></span>
						</td>
					</tr>
					<tr>
						<td  height="30">
							&nbsp;&nbsp;*���룺
						</td>
						<td >
							<input type="password" name="userPassword" size="60%">
							&nbsp;&nbsp;&nbsp;
							<span style="color: #666666" id="password"></span>
						</td>
					</tr>
					<tr>
						<td  height="30">
							&nbsp;&nbsp;*����ȷ�ϣ�
						</td>
						<td >
							<input type="password" name="confirmpassword" size="60%">
							&nbsp;&nbsp;&nbsp;
							<span style="color: #666666" id="cpassword"></span>
						</td>
						
					</tr>
					<tr>
						<td height="30">
							&nbsp;&nbsp;�ϴ�����ͷ��:
						</td>
						<td>
							<html:file property="image" size="60%" styleId="image" ></html:file>
							&nbsp;&nbsp;&nbsp;
								<span style="color: #666666" id="picture"></span>
						</td>
					</tr>
					<tr>
						<td height="30">
							&nbsp;&nbsp;*�Ա�
						</td>
						<td >
							<input type="radio" name="userSex" value="1" checked>
							<img src="images/default/boy.gif" width="24" height="24">
							��
							<input type="radio" name="userSex" value="0">
							<img src="images/default/girl.gif" width="26" height="26">
							Ů
						</td>
					</tr>
					<tr>
						<td  height="30">
							&nbsp;&nbsp;*�����ʼ���
						</td>
						<td>
							<input type="text" name="userEmail" size="60%">
							&nbsp;&nbsp;&nbsp;
							<span style="color: #666666">�磺jianfeng@163.com</span>
							&nbsp;&nbsp;&nbsp;
							<span style="color: #666666" id="email"></span>
						</td>
					</tr>
					<tr>
						<td  height="30" colspan="2" align="center">
							<input type="checkbox" name="law"  value ="1" onclick="isLaw()" size="60%"><a href="web/bbs/bbsLaw.jsp" target="_bank">�Ѿ��Ķ���ͬ�Ȿ��̳�����</a><br>
							<span style="color: #666666" id="lawMsg"></span>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" class="button" value="�ύ" onclick="return validate()">
							&nbsp;&nbsp;&nbsp;
							<input type="reset" class="button" value="���">
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

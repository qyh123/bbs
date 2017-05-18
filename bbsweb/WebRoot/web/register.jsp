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
		<title>注册</title>
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
  //验证添加用户和用户注册的表单
 function validate(){
      
	   if(document.getElementsByName("userName")[0].value=='') {
	      document.all.message.innerText="请输入合法的用户名";
		  document.getElementsByName("userName")[0].focus();
		  return false;
	   }
	   if(document.getElementsByName("userNName")[0].value.length>15) {
		  document.all.nname.innerText="昵称至多15个字符";
		  document.getElementsByName("userNName")[0].focus();
		  return false;
	   } 
	   if(document.getElementsByName("userNName")[0].value=='') {
		  document.all.nname.innerText="请输入合法的昵称";
		  document.getElementsByName("userNName")[0].focus();
		  return false;
	   }else {
	       var  nname =  document.getElementsByName("userNName")[0].value;
	       if(isChar(nname)==true){
	          document.all.nname.innerText="";
	       } else {
	          document.all.nname.innerText="昵称含有非法字符,不能含有 ',<,\",>,/,字符";
	          return false;
	       }	     
	   }
	   if(document.getElementsByName("userPassword")[0].value.length<6||document.getElementsByName("userPassword")[0].value.length>30) {
		  document.all.password.innerText="密码长度不小于6位不超过30位";
		  document.getElementsByName("userPassword")[0].focus();
		  return false;
	   }else {
	      document.all.password.innerText="";
	   }
	   if(document.getElementsByName("confirmpassword")[0].value!=document.getElementsByName("userPassword")[0].value) {
		  document.all.cpassword.innerText="密码不一致";
		  document.getElementsByName("confirmpassword")[0].focus();
		  return false;
	   } else {
	      document.all.password.innerText="";
	   }
	   if(document.getElementById("image").value!='') {
	        var fileName = document.getElementById("image").value;
	        if( isPicture(fileName) == false) {
	           document.all.picture.innerText="文件格式非法，只能是jpeg，jpg，bmp，png，gif格式文件";
	           return false;
	        }else {
	          document.all.picture.innerText="";
	        }
	   }
	     re=/^\w+@\w+(\.\w+)+$/;
	  if(re.test(document.getElementsByName("userEmail")[0].value)==false){
		  document.all.email.innerText="请输入有效的电子邮件地址";
		  document.getElementsByName("userEmail")[0].focus();
		  return false;
	   } else {
	       document.all.email.innerText="";
	   }
	   if(document.all.law.value==1) {
         document.all.lawMsg.innerText="同意本论坛条款后方可注册";
	     return false;
	   }else {
	      document.all.lawMsg.innerText="";
	   }
	   
}
//判断用户昵称的合法性
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
//判断用户是不是上传的图片
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
		if (http_request.readyState == 4) { // 判断对象状态
			if (http_request.status == 200) { // 信息已经成功返回，开始处理信息
			    var text = http_request.responseText;
			    document.getElementById("message").innerText = http_request.responseText;
			    if(text.indexOf("已被")!=-1||text.indexOf("20个")!=-1) {
			       document.all.userName.value="";
			    }   
			}else{
				alert("您所请求的页面有异常。");
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
						<a id="forumlist" href="index.jsp"><%=Snippet.BBS_NAME%></a>&raquo;会员注册
					</p>
				</div>
			</div>
			<html:form action="register.do?method=register" enctype="multipart/form-data">
			<div class="mainbox forumbox" id="forumlist" style="width: 1003px;">
				<h1 align="left">
					&nbsp;&nbsp;用户注册
				</h1>
			
				<table cellpadding="0" cellspacing="0">
					<thead><tr><td>基本信息 ( * 为必填项)</td><td>&nbsp;</td></tr></thead>
					<tr>
						<td width="240"  height="30">
							&nbsp;&nbsp;*用户名：
						</td>
						<td width="763" >
							<input type="text" size="60%" name="userName" onBlur="go()"
								value=${addUserName}>&nbsp;&nbsp;&nbsp;
							<span style="color: #666666" id="message"></span>
						</td>
					</tr>
					<tr>
						<td  height="30">
							&nbsp;&nbsp;*用户昵称：
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
							&nbsp;&nbsp;*密码：
						</td>
						<td >
							<input type="password" name="userPassword" size="60%">
							&nbsp;&nbsp;&nbsp;
							<span style="color: #666666" id="password"></span>
						</td>
					</tr>
					<tr>
						<td  height="30">
							&nbsp;&nbsp;*密码确认：
						</td>
						<td >
							<input type="password" name="confirmpassword" size="60%">
							&nbsp;&nbsp;&nbsp;
							<span style="color: #666666" id="cpassword"></span>
						</td>
						
					</tr>
					<tr>
						<td height="30">
							&nbsp;&nbsp;上传您的头像:
						</td>
						<td>
							<html:file property="image" size="60%" styleId="image" ></html:file>
							&nbsp;&nbsp;&nbsp;
								<span style="color: #666666" id="picture"></span>
						</td>
					</tr>
					<tr>
						<td height="30">
							&nbsp;&nbsp;*性别：
						</td>
						<td >
							<input type="radio" name="userSex" value="1" checked>
							<img src="images/default/boy.gif" width="24" height="24">
							男
							<input type="radio" name="userSex" value="0">
							<img src="images/default/girl.gif" width="26" height="26">
							女
						</td>
					</tr>
					<tr>
						<td  height="30">
							&nbsp;&nbsp;*电子邮件：
						</td>
						<td>
							<input type="text" name="userEmail" size="60%">
							&nbsp;&nbsp;&nbsp;
							<span style="color: #666666">如：jianfeng@163.com</span>
							&nbsp;&nbsp;&nbsp;
							<span style="color: #666666" id="email"></span>
						</td>
					</tr>
					<tr>
						<td  height="30" colspan="2" align="center">
							<input type="checkbox" name="law"  value ="1" onclick="isLaw()" size="60%"><a href="web/bbs/bbsLaw.jsp" target="_bank">已经阅读并同意本论坛的条款！</a><br>
							<span style="color: #666666" id="lawMsg"></span>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" class="button" value="提交" onclick="return validate()">
							&nbsp;&nbsp;&nbsp;
							<input type="reset" class="button" value="清空">
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

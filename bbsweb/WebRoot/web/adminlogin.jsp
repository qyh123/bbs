<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
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
		<title>后台登录</title>
		<base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<script type="text/javascript">
	
	</script>
		<link href="css/login.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<center>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<c:if test="${powerwrong==null }">
			<html:form action="adminlogin.do?flag=login">
				<table width="600" border="0" cellpadding="8" cellspacing="0"
					class="logintable">
					<tr class="loginheader">
						<td width="80"></td>
						<td width="100"></td>
						<td width="180" ></td>
						<td width="160"></td>
						<td width="80"></td>
					</tr>
					<tr style="height: 40px">
						<td>&nbsp;
							
						</td>
						<td class="line1">
							<span style="color: #ffff66; font-size: 14px; font-weight: bold;">系统设置</span>
						</td>
						<td class="line1">&nbsp;
							
						</td>
						<td class="line1">&nbsp;
							
						</td>
						<td>&nbsp;
							
						</td>
					</tr>

					<tr>
						<td>&nbsp;
							
						</td>
						<td class="line2">&nbsp;
							
						</td>
						<td class="line2">&nbsp;
							
						</td>
						<td class="line2">&nbsp;
							
						</td>
						<td>&nbsp;
							
						</td>
					</tr>
					<tr style="font-size: 12px">
						<td>&nbsp;
							
					  </td>
						<td align='right'>
							用户名:
						</td>
					  <td>
							<c:if test="${user==null}">
								<INPUT name="userName" type="text" size="24%" />
							</c:if>
					    ${user.userName}</td>
						<td><INPUT name="userName" type="hidden" size="24%" value="${user.userName}" />	<font color="#99FF00">${userwrong}</font>					</td>
						<td>&nbsp;
							
					  </td>
					</tr>
					<tr style="font-size: 12px">
						<td>&nbsp;
							
					  </td>
						<td align='right'>
							密 码:
						</td>
					  <td>
						<INPUT type="password" name="userPassword" size="26%" /></td>
						<td><font color="#99FF00">${passwordwrong}</font>						</td>
						<td>&nbsp;
							
					  </td>
					</tr>
					<tr>
						<td>&nbsp;
							
						</td>
						<td class='line1'>&nbsp;
							
						</td>
						<td class='line1' align='center'>
							<html:submit property="button" value="提交" style=""></html:submit>
							<html:button  property="tuichu" value="退出" onclick="javascript:location.href='/bbsweb/adminlogin.do?flag=out'" ></html:button>
									
						</td>
						
						<td class='line1'>&nbsp;
							
						</td>
						<td>&nbsp;
							
						</td>
					</tr>
					<tr>
						<td>&nbsp;
							
						</td>
						<td class="line2">&nbsp;
							
						</td>
						<td class="line2">&nbsp;
							
						</td>
						<td class="line2">&nbsp;
							
						</td>
						<td>&nbsp;
							
						</td>
					</tr>
					<tr style="font-size: 12px">
						<td colspan="5" align="center">
							  
				 
						</td>
					</tr>
					<tr>
						<td>&nbsp;
							
						</td>
						<td>&nbsp;
							
						</td>
						<td>&nbsp;
							
						</td>
						<td>&nbsp;
							
						</td>
						<td>&nbsp;
							
						</td>
					</tr>
				</table>
			</html:form>
</c:if>
<c:if test="${powerwrong!=null}">
<table width="600" border="0" cellpadding="8" cellspacing="0"
					class="logintable">
					<tr class="loginheader">
						<td width="80"></td>
						<td width="100"></td>
						<td width="220"></td>
						<td width="120"></td>
						<td width="80"></td>
					</tr>
					<tr style="height: 40px">
						<td>&nbsp;
							
						</td>
						<td class="line1">
							<span style="color: #ffff66; font-size: 14px; font-weight: bold;">系统设置</span>
						</td>
						<td class="line1">&nbsp;
							
						</td>
						<td class="line1">&nbsp;
							
						</td>
						<td>&nbsp;
							
						</td>
					</tr>

					<tr>
						<td>&nbsp;
							
						</td>
						<td class="line2">&nbsp;
							
						</td>
						<td class="line2">&nbsp;
							
						</td>
						<td class="line2">&nbsp;
							
						</td>
						<td>&nbsp;
							
						</td>
					</tr>
					<tr style="font-size: 12px">
						<td>&nbsp;
							
						</td>
						<td align='right'>
							
						</td>
						<td align='center'>
							
							${powerwrong}
						</td>
						<td>
						
							
						</td>
						<td>
							
						</td>
					</tr>
					<tr style="font-size: 12px">
						<td>&nbsp;
							
						</td>
						<td align='right'>
							
						</td>
						<td>
							<a href="web/adminlogin.jsp">返回后台管理登录页面重新登录！</a>
						</td>
						<td>&nbsp;
							
							
						</td>
						<td>&nbsp;
							
						</td>
					</tr>
					<tr>
						<td>&nbsp;
							
						</td>
						<td class='line1'>&nbsp;
							
						</td>
						<td class='line1' align='center'>
							
						</td>
						
						<td class='line1'>&nbsp;
							
						</td>
						<td>&nbsp;
							
						</td>
					</tr>
					<tr>
						<td>&nbsp;
							
						</td>
						<td class="line2">&nbsp;
							
						</td>
						<td class="line2">&nbsp;
							
						</td>
						<td class="line2">&nbsp;
							
						</td>
						<td>&nbsp;
							
						</td>
					</tr>
					<tr style="font-size: 12px">
						<td colspan="5" align="center">
							<!--  2009-2010-->版权所有&nbsp;&nbsp;&nbsp;&nbsp;沈阳市工商管理局<br/>
				 <!--  
				       通信地址：沈阳市沈河区南关路118号&nbsp;&nbsp;&nbsp;&nbsp;邮编：110016<br/>
				       电话：024-24011114&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;传真：024-24112911<br/>
				       辽ICP备05015137-->
						</td>
					</tr>
					<tr>
						<td>&nbsp;
							
						</td>
						<td>&nbsp;
							
						</td>
						<td>&nbsp;
							
						</td>
						<td>&nbsp;
							
						</td>
						<td>&nbsp;
							
						</td>
					</tr>
				</table></c:if>
		</center>
	</body>
</html>


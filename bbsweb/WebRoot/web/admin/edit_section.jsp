<%@ page language="java" pageEncoding="GBK" isELIgnored="false"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>修改版块信息</title>
		<link href="/bbsweb/css/admincp.css" type="text/css"
			rel="stylesheet">
		<script>
  	function validate(){
		var sName = document.all.sname.value;
		var sProfile = document.all.profile.value;
		if(sName==""){
			 document.all.message.innerText="版块名称为空";
			return false;
		}else if(sName.length>400){
		    document.all.message.innerText = "版块名称过长，请不要大于400字符";
		    return false;
		}else if(sProfile.length>800) {
		    document.all.message.innerText = "简介共计"+sProfile.length +"个字符，超过800字符";
		    return false;
		}
		else{
		 document.all.message.innerText="";
		 return true;
		}
	}
  </script>
	</head>
	<body>
		<table width="100%" border="0" cellpadding="2" cellspacing="6"
			style="margin-right: 5px">
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="90%" border="0" cellpadding="0" cellspacing="0"
			class="guide" style="margin-right: 5px">
			<tr>
				<td>
					<a href="web/admin/center.jsp" >系统后台管理</a>&nbsp;&raquo;&nbsp;修改版块
				</td>
			</tr>
		</table>
		<br />
		<form action="displaySection.do?method=edit" name="edit" method="post" onsubmit="return validate();">
			<input name="sid" type="hidden" value="${section.sectionId}">
			<table width="90%" cellpadding="0" cellspacing="0"
				class="tableborder" style="margin-right: 5px">
				<tr class="header">
					<td colspan="2">
						版块基本设置
					</td>
				</tr>
				<tbody id="menu_846ed2c056ad9a1f">
					<tr>
						<td width="44%" height="35" class="altbg1">
							版块名称
						</td>
						<td width="56%" class="altbg2">
							<input type="text" name="sname" size="60" maxlength="400" value="${section.sectionName}" />
						</td>
					</tr>

					<tr>
						<td height="35" class="altbg1">
							版主名
						</td>
						<td height="35" class="altbg2">
							<select name="mid">
								<c:if test="${sectionMaster.userName!=null}">
									<option value="${section.sectionMasterID}" selected>
										${sectionMaster.userName}
									</option>
								</c:if>
								<option value="0" >
										暂无版主
								</option>
								<logic:present name="masterList">
									<logic:iterate id="masterList" name="masterList">
										<option value="${masterList.userId}">
											${masterList.userName}
										</option>
									</logic:iterate>
								</logic:present>
							</select>
						</td>
					</tr>
					<tr>
						<td height="35" class="altbg1">
							版面简介
						</td>
						<td height="35" class="altbg2" >
							<textarea rows="15" cols="62" name="profile">${section.sectionProfile }</textarea>
							<br>
							<span id="message" style="color:#FF0000"></span></td>
					</tr>
				</tbody>
			</table>
			<br />
			<div align="center">
				<button class="button" type="submit">
					提交
				</button>
				&nbsp;&nbsp;
				<button onClick="javascrit:document.edit.reset()" class="button" />
					重置
				</button>
			</div>
		</form>
		<br />
		<br />
		<jsp:include page="/web/admin/foot.jsp"></jsp:include>
	</body>
</html>
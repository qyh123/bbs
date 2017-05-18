<%@ page language="java" pageEncoding="GBK" isELIgnored="false"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>�޸İ����Ϣ</title>
		<link href="/bbsweb/css/admincp.css" type="text/css"
			rel="stylesheet">
		<script>
  	function validate(){
		var sName = document.all.sname.value;
		var sProfile = document.all.profile.value;
		if(sName==""){
			 document.all.message.innerText="�������Ϊ��";
			return false;
		}else if(sName.length>400){
		    document.all.message.innerText = "������ƹ������벻Ҫ����400�ַ�";
		    return false;
		}else if(sProfile.length>800) {
		    document.all.message.innerText = "��鹲��"+sProfile.length +"���ַ�������800�ַ�";
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
					<a href="web/admin/center.jsp" >ϵͳ��̨����</a>&nbsp;&raquo;&nbsp;�޸İ��
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
						����������
					</td>
				</tr>
				<tbody id="menu_846ed2c056ad9a1f">
					<tr>
						<td width="44%" height="35" class="altbg1">
							�������
						</td>
						<td width="56%" class="altbg2">
							<input type="text" name="sname" size="60" maxlength="400" value="${section.sectionName}" />
						</td>
					</tr>

					<tr>
						<td height="35" class="altbg1">
							������
						</td>
						<td height="35" class="altbg2">
							<select name="mid">
								<c:if test="${sectionMaster.userName!=null}">
									<option value="${section.sectionMasterID}" selected>
										${sectionMaster.userName}
									</option>
								</c:if>
								<option value="0" >
										���ް���
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
							������
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
					�ύ
				</button>
				&nbsp;&nbsp;
				<button onClick="javascrit:document.edit.reset()" class="button" />
					����
				</button>
			</div>
		</form>
		<br />
		<br />
		<jsp:include page="/web/admin/foot.jsp"></jsp:include>
	</body>
</html>
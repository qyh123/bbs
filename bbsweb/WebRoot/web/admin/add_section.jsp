<%@ page language="java" pageEncoding="GBK"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html>
	<head>
		<title>添加版块</title>
		<link href="/bbsweb/css/admincp.css" type="text/css"
			rel="stylesheet">
		<script>
	function checked(){
		var sName=document.add.SectionName.value;
		var sid=document.add.SectionMasterID.value;
		var sProfile = document.all.sectionProfile.value;
		if(sName==""){
			 document.all.message.innerTect="版块名称为空";
			return false;
		}
		else if(sid==""|| isNaN(sid)){
			 document.all.message.innerTect="版主ID为空或不是一个数字";
			return false;
		}else if(sName.length>400){
		    document.all.message.innerTect = "版块名称过长，请不要超过400字符";
		}else if(sPorfile.length>800) {
		    document.all.message.innerTect = "简介共计"+sPorfile.length +"版块简介超过800字符";
		}
		else{
		 document.all.message.innerTect="";
		 return true;
		}
	}
</script>
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
						<a href="web/admin/center.jsp">系统后台管理</a>&nbsp;&raquo;&nbsp;添加版块
					</td>
				</tr>
			</table>
			<br />
			<html:form  method="post" action="addSection.do">
				<table width="100%" cellpadding="0" cellspacing="0"
					class="tableborder">
					<tr class="header">
						<td colspan="2">
							添加新版块
						</td>
					</tr>

					<tr>
						<td width="16%" class="altbg1">
							版块名称
						</td>
						<td width="84%" class="altbg2">
							<input type="text" name="sectionName" size ="60" maxlength="400"/>
						</td>
					</tr>
					<tr>
						<td class="altbg1">
							版面简介
						</td>
						<td class="altbg2">
							<textarea name="sectionProfile" cols="62" rows="15"></textarea>
						</td>
					</tr>
					<tr>
						<td class="altbg1">
							设为隐藏状态？
						</td>
						<td class="altbg2">
							<input name="sectionState" type="radio" value="1" class="checkbox" />
							是
							<input name="sectionState" type="radio" value="0" checked="checked"
								class="checkbox" />
							否
						</td>
					</tr>
					<tr>
					<span id="message" style="color:#FF0000"></span>
					</tr>
				</table>
				
				<div align="center">
					<input type="submit" name="Submit" value="提交" class="button" onClick="return checked()"/>
					<input name="reset" type="reset" value="重置" class="button" />
				</div>
			</html:form>
			<br />
			<br />
			<jsp:include page="/web/admin/foot.jsp"></jsp:include>
		</center>
</body>
</html>

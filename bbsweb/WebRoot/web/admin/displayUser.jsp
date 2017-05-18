<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="snippet.Snippet" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("GBK");
	response.setCharacterEncoding("GBK");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title></title>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<meta http-equiv="Content-Language" content="zh-cn">
		<script src="js/middle.js" type="text/javascript"></script>
		<title><%=Snippet.BBS_NAME%>后台管理</title>
		<link href="css/admincp.css" type="text/css" rel="stylesheet">
		<script language="javascript" type="text/javascript" charset="utf-8">



function oprateUser(str) {
    if(str.indexOf("delete")!=-1) {
		var url= "delete";
		var info ="删除";
    } else if(str.indexOf("setUser")!=-1){
        var url="setUser";
        var info = "设为版主";
    } else{
        var url="setManage";
        var info = "设为管理员";
    }
	if (getSelectedCheckBoxNumber()==0) {
			alert("请选择需要"+info+"的记录！");
			return false;
	}  else {
			if (confirm("你确定要将所选记录"+info+"吗！")) {
				var checkbutton = document.getElementsByName('chkButton');
				var resultstr = "";
				for(var i=1; i<checkbutton.length; i++) {
					if(document.all.chkButton[i].checked){
						resultstr = resultstr+","+document.all.chkButton[i].value;
					}
				}
				if(resultstr != ""){
					resultstr = resultstr.substring(1);
				}
		     	location.href= "displayUser.do?method="+url+"&id="+resultstr;
				
			}
  }
}

function gopage(selectpage){
 var valu=selectpage.value.replace(/[ ]/g,"");
 var patrn=/^[0-9]*[1-9][0-9]*$/;
	if(valu==""){
		alert("请输入页数");
		selectpage.value="";
		return ;
	}else if(!patrn.exec(valu)){
		alert("请输入正整数");
		selectpage.value="";
		return ;
	}else if(${pageSize}<valu){
		selectpage.value=${pageSize};
		location.href="displayUser.do?method=show&cp=${pageSize}";
	} else {
      location.href="displayUser.do?method=show&cp="+valu;
    }
}
</script>
	</head>
	<BODY>
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
						<a href="web/admin/center.jsp">系统后台管理</a>&nbsp;&raquo;&nbsp;用户管理
					</td>
				</tr>
			</table>
			<br />
			<form action="displayUser.do?method=show" method="post">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="tableborder">
					<tr class="header">
						<td colspan="3">
							用户管理-搜索用户
						</td>
					</tr>
					<tr class="altbg1">
						<td>
							用户名
						</td>
						<td>
							<input type="text" name="title"
								value="${title== null ?'': title }" style="width: 100px;">
						</td>
						<td>
							<input class="button" type="submit" name="searchsubmit"
								value="提交">
						</td>
					</tr>
				</table>
			</form>
			<c:if test="${pageSize!=1 && allRecorders!=0}">
				<table align="center" width="100%">
					<tr>
						<td align="left" width="5%">
							<div class="pages">
								<em>共有${allRecorders}条记录&nbsp;
									第${currentPage}页&nbsp;共${pageSize}页</em>
								<a href="displayUser.do?method=show&cp=1" class="prev">首页</a>
								<c:if test="${currentPage!=1}">
									<a href="displayUser.do?method=show&cp=${currentPage-1}"
										class="prev">上一页</a>
								</c:if>
								<c:if test="${currentPage!=pageSize}">
									<a href="displayUser.do?method=show&cp=${currentPage+1}"
										class="next"> 下一页</a>
								</c:if>
								<a href="displayUser.do?method=show&cp=${pageSize}" class="prev">尾页</a>
								<a href="//" class="next" onclick="gopage(selectedCurPage)"
									class="next">Go</a>
								<input type="text" name="selectedCurPage" size="5" maxlength="5" />
								页
							</div>
						</td>
					</tr>
				</table>
			</c:if>

			<br>
			<table width="100%" cellpadding="0" cellspacing="0"
				class="tableborder">
				<tr class="header">
					<td align="center" height="28">
						<label for="rdo_1">
							<input type="hidden" name="userSize" value="0" />
							<input type="checkbox" name="chkButton" value="0"
								class="checkbox" onClick="checkAll();" id="rdo_1" />
							全选
						</label>
					</td>
					<td align="center">
						用户名
					</td>
					<td align="center">
						昵称
					</td>
					<td align="center">
						性别
					</td>
					<td align="center">
						邮件
					</td>
					<td align="center">
						注册日期
					</td>
					<td align="center">
						级别
					</td>
					<td align="center">
						用户状态
					</td>
					<td align="center">
						积分
					</td>
				</tr>
				<c:if test="${allRecorders>0}">
					<logic:present name="array">
						<logic:iterate id="array" name="array">
							<tr>
								<td align="center" height="25" class="altbg1">
									<input type="checkbox" name="chkButton" class="checkbox"
										value="${array.userId}" />
								</td>
								<td align="center" class="altbg2">
									${array.userName}
								</td>
								<td align="center" class="altbg1">
									${array.userNName}
								</td>
								<td align="center" class="altbg2">
									${array.userSexName}
								</td>
								<td align="center" class="altbg1">
									${array.userEmail}
								</td>
								<td align="center" class="altbg2">
									${array.userRegDate}
								</td>
								<td align="center" class="altbg1">
									${array.userClassName}
								</td>
								<td align="center" class="altbg2">
									<c:if test="${array.bunState==1}">
									   正常状态
									</c:if>
									<c:if test="${array.bunState==2}">
									   禁止发言
									</c:if>
									<c:if test="${array.bunState==3}">
									   禁止访问
									</c:if>
								</td>
								<td align="center" class="altbg1">
									${array.userPoint}
								</td>

							</tr>
						</logic:iterate>
					</logic:present>
				</c:if>
				<c:if test="${allRecorders<=0}">
					<tr height="25" align="center">
						<td class="altbg2" colspan="9">
							您没有提供搜索条件或没有与条件匹配的用户。
						</td>
					</tr>
				</c:if>
			</table>
			<center>
				<input type="button" class="button" onClick="oprateUser('delete')"
					value="删除">
				<input type="button" class="button" onClick="oprateUser('setUser')"
					value="设为版主">
				<input type="button" class="button"
					onClick="oprateUser('setManage')" value="设为管理员">
					<br>
			   <br>
			</center>
			<jsp:include page="/web/admin/foot.jsp"></jsp:include>
		</center>
	</BODY>
</html>
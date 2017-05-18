<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<meta http-equiv="Expires" content="0" />
		<meta http-equiv="Content-Language" content="zh-cn">
		<script src="js/middle.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/ajax_func.js"></script>
		<link href="css/admincp.css" type="text/css" rel="stylesheet">
		<script language="javascript" type="text/javascript" charset="utf-8">

function findPassword(str,resultstr) { 
    if(str.indexOf("delete")!=-1){
         location.href  = "findPasswordManage.do?method=delete&passwordId="+resultstr;
    } else {
         send_request("POST","findPasswordManage.do?method=reset&passwordId="+resultstr,null,"text", showCheck); 
    }
}
function showCheck() { 
		if (http_request.readyState == 4) { // 判断对象状态
			if (http_request.status == 200) { // 信息已经成功返回，开始处理信息
			       var op = http_request.responseText;
				   document.getElementById("opration"+op).innerHTML="<input type='button' class='button' value=' 发送通知' onclick=findPassword('delete',"+op+")>";
                    
			}else{
				alert("您所请求的页面有异常。");
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
						<a href="web/admin/center.jsp">系统后台管理</a>&nbsp;&raquo;&nbsp;找回密码管理
					</td>
				</tr>
			</table>
			<br />
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="tableborder">
				<tr class="header">
					<td>
						<div style="float: left; margin-left: 0px; padding-top: 8px">
							<a href="///" onclick="display1(menutip,menuimg)">技巧提示</a>
						</div>
						<div style="float: right; margin-right: 4px; padding-bottom: 2px">
							<a href="///" onclick="display1(menutip,menuimg)"> <img
									id="menuimg" src="images/admincp/menu_reduce.gif" border="0" />
							</a>
						</div>
					</td>
				</tr>
				<tbody id="menutip" style="display: ">
					<tr>
						<td>
							<ul>
								<li>
									此功能是对用户找回密码进行管理。方法如下：
								</li>
								<li>
									第一步先重设密码
								</li>
								<li>
									第二步向用户发送通知
								</li>
								<li>
									记住用户的email信息，然后通过互联网发送初始密码。初始密码为123456
								</li>
								<li>
									<font color="red">注意：一定要按照步骤执行！</font>
								</li>
							</ul>
						</td>
					</tr>
				</tbody>
			</table>

			<br />
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
								<a href="##" class="next" onclick="gopage(selectedCurPage)"
									class="next">Go</a>
								<input type="text" name="selectedCurPage" size="5" maxlength="5" />
								页
							</div>
						</td>
					</tr>
				</table>
			</c:if>

			<input type="hidden" name="id" value="" />
			<br>
			<table width="100%" cellpadding="0" cellspacing="0"
				class="tableborder">
				<tr class="header">
					<td align="center">
						用户名
					</td>
					<td align="center">
						邮件
					</td>
					<td align="center">
						找回日期
					</td>
					<td align="center">
						编辑
					</td>
				</tr>
				<c:if test="${allRecorders>0}">
					<logic:present name="array">
						<logic:iterate id="array" name="array">
							<tr>
								<td align="center" class="altbg1">
									${array.userName}
								</td>
								<td align="center" class="altbg2">
									${array.userEmail}
								</td>
								<td align="center" class="altbg1">
									${array.findTime}
								</td>
								<td align="center" class="altbg2">
									<div id="opration${array.passwordId}">
										<input type="button" class="button" value=" 重设密码"
											onclick="findPassword('setPassword',${array.passwordId})">
									</div>
								</td>
							</tr>
						</logic:iterate>
					</logic:present>
				</c:if>
				<c:if test="${allRecorders==0}">
					<tr>
						<td  colspan="5" height="25" align="center">
							没有用户找回密码！
						</td>
					</tr>
				</c:if>
			</table>
			<br />
			<br />
			<jsp:include page="/web/admin/foot.jsp"></jsp:include>
		</center>
	</BODY>
</html>
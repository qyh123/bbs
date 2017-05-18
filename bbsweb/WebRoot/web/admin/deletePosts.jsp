<%@ page contentType="text/html;charset=gb2312"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="snippet.Snippet" %>
 
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
		<meta http-equiv="Content-Type" content="text/html;charset=gb2312">
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<meta http-equiv="Content-Language" content="zh-cn">
		<script src="js/middle.js" type="text/javascript"></script>
		<title><%=Snippet.BBS_NAME%>后台管理</title>
		<link href="css/admincp.css" type="text/css"
			rel="stylesheet">
		<script language="javascript" type="text/javascript" charset="gb2312">

function delposts() {
    var resultstr = "";
	if (getSelectedCheckBoxNumber==0) {
		alert('请选择需要删除的记录！');
		return false;
	}  else {
		if (confirm('你确定要删除所选记录吗！')) {
			var checkbutton = document.getElementsByName('chkButton');
			for(var i=1; i<checkbutton.length; i++) {
				if(document.all.chkButton[i].checked){
					resultstr = resultstr+","+document.all.chkButton[i].value;
				    
				}
			}
			if(resultstr != ""){
				resultstr = resultstr.substring(1);
			}
			//document.page.id.value = resultstr;
			var resultState = document.all.resultState.value;
			document.page.action = "deletePosts.do?method=delete&resultState="+resultState+"&id="+resultstr;
			document.page.submit();
		}
	}
			
}

function check(chk){ 
   if(chk==0) {
      document.all.detail.value="1";
      document.posts.resultState.value="2";
   } else {
      document.all.detail.value="0"; 
     document.posts.resultState.value="1";
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
		location.href="deletePosts.do?method=show&resultState=2&author=${form.author}&postsContent=${form.postsContent}&section=${form.section}&cp=${pageSize}";
	} else {
      location.href="deletePosts.do?method=show&resultState=2&author=${form.author}&postsContent=${form.postsContent}&section=${form.section}&cp="+valu;
    }
}
function toPage(cp){
  document.location.href="deletePosts.do?method=show&resultState=2&author=${form.author}&postsContent=${form.postsContent}&section=${form.section}&cp=" + cp;
}
</script>
	</head>
	<BODY>
	<center>
		<%
			//session.setAttribute("postslist",request.getAttribute("array"));
		%>
		<table width="100%" border="0" cellpadding="2" cellspacing="6">
			<tr>
				<td></td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="guide">
			<tr>
				<td>
					<a href="web/admin/center.jsp" >系统后台管理</a>&nbsp;&raquo;&nbsp;批量删帖
				</td>
			</tr>
		</table>
		<br />
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="tableborder">
			<tr class="header">
				<td>
					<div style="float: left; margin-left: 0px; padding-top: 8px">
						<a href="javascript:display1(menutip,menuimg)">技巧提示</a>
					</div>
					<div style="float: right; margin-right: 4px; padding-bottom: 2px">
						<a href="javascript:display1(menutip,menuimg)"><img
								id="menuimg" src="images/admincp/menu_reduce.gif"
								border="0" /> </a>
					</div>
				</td>
			</tr>
			<tbody id="menutip" style="display: ">
				<tr>
					<td>
						<ul>
							<li>
								批量删帖仅用于删除违规帖子使用，如您需要批量删除历史主题，请使用批量主题管理功能。
						</ul>
					</td>
				</tr>
			</tbody>
		</table>
		<br />

		<form action="deletePosts.do?method=show" method="post" name="posts">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="tableborder">
				<tr class="header">
					<td colspan="2">
						搜索符合条件主题
					</td>
				</tr>
				<tr>
					<td width="45%" class="altbg1">
						显示详细主题列表:
					</td>
					<td width="55%" class="altbg2">
						<input class="checkbox" type="checkbox" name="detail" value="1"
							onClick="check(this.value)" checked />
					</td>
				</tr>
				<tr>
					<td class="altbg1">
						所在版块:
					</td>
					<td class="altbg2">
						<input type="text" name="section"
							value="${form.section== null ?'': form.section}">
					</td>
				</tr>
				<tr>
					<td class="altbg1">
						主题作者:
					</td>
					<td class="altbg2">
						<input type="text" name="author" size="40"
							value="${form.author== null ?'' : form.author }">
						<input type="hidden" name="resultState" value="2">
					</td>
				</tr>
				<tr>
					<td class="altbg1">
						内容关键字:
					</td>
					<td class="altbg2">
						<input type="text" name="postsContent" size="40"
							value="${form.postsContent== null ?'': form.postsContent}">
					</td>
				</tr>
			</table>
			<center>
				<input class="button" type="submit" name="searchsubmit" value="提 交">
			</center>
		</form>
		<c:if test="${resultState!=0}">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="tableborder">
				<tr class="header">
					<td colspan="2">
						符合条件的主题数:${allRecorders}
					</td>
				</tr>
				<tr>
					<td class="altbg1">
						批量删除
					</td>
			</table>
			<c:if test="${resultState==2}">
				<c:if test="${pageSize!=1 && allRecorders!=0}">
					<table align="center" width="100%">
						<tr>
							<td align="left" width="5%">
								<div class="pages">
									<c:if test="${allRecorders!=0}">
										<em>第${currentPage}页共${pageSize}页</em>
										<a href="javascript:toPage(1)" class="next"> 首页</a>
										<c:if test="${currentPage!=1}">
											<a href="javascript:toPage('${currentPage-1}')"
												class="prev">上一页</a>
										</c:if>
										<c:if test="${currentPage!=pageSize}">
											<a href="javascript:toPage('${currentPage+1}')"
												class="next"> 下一页</a>
										</c:if>
										<a href="javascript:toPage('${pageSize}')" class="next">
											尾页</a>
										<a href="javascript:gopage(selectedCurPage)"class="next">Go</a>
										<input type="text" name="selectedCurPage" size="5"
											maxlength="5" />页
				  </c:if>
								</div>
							</td>
						</tr>
					</table>
				</c:if>
			</c:if>
			<c:if test="${resultState==2}">
				<form action="deletePosts.do?method=delete" method="post"
					name="page">
					<input type="hidden" name="postsSize" value="" />
					<br />
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="tableborder">
						<tr class="header">
							<td width="9%" align="center" height="28">
								<label for="rdo_1" style="vertical-align: top">
									<input type="checkbox" name="chkButton" value="1"
										class="checkbox" onclick="checkAll();" id="rdo_1"
										checked="checked" />
									全选
								</label>
							</td>
							<td width="14%" align="center">
								内容
							</td>
							<td width="11%" align="center">
								版块
							</td>
							<td width="22%" align="center">
								作者
							</td>
							<td width="7%" align="center">
								时间
							</td>
						</tr>
						<c:if test="${allRecorders>0}">
							<logic:present name="array">
								<logic:iterate id="array" name="array" indexId="index">
									<tr>
										<td align="center" class="altbg1">
											<input type="checkbox" name="chkButton" class="checkbox"
												value="${pageScope.index}" checked="checked" />
										</td>
										<td align="center" class="altbg2">
											<a
												href="show.do?method=show&topicid=${array.topicId}&author=${array.author}"
												target="_bank">${array.postsContent}</a>
										</td>
										<td align="center" class="altbg1">
											${array.sectionName}
										</td>
										<td align="center" class="altbg2">
											${array.userName }
										</td>
										<td align="center" class="altbg1">
											${array.postsTime }
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</c:if>
						<c:if test="${allRecorders<=0}">
							<tr height="25" align="center" bgcolor="#E4EAEE">
								<td class="altbg2" colspan="7">
									您没有提供搜索条件或没有与条件匹配的主题。
								</td>
							</tr>
						</c:if>
					</table>
					<center>
						<input class="button" type="button" name="deletesubmit"
							value="提 交" onclick="delposts()">
					</center>
				</form>
			</c:if>
		</c:if>
		<br />
		<br />
			<jsp:include page="/web/admin/foot.jsp"></jsp:include>
		</center>
	</BODY>
</html>
<%@ page contentType="text/html;charset=gb2312"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
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
		<title><%=Snippet.BBS_NAME%>后台管理</title>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<meta http-equiv="Content-Language" content="zh-cn">
		<link href="css/admincp.css" type="text/css" rel="stylesheet">
		<script src="js/middle.js" type="text/javascript"></script>		
<script language="javascript" type="text/javascript" charset="gb2312">
function check(chk){ 
 if(chk==0) {
   document.all.detail.value="1";
   document.searchTopic.resultState.value="2";
   document.page.resultState.value="2";
 } else {
   document.all.detail.value="0";
   document.searchTopic.resultState.value="1";
   document.page.resultState.value="1";
 }
}

function oprateTopic(str) {
    if(str.indexOf("state")!=-1) {
        var url= "state";
		var info ="精华";
    } else if(str.indexOf("delete")!=-1) {
		var url= "delete";
		var info ="删除";
    } else  {
        var url="top";
        var info = "置顶";
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
				var resultState = document.searchTopic.resultState.value;
		     	location.href= "displayTopic.do?method="+url+"&id="+resultstr+"&resultState="+resultState;
				
			}
  }
}

function gopage(selectpage){
 var valu=selectpage.value.replace(/[ ]/g,"");
 var state =  document.all.resultState
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
		document.searchTopic.action="displayTopic.do?method=show&cp=${pageSize}";
		document.searchTopic.submit();
	} else {
      document.searchTopic.action="displayTopic.do?method=show&cp="+valu;
      document.searchTopic.submit();
    }
}

function toPage(page){
  document.searchTopic.action="displayTopic.do?method=show&cp="+page;
  document.searchTopic.submit();
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
						<a href="web/admin/center.jsp">系统后台管理</a>&nbsp;&raquo;&nbsp;批量主题管理
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
									批量主题管理仅用于各版块的主题帖子进行管理，如您需要批量删除所有帖子，请使用批量删帖功能。
							</ul>
						</td>
					</tr>
				</tbody>
			</table>
			<br />

			<form action="displayTopic.do?method=show" method="post"
				name="searchTopic">
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
							<input type="text" name="username" size="40"
								value="${form.username== null ?'': form.username}">
							<input type="hidden" name="resultState" value="2">
						</td>
					</tr>
					<tr>
						<td class="altbg1">
							标题关键字:
						</td>
						<td class="altbg2">
							<input type="text" name="title" size="40"
								value="${form.title== null ?'': form.title}">
						</td>
					</tr>
				</table>
				<center>
					<input class="button" type="submit" value="提 交">
				</center>
			</form>
			<c:if test="${resultState!=0}">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="tableborder">
					<tr class="header">
						<td>
							符合条件的主题数:${allRecorders }
						</td>
					</tr>
					<tr>
						<td class="altbg1">
							<input class="radio" type="radio" name="opration" value="0"
								onClick="oprateTopic('delete')">
							批量删除
						</td>

					</tr>
					<tr>
						<td class="altbg1">
							<input class="radio" type="radio" name="opration" value="1"
								onClick="oprateTopic('top')">
							批量置顶
						</td>
					</tr>
					<tr>
						<td class="altbg1">
							<input class="radio" type="radio" name="opration" value="2"
								onClick="oprateTopic('state')">
							批量设置精华
						</td>
					</tr>
				</table>
				<form action="displayTopic.do?method=top" method="post" name="page">
					<input type="hidden" name="id" value="" />
					<input type="hidden" name="resultState" value="2">
				</form>
				<c:if test="${resultState==2}">
					<c:if test="${pageSize!=1 && allRecorders!=0}">
						<table align="center" width="100%">
							<tr>
								<td align="left" width="5%" rowspan="2">
									<div class="pages">
										<c:if test="${allRecorders!=0}">
											<em>第${currentPage}页共${pageSize}页</em>
											<a href="javascript:toPage('1')" class="next">首页</a>
											<c:if test="${currentPage!=1}">
												<a href="javascript:toPage('${currentPage-1}')"
													class="prev">上一页</a>
											</c:if>
											<c:if test="${currentPage!=pageSize}">
												<a href="javascript:toPage('${currentPage+1}')"
													class="next">下一页</a>
											</c:if>
											<a href="javascript:toPage(${pageSize})" class="next">尾页</a>
											<a href="javascript:gopage(selectedCurPage)" class="next">Go</a>

											<input type="text" size="5" name="selectedCurPage" maxlength="5">页
					        </c:if>
									</div>
								</td>
							</tr>
						</table>
					</c:if>

					<br />
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="tableborder">
						<tr class="header">
							<td width="9%" align="center" height="28">
								<label for="rdo_1" style="vertical-align: top">
									<input type="checkbox" name="chkButton" value="0"
										class="checkbox" onclick="checkAll();" id="rdo_1"
										 />
									全选
								</label>
							</td>
							<td width="25%" align="center">
								标题
							</td>
							<td width="16%" align="center">
								版块
							</td>
							<td width="14%" align="center">
								作者
							</td>
							<td width="5%" align="center">
								回复
							</td>
							<td width="19%" align="center">
								发帖时间
							</td>
							<td width="6%" align="center">
								精华
							</td>
							<td width="6%" align="center">
								置顶
							</td>
						</tr>
						<c:if test="${allRecorders>0}">
							<logic:present name="array">
								<logic:iterate id="array" name="array">
									<tr>
										<td align="center" class="altbg1">
											<input type="checkbox" name="chkButton" class="checkbox"
												value="${array.topicId}"  />
										</td>
										<td align="center" class="altbg2">
											<a
												href="show.do?method=show&topicid=${array.topicId}&author=${array.userName}"
												target="_bank">${array.topicTopic}</a>
										</td>
										<td align="center" class="altbg1">
											${array.sectionName}
										</td>
										<td align="center" class="altbg2">
											${array.userName}
										</td>
										<td align="center" class="altbg1">
											${array.topicReplyCount}
										</td>
										<td align="center" class="altbg2">
											${array.topicTime}
										</td>
										<td align="center" class="altbg1">
											<c:if test="${array.topicState==1}">精华</c:if>
											<c:if test="${array.topicState==0}">非精华</c:if>
										</td>
										<td align="center" class="altbg2">
											<c:if test="${array.topTime!=null}">置顶</c:if>
											<c:if test="${array.topTime==null}">非置顶</c:if>
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</c:if>
						<c:if test="${allRecorders<=0}">
							<tr height="25" align="center" bgcolor="#E4EAEE">
								<td class="altbg2" colspan="8">
									您没有提供搜索条件或没有与条件匹配的主题。
								</td>
							</tr>
						</c:if>
					</table>
				</c:if>

			</c:if>

			<br />
			<br />
			<jsp:include page="/web/admin/foot.jsp"></jsp:include>	
		</center>
	</BODY>
</html>
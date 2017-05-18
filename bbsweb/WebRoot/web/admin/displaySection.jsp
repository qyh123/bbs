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
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<meta http-equiv="Content-Language" content="zh-cn">
		<%
			int count = 0;//记录数
		%>
		<script src="js/middle.js" type="text/javascript"></script>
		<title><%=Snippet.BBS_NAME%>后台管理</title>
		<link href="css/admincp.css" type="text/css" rel="stylesheet">
		<script language="javascript" type="text/javascript">

function oprateSection(str) {
    if(str.indexOf("state")!=-1) {
        var url= "state";
		var info ="隐藏";
    } else if(str.indexOf("delete")!=-1) {
		var url= "delete";
		var info  ="删除";
    //} else if (str.indexOf("top")!=-1){
      //  var url="top";
       // var info = "置顶";
   // }
    } else if(str.indexOf("edit")!=-1) {
		var url= "viewName";
		var info ="修改";
    }
	if (getSelectedCheckBoxNumber()==0) {
			alert("请选择需要"+info+"的记录！");
			return false;
	} else {
	      var checkbutton = document.getElementsByName('chkButton');
	      if(url.indexOf("viewName")!=-1&&getSelectedCheckBoxNumber()>1){
	          alert("请选择一条记录进行修改");
	      }else if(url.indexOf("viewName")!=-1&&getSelectedCheckBoxNumber()==1&&document.all.chkButton[0].checked==true) {
	         alert("请选择一条记录进行修改");
	      }
	      else{
			 if (confirm("你确定要将所选记录"+info+"吗！")) {
				var resultstr = "";
				for(var i=1; i<checkbutton.length; i++) {
					if(document.all.chkButton[i].checked){
						resultstr = resultstr+","+document.all.chkButton[i].value;
					}
				}
				if(resultstr != ""){
					resultstr = resultstr.substring(1);
				}
		     	location.href= "displaySection.do?method="+url+"&id="+resultstr;
			}	
		}
  }
}
function edit(id) {
  location.href= "displaySection.do?method=viewName&id="+id;
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
		location.href="displaySection.do?method=show&cp=${pageSize}";
	} else {
      location.href="displaySection.do?method=show&cp="+valu;
    }
}
</script>
	</head>
	<BODY>
		<center>
			<div id="title">
				<table border="0" cellpadding="2" cellspacing="6">
					<tr>
						<td></td>
					</tr>
				</table>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="guide">
					<tr>
						<td>
							<a href="web/admin/center.jsp">系统后台管理</a>&nbsp;&raquo;&nbsp;查看版块
						</td>
					</tr>
				</table>
				<br />

				<table align="center" width="100%">
					<tr>
						<td align="left" width="5%">
							<c:if test="${pageSize!=1}">
								<div class="pages">
									<c:if test="${allRecorders!=0}">
										<em>共有${allRecorders}条记录&nbsp;
											第${currentPage}页&nbsp;共${pageSize}页</em>
										<a href="displaySection.do?method=show&cp=1" class="next">首页</a>
										<c:if test="${currentPage!=1}">
											<a href="displaySection.do?method=show&cp=${currentPage-1}"
												class="prev">上一页</a>
										</c:if>
										<c:if test="${currentPage!=pageSize}">
											<a href="displaySection.do?method=show&cp=${currentPage+1}"
												class="next">下一页</a>
										</c:if>
										<a href="displaySection.do?method=show&cp=${pageSize}"
											class="next">尾页</a>
										<a href="///" class="next" onclick="gopage(selectedCurPage)"
											class="next">Go</a>
										<input type="text" name="selectedCurPage" size="5"
											maxlength="5" />页  
				  </c:if>
								</div>
							</c:if>
						</td>
					</tr>
				</table>
				<form action="displaySection.do?method=show" method="post"
					name="page">
					<input type="hidden" name="studentSize" value="<%=count%>" />
					<input type="hidden" name="id" value="" />
					<table width="100%" cellpadding="0" cellspacing="0"
						class="tableborder">
						<tr class="header">
							<td width="7%" height="28" align="center">
								<label for="rdo_1">
									<input type="checkbox" name="chkButton" value="0"
										onclick="checkAll();" id="rdo_1" class="checkbox" />
									全选
								</label>
							</td>
							<td width="29%" align="center">
								版块名称
							</td>
							<td width="25%" align="center">
								版主
							</td>
							<td width="14%" align="center">
								是否隐藏
							</td>
							<!-- 
							<td width="12%" align="center">
								是否置顶
							</td>
							
							<td width="13%" align="center">
								操作
							</td> -->
						</tr>
						<c:if test="${allRecorders>0}">
							<c:forEach var="array" items="${array}"
								begin="${(currentPage-1)*10}" end="${currentPage*10-1}" step="1">
								<tr bgcolor="#FFFFFF">
									<td align="center" height="25" class="altbg1">
										<input type="checkbox" name="chkButton" class="checkbox"
											value="${array.sectionId}" />
									</td>
									<td align="center" class="altbg2">
										${array.sectionName}
									</td>
									<td align="center" class="altbg1">
										${array.sectionMasterID}
									</td>
									<td align="center" class="altbg2">
										<c:if test="${array.sectionState==1}">隐藏</c:if>
										<c:if test="${array.sectionState==0}">非隐藏</c:if>
									</td>
									<!-- td align="center" class="altbg1">
										<c:if test="${array.sectionTop==1}">置顶</c:if>
										<c:if test="${array.sectionTop==0}">非置顶</c:if>
									</td 
									<td class="altbg1">
										<a href="##" onClick="edit('${array.sectionId}')">[修改]</a>
									</td>-->
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${allRecorders<=0}">
							<tr height="25" align="right">
								<td colspan="12" align="center" class="altbg2">
									没有您要搜索的版块数据
								</td>
							</tr>
						</c:if>

					</table>
					<center>
						<input type="button" class="button"
							onClick="oprateSection('state')" value="隐藏">
							<!-- 
						<input type="button" class="button"
						    onClick="oprateSection('top')" value="置顶">
						     -->
						<input type="button" class="button"
							onClick="oprateSection('edit')" value="修改">
						<input type="button" class="button"
							onClick="oprateSection('delete')" value="删除">
						
					</center>
				</form>
			</div>
			<br />
			<br />
			<jsp:include page="/web/admin/foot.jsp"></jsp:include>
		</center>
	</BODY>
</html>
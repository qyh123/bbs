<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="snippet.Snippet"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title><%=Snippet.BBS_NAME%>-会员搜索</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="/bbsweb/css/style_1.css">
<script type="text/javascript">
function gopage(totalPage){
 var selectpage = document.all.selectedCurPage;
 var valu = selectpage.value.replace(/[ ]/g,"");
 var patrn=/^[0-9]*[1-9][0-9]*$/;
	if(valu==""){
		alert("请输入页数");
		selectpage.value="";
		return ;
	}else if(!patrn.exec(valu)){
		alert("请输入正整数");
		selectpage.value="";
		return ;
	}else if(totalPage<valu){
		selectpage.value=totalPage;
		document.forms[0].action="searchUser.do?user=${userType}&userSearch=${userSearch}&pages="+totalPage;
		document.forms[0].submit();
	} else {
       document.forms[0].action="searchUser.do?user=${userType}&userSearch=${userSearch}&pages="+valu;
       document.forms[0].submit();
    }
}
function page(page){
  document.forms[0].action="searchUser.do?user=${userType}&userSearch=${userSearch}&pages="+page;
  document.forms[0].submit();
}
     
  </script>
	</head>
	<body>
		<center>
			<jsp:include page="/top.jsp"></jsp:include>
			

				<div class="pages_btns" style="width: 1003px;margin: 10px 0;">
					<span class="pages"> <a href="index.jsp" title="论坛首页">论坛首页</a>
						<c:if test="${allRecorders!=0}">
							<em>共${allRecorders}条记录&nbsp;第${currentPage}页共${pageSize}页</em>
							<a href="javascript:page(1)" class="prev">首页</a>
							<c:if test="${currentPage!=1}">
								<a href="javascript:page(${currentPage-1})" class="prev">上一页</a>
							</c:if>
							<c:if test="${currentPage!=pageSize}">
								<a href="javascript:page(${currentPage+1})" class="next">下一页</a>
							</c:if>
							<a href="javascript:page(${pageSize})" class="next">尾页</a>
							<a href="javascript:gopage('${pageSize}')" class="next">Go</a>
							<input type="text" name="selectedCurPage" size="5" maxlength="5" />页	
				        </c:if> </span>
					<span class="postbtn" style="margin-left: 0px"> <span
						id="nav"><a id="forumlist" href="index.jsp"><%=Snippet.BBS_NAME%></a>&raquo;会员搜索</span>
					</span>
				</div>

			<form action ="" method="post">
			<div class="mainbox forumlist" id="forumlist" style="width: 1003px;">
				<h3 align="left">
					&nbsp;&nbsp;搜索
				</h3>
				<table width="1003" cellpadding="0" cellspacing="0">
					<thead class="category">
						<tr>
							<td class="nums" style="font-size: 12px">
								&nbsp;&nbsp;会员名称
							</td>

							<td class="nums">
								会员昵称
							</td>
							<td class="nums">
								会员头像
							</td>
							<td class="nums">
								会员级别
							</td>
							<td class="nums">
								会员积分
							</td>
						</tr>
					</thead>
					<c:if test="${errormessage!=null}">
						<tr>
							<td colspan="5" align="center">
								<logic:present name="errormessage" scope="request">
									<bean:write name="errormessage" />
								</logic:present>
							</td>
						</tr>
					</c:if>
					<logic:present name="list" scope="request">
						<logic:iterate id="list" name="list">
							<tr>

								<td>
									${list.userName}
								</td>
								<td>
									${list.userNName}
								</td>
								<td>
									<!-- <img src="OutPicture?userId=${list.userId}" width="25"
										height="25">-->
										 <img src="${list.headImage}" width="25" height="25">
								</td>
								<td>
									${list.userClassName}
								</td>
								<td>
									${list.userPoint}
								</td>
							</tr>
						</logic:iterate>
					</logic:present>
				</table>
			</div>
			</form>
			<p>
				&nbsp;
			</p>
			<jsp:include page="/foot.jsp"></jsp:include>
		</center>
	</body>
</html>

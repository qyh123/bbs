<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.itstar.model.BBSTopic"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.itstar.util.Page"%>
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
		<base href="<%=basePath%>">
		<title><%=Snippet.BBS_NAME%>-帖子搜索</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="css/style_1.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
function gopage(selectpage,totalPage){
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
	}else if(totalPage<valu){
		selectpage.value=totalPage;
	    document.forms[0].action="searchTopic.do?note=${note}&sectionSearch=${sectionSearch}&pages="+totalPage;
		document.forms[0].submit();
	} else {
       document.forms[0].action="searchTopic.do?note=${note}&sectionSearch=${sectionSearch}&pages="+valu;
       document.forms[0].submit();
    }
}
function page(page){
  document.forms[0].action="searchTopic.do?note=${note}&sectionSearch=${sectionSearch}&pages="+page;
  document.forms[0].submit();
}  
  </script>
	</head>
	<body>
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
							<a href="javascript:gopage(selectedCurPage,'${pageSize}')" class="next">Go</a>
							<input type="text" name="selectedCurPage" size="5" maxlength="5" />页	
				        </c:if> </span>
					<span class="postbtn" style="margin-left: 0px"> <span
						id="nav"><a id="forumlist" href="index.jsp"><%=Snippet.BBS_NAME%></a>&raquo;帖子搜索</span>
					</span>
				</div>
			
			<form action="" method="post">
			<div class="mainbox forumlist" id="forumlist" style="width: 1003px;">
				<h3 align="left">
					&nbsp;&nbsp;搜索
				</h3>
				<table width="1003" cellpadding="0" cellspacing="0">
					<thead class="category">
						<tr>
							<td width="417" class="nums" style="font-size: 12px">
								&nbsp;&nbsp;标题
							</td>

							<td width="211" class="nums">
								版块
							</td>
							<td width="181" class="nums">
								作者
							</td>
							<td width="192" align="right" class="lastpost">
								发表时间
							</td>
						</tr>
					</thead>
					<tr>
						<td colspan="4" align="center">
							<logic:present name="em" scope="request">
								<bean:write name="em" />
							</logic:present>
						</td>
					</tr>
				<logic:present name="list" scope="request">
						<logic:iterate id="list" name="list">
					<tr>
						<td height="30" class="nums">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a
								href="show.do?method=show&topicid=${list.topicId }&author=${list.userName}">${list.topicTopic}</a>
						</td>
						<td height="30" class="nums">
							<a
								href="showTopicList.do?method=showTopic&sectionid=${list.topicSectionId}">${list.sectionName}</a>
						</td>
						<td height="30" class="nums">${list.userName }</td>
						<td height="30" class="lastpost" align="right">${list.topicTime }</td>
					</tr>
					</logic:iterate>
				</logic:present>
				</table>
			</div>


			<p>
				&nbsp;

			</p>
			<jsp:include page="/foot.jsp" />
	</body>
</html>

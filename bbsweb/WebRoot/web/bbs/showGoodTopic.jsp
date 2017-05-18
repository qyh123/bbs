<%@ page language="java" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
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
		<title><%=Snippet.BBS_NAME%></title>
		<link href="css/style_1.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<script language="javascript">
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
		document.forms[0].action="showTopicList.do?method=showGoodTopic&pages="+totalPage;
		document.forms[0].submit();
	} else {
      document.forms[0].action="showTopicList.do?method=showGoodTopic&pages="+valu;
      document.forms[0].submit();
    }
}
function page(page){
  document.forms[0].action="showTopicList.do?method=showGoodTopic&pages="+page;
  document.forms[0].submit();
}
</script>
	</head>

	<body>
		<jsp:include page="/top.jsp"></jsp:include>
		<center>
		<form action="" method="post">
			
				<div class="pages_btns" style="width: 1003px;margin: 10px 0;">
					<span class="pages"> <a href="index.jsp" title="论坛首页">论坛首页</a>
						<c:if test="${allPage>1}">
							<em>共${allRecorders}条记录第${currentPage }页共${allPage}页</em>
							<a href="javascript:page(1)" class="prev">首页</a>
							<c:if test="${currentPage!=1}">
								<a href="javascript:page(${currentPage-1})" class="prev">上一页</a>
							</c:if>
							<c:if test="${currentPage!=allPage}">
								<a href="javascript:page(${currentPage+1})" class="next">下一页</a>
							</c:if>
							<a href="javascript:page(${allPage})" class="next">尾页</a>
							<a href="javascript:gopage(selectedCurPage,'${allPage}')" class="next">Go</a>
							<input type="text" name="selectedCurPage" size="5" maxlength="5" />页
						</c:if>
					</span>
					<span class="postbtn" style="margin-left: 0px"> <span
						id="nav"><a id="forumlist" href="index.jsp"><%=Snippet.BBS_NAME%></a>&raquo;精华区</span>
					</span>
				</div>
			
				<div class="mainbox forumlist" id="forumlist" style="width: 1003px;">
					<h3 align="left">
						&nbsp;&nbsp;精华区
					</h3>
					<table cellpadding="0" cellspacing="0" id="forum_2" >
						<thead class="category">
							<tr>
								<td width="32"  align="center">
									&nbsp;
								</td>
								<td width="423" >
									文章标题
								</td>
								<td width="119" >
									版块
								</td>
								<td width="119" class="author">
									作者
								</td>
								<td width="90" align="center">
									回复量
								</td>
								<td width="100" align="right">
									发布时间
								</td>
							</tr>
						</thead>
						<logic:present name="array">
							<logic:iterate id="array" name="array" indexId="i">
								<tbody>
									<tr>
										<td align="center" height="25" width="32">
											&nbsp;
											<img src="images/default/jing.gif">
										</td>
										<td height="30" class="nums">
											<a
												href="show.do?method=show&topicid=${array.topicId }&author=${array.userName}"
												target="_bank">${array.topicTopic } </a>
											<c:if test="${array.newTopic==true}">
												<img src="images/default/firstnew.gif" />
											</c:if>
											<c:if test="${array.topTime!=null}">
												<img src="images/default/pin_3.gif">
											</c:if>
										</td>
										<td >
											${array.sectionName}
										</td>
										<td >
											${array.userName}
										</td>
										<td align="center" >
											${array.topicReplyCount}
										</td>
										<td align="right">
											${array.topicTime}
										</td>
									</tr>
								</tbody>
							</logic:iterate>
						</logic:present>
						<c:if test="${allRecorders==0}">
							<tr>
								<td height="25" align="center" colspan="6">
									没有匹配的主题。
								</td>
							</tr>
						</c:if>

					</table>
				</div>
			</form>
			<table width="1003" align="center">
				<tr>
					<td align="right">
						<div class="pages" align="right">
							<a href="index.jsp" title="论坛首页">论坛首页</a>
						 <c:if test="${allPage>1}">
							<em>共${allRecorders}条记录第${currentPage }页共${allPage}页</em>
							<a href="javascript:page(1)" class="prev">首页</a>
							<c:if test="${currentPage!=1}">
								<a href="javascript:page(${currentPage-1})" class="prev">上一页</a>
							</c:if>
							<c:if test="${currentPage!=allPage}">
								<a href="javascript:page(${currentPage+1})" class="next">下一页</a>
							</c:if>
							<a href="javascript:page(${allPage})" class="next">尾页</a>
							<a href="javascript:gopage(selectedCurPage1,'${allPage}')" class="next">Go</a>
							<input type="text" name="selectedCurPage1" size="5" maxlength="5" />页
						 </c:if>
						</div>
					</td>
				</tr>
			</table>
		</center>
		<p>
			&nbsp;&nbsp;
		</p>
		<jsp:include page="/foot.jsp"></jsp:include>
	</body>
</html>
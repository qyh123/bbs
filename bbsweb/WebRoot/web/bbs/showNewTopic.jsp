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
		<link href="css/style_1.css" rel="stylesheet"
			type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<script language="javascript">
function gopage(selectpage,totalPage){
 var valu=selectpage.value.replace(/[ ]/g,"");
 var patrn=/^[0-9]*[1-9][0-9]*$/;
	if(valu==""){
		alert("������ҳ��");
		selectpage.value="";
		return ;
	}else if(!patrn.exec(valu)){
		alert("������������");
		selectpage.value="";
		return ;
	}else if(totalPage<valu){
		selectpage.value=totalPage;
		location.href="showTopicList.do?method=showNewTopic&pages="+totalPage;
	} else {
      location.href="showTopicList.do?method=showNewTopic&pages="+valu;
    }
}
function page(page){
  location.href="showTopicList.do?method=showNewTopic&pages="+page;
  //document.forms[0].submit();
}
</script>
	</head>

	<body>
		<jsp:include page="/top.jsp"></jsp:include>
		<center>
				<div class="pages_btns" style="width: 1003px;margin: 10px 0;">
					<span class="pages"> <a href="index.jsp" title="��̳��ҳ">��̳��ҳ</a>
						<c:if test="${allPage>1}">
							 <em>��${allRecorders}����¼��${currentPage }ҳ��${allPage}ҳ</em>
							<a href="javascript:page(1)" class="prev">��ҳ</a>
							<c:if test="${currentPage!=1}">
								<a href="javascript:page(${currentPage-1})" class="prev">��һҳ</a>
							</c:if>
							<c:if test="${currentPage!=allPage}">
								<a href="javascript:page(${currentPage+1})" class="next">��һҳ</a>
							</c:if>
							<a href="javascript:page(${allPage})" class="next">βҳ</a>
							<a href="javascript:gopage(selectedCurPage,'${allPage}')" class="next">Go</a>
							<input type="text" name="selectedCurPage" size="5" maxlength="5" />ҳ
						</c:if>
					</span>
					<span class="postbtn" style="margin-left: 0px"> <span
						id="nav"><a id="forumlist" href="index.jsp"><%=Snippet.BBS_NAME%></a>&raquo;������</span>
					</span>
				</div>
			<form action="User_Check" name="myform1">
				<div class="mainbox forumlist" id="forumlist" style="width: 1003px;">
				<h3 align="left">
						&nbsp;&nbsp;������
					</h3>
					<table width="1003" align="center" cellpadding="0" cellspacing="0"
						id="category_1" style="" summary="category1">
						<thead class="category">
							<tr>
								<td width="32" height="25" align="center">&nbsp;
									
								</td>
								<td width="389"  class="nums">
									���±���
								</td>
								<td width="140">
									���
								</td>
								<td width="119">
									����
								</td>
								<td width="168" align="center">
									�ظ���
								</td>
								<td width="153" align="right">
									����ʱ��
								</td>
							</tr>
						</thead>
						<logic:present name="array">
							<logic:iterate id="array" name="array" indexId="i">
								<tbody>
									<tr>
										<td align="center" height="25" width="32">
											&nbsp;
											<c:if test="${array.topicState==1}">
												<img src="images/default/jing.gif">
											</c:if>
										</td>
										<td height="30" class="nums">
											<a
												href="show.do?method=show&topicid=${array.topicId }&author=${array.userName}"
												target="_bank">${array.topicTopic }</a><img
													src="images/default/firstnew.gif" /> 
										</td>
										<td width="140">
											${array.sectionName}
										</td>
										<td class="nums">
											${array.userName}
										</td>
										<td align="center">
											<div>
												${array.topicReplyCount}
											</div>
										</td>
										<td align="right">
											&nbsp;
											<div>
												${array.topicTime}
											</div>
										</td>
									</tr>
								</tbody>
							</logic:iterate>
						</logic:present>
						<c:if test="${allRecorders==0}">
							<tr>
								<td height="25" align="center" colspan="6">
								  û��ƥ������⡣</td>
							</tr>
						</c:if>

					</table>
				</div>
			</form>
			<table width="1003" align="center">
				<tr>
					<td align="right">
						<div class="pages" align="right">
							<a href="index.jsp" title="��̳��ҳ">��̳��ҳ</a>
						<c:if test="${allPage>1}">
							<em>��${allRecorders}����¼��${currentPage }ҳ��${allPage}ҳ</em>
							<a href="javascript:page(1)" class="prev">��ҳ</a>
							<c:if test="${currentPage!=1}">
								<a href="javascript:page(${currentPage-1})" class="prev">��һҳ</a>
							</c:if>
							<c:if test="${currentPage!=allPage}">
								<a href="javascript:page(${currentPage+1})" class="next">��һҳ</a>
							</c:if>
							<a href="javascript:page(${allPage})" class="next">βҳ</a>
							<a href="javascript:gopage(selectedCurPage1,'${allPage}')" class="next">Go</a>
							<input type="text" name="selectedCurPage1" size="5" maxlength="5" />ҳ
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
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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<script src="js/middle.js" type="text/javascript"></script>
		<link href="css/style_1.css" rel="stylesheet" type="text/css">
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
		document.forms[0].action="showTopicList.do?method=showTopic&sectionid=${section.sectionId}&pages="+totalPage;
		document.forms[0].submit();
	} else {
      document.forms[0].action="showTopicList.do?method=showTopic&sectionid=${section.sectionId}&pages="+valu;
      document.forms[0].submit();
    }
}

function page(page){
  document.forms[0].action="showTopicList.do?method=showTopic&sectionid=${section.sectionId}&pages="+page;
  document.forms[0].submit();
}

function checkAll(){
    var eles = document.getElementsByName("chkButton");
    var len = eles.length
    for (var i=0; i < len; i++)
    {
        eles[i].checked = eles[len-1].checked;
    }
}

function oprateSection(str) {
    if(str.indexOf("state")!=-1) {
        var url= "stateTopic";
		var info ="����";
    } else if(str.indexOf("delete")!=-1) {
		var url= "deleteTopic";
		var info ="ɾ��";
    } else  {
        var url="topTopic";
        var info = "�ö�";
    }
	if (getSelectedCheckBoxNumber()==0) {
			alert("��ѡ����Ҫ"+info+"�ļ�¼��");
			return false;
	}  else {
			if (confirm("��ȷ��Ҫ����ѡ��¼"+info+"��")) {
				var checkbutton = document.getElementsByName('chkButton');
				var resultstr = "";
				for(var i=0; i<checkbutton.length-1; i++) {
					if(checkbutton[i].checked){
						resultstr = resultstr+","+checkbutton[i].value;
					}
				}
				if(resultstr != ""){
					resultstr = resultstr.substring(1);
				}
		     	document.forms[0].action= "showTopicList.do?method="+url+"&sectionid=${section.sectionId}&topicId="+resultstr;
				document.forms[0].submit();
			}
  }
}


</script>
	</head>
	<body>
		<br><jsp:include page="/top.jsp"></jsp:include>
		<center>
		   <form action="" method="post">
			 <div id="foruminfo" style="width: 1003px">
				<div id="nav">
					<p>
						<a id="forumlist" href="index.jsp"><%=Snippet.BBS_NAME%></a>
						&raquo;${section.sectionName }
						
					</p>
					<p align="left">
						����:
						<a class="notabs">${sectionMaster}</a>
					</p>
				 </div>
			  </div>
		    </form>
			<div class="pages_btns" style="width: 1003px">
				<span class="pages"> <a href="index.jsp" title="��̳��ҳ">��̳��ҳ</a>
				  <c:if test="${allRecorders>0}">
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
				  </c:if></span>
				 
				<span class="postbtn">
				<a href="saveToken.do?path=post&id=${section.sectionId}"><img
							src="images/default/newtopic.gif" alt="���»���" /> </a> </span>
			</div>
				<div class="mainbox forumlist" id="forumlist" style="width: 1003px;">
					<h3 align="left">
						${section.sectionName}
					</h3>
					<table width="1003" align="center" cellpadding="0" cellspacing="0"
						id="category_1" style="" summary="category1">
						<thead class="category">
							<tr>
								<td width="20" height="30">
									&nbsp;
								</td>
								<td width="472">
									���±���
								</td>
								<td width="126" align="center">
									����
								</td>
								<td width="168" align="center">
									�ظ���
								</td>
								<td width="130" align="right">
									����ʱ��
								</td>
							</tr>
						</thead>

						<c:if test="${isHaveTop==true&&currentPage==1}">
							<thead class="separation"">
								<tr>
									<td height="30">
										&nbsp;
									</td>
									<td height="30" colspan="5">
										�ö�����
									</td>
								</tr>
							</thead>
						</c:if>
						<logic:present name="array">
							<logic:iterate id="list" name="array" indexId="i">
								<c:if test="${list.topTime!=null}">
									<tbody>
										<tr>
											<td>
												&nbsp;
												<c:if test="${list.topicState==1}">
													<img src="images/default/jing.gif">
												</c:if>
											</td>
											<td height="30">
												<c:if
													test="${user.userId == section.sectionMasterID||user.userClass==3}">
													<input type="checkbox" name="chkButton" class="checkbox"
														value="${list.topicId}" />
												</c:if>
												<a
													href="show.do?method=show&topicid=${list.topicId }&author=${list.userName}">${list.topicTopic}</a>
												<c:if test="${list.newTopic==true}">
													<img src="images/default/firstnew.gif" />
												</c:if>
												<img src="images/default/pin_3.gif">
											</td>

											<td align="center">
												${list.userName}
											</td>
											<td align="center">
												<div>
													${list.topicReplyCount}
												</div>
											</td>
											<td align="right">
												<div>
													${list.topicTime}
												</div>
											</td>
										</tr>
									</tbody>
								</c:if>
							</logic:iterate>
						</logic:present>
						<c:if test="${isHaveTop==true&&list.topTime==null}">
							<thead class="separation"">
								<tr>
									<td height="30">
										&nbsp;
									</td>
									<td height="30" colspan="5">
										�������
									</td>
								</tr>
							</thead>
						</c:if>
						<logic:present name="array">
							<logic:iterate id="array" name="array" indexId="i">
								<c:if test="${array.topTime==null}">
									<tbody>
										<tr>
											<td>
												&nbsp;
												<c:if test="${array.topicState==1}">
													<img src="images/default/jing.gif">
												</c:if>
											</td>

											<td height="30">
												<c:if
													test="${user.userId == section.sectionMasterID||user.userClass==3}">
													<input type="checkbox" name="chkButton" class="checkbox"
														value="${array.topicId}" />
												</c:if>
												<a
													href="show.do?method=show&topicid=${array.topicId }&author=${array.userName}">${array.topicTopic}
													<c:if test="${array.newTopic==true}">
														<img src="images/default/firstnew.gif" />
													</c:if> </a>
											</td>

											<td align="center">
												${array.userName}
											</td>
											<td align="center">
												<div>
													${array.topicReplyCount}
												</div>
											</td>
											<td align="right">
												<div>
													${array.topicTime}
												</div>
											</td>
										</tr>
									</tbody>
								</c:if>
							</logic:iterate>
						</logic:present>
						<c:if test="${allRecorders==0}">
							<tr>
								<td height="25" align="center" colspan="5">
									��û���ṩ����������û��������ƥ������⡣
								</td>
							</tr>
						</c:if>
					</table>
					<c:if  test="${ user.userId == section.sectionMasterID ||user.userClass==3}">
						<div class="footoperation" align="left">
							<label>
								<input class="checkbox" type="checkbox" name="chkButton"
									onClick="checkAll()" />
								ȫѡ
							</label>
							<button onClick="oprateSection('delete')">
								ɾ������
							</button>
							<button onClick="oprateSection('top')">
								�ö�/����ö�
							</button>
							<button onClick="oprateSection('state')">
								����/�������
							</button>
						</div>
					</c:if>
				</div>
			<div class="pages_btns" style="width: 1003px">
				<span class="pages"> <a href="index.jsp" title="��̳��ҳ">��̳��ҳ</a>
				  <c:if test="${allRecorders>0}">
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
				  </c:if></span>
				 
				<span class="postbtn">
				<a href="saveToken.do?path=post&id=${section.sectionId}"><img
							src="images/default/newtopic.gif" alt="���»���" /> </a> </span>
			</div>
		</center>
		<p>
			&nbsp;&nbsp;
		</p>
		<jsp:include page="/foot.jsp"></jsp:include>
	</body>
</html>
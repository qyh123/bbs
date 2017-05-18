<%@ page language="java" pageEncoding="GBK" import="net.fckeditor.*,java.net.URLEncoder"
	errorPage="web/error.sjp"%>
	<%@ page import="snippet.Snippet"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	//String basePath = request.getScheme() + "://"
	//		+ request.getServerName() + ":" + request.getServerPort()
	//		+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><%=Snippet.BBS_NAME%>-����</title>
		<link href="css/style_1.css" rel="stylesheet" type="text/css">
	</head>
	<script language="javascript">
   function pingBi(trid){
       var username = document.all.userName.value;
       send_request("POST","show.do?method=bunPosts",null,"text", showCheck(trid)); 
   }
   
   function showCheck(trid) { 
		if (http_request.readyState == 4) { // �ж϶���״̬
			if (http_request.status == 200) { // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
			    var text = http_request.responseText;
			    var contentDiv = document.getElementById("replay"+trid);
                var pingBiDiv = document.getElementById("pingBi"+trid);
			    document.getElementById("message").innerText = http_request.responseText;
			    if(text.indexOf("1")!=-1) {
			       contentDiv.style.display = "none";
		           pingBiDiv.style.display = "";
			    } else {
			       contentDiv.style.display = "";
		           pingBiDiv.style.display = "none";
			    } 
			}else{
				alert("���������ҳ�����쳣��");
			}
		}
  }
   function validate(){
        //var editor = FCKeditorAPI.GetInstance("topicContent");
       // var chk = editor.GetXHTML();
       var chk = document.all.topicContent.value;
        if(chk=="") {
           alert("���ݲ���Ϊ��");
           //editor.Focus();
           document.all.topicContent.focus();
           return false;
 		} 
 	}
     function reply(buildId){
         //var editor = FCKeditorAPI.GetInstance("topicContent");
        //editor.SetHTML("�ظ�" + buildId+"¥:");
        document.all.topicContent.value = "�ظ�" + buildId+"¥:";
     }
     function clearContent(){
         var editor = FCKeditorAPI.GetInstance("topicContent");
          editor.SetHTML("");
     }
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
		    location.href="show.do?method=show&topicid=${bTopic.topicId}&author=${bUser.userName}&pages="+totalPage;
	   } else {
             location.href="show.do?method=show&topicid=${bTopic.topicId}&author=${bUser.userName}&pages="+valu;
       }
    }
function page(page){
  location.href="show.do?method=show&topicid=${bTopic.topicId}&author=${bUser.userName}&pages="+page;
}
function advanceReply(){
	document.forms[0].action="saveToken.do?path=reply";
	document.forms[0].submit();
}
</script>
	<body>

		<jsp:include page="/top.jsp"></jsp:include>
		<center>
			<div id="foruminfo" style="width: 1003px">
				<div id="nav">
					<p>
						<a href="index.jsp"><%=Snippet.BBS_NAME%></a>&raquo;
						<a
							href="showTopicList.do?method=showTopic&sectionid=${section.sectionId}">
							${section.sectionName} </a>&raquo; ${bTopic.topicTopic}
					</p>
				</div>
			</div>
			<div class="pages_btns" style="width: 1003px">
				<span class="pages"><a href="index.jsp" title="��̳��ҳ">��̳��ҳ</a>
					<em>��${allRecorders}����${currentPage }ҳ��${allPage}ҳ</em> 
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
				 </span>
				<span class="postbtn"><a
					href="#reply">
						<img src="images/default/reply.gif" alt="�ظ�" /> </a>&nbsp;&nbsp; 
					<a href="saveToken.do?path=post&id=${section.sectionId}"><img
							src="images/default/newtopic.gif" alt="���»���" /> </a> </span>
			</div>
			<table width="1003" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3" align="center" valign="bottom" height="30"
						class="tdtop">
						<table background="images/default/header_bg.gif"
							style="background-repeat: repeat; width: 999px;">
							<tr>
								<td height="28" class="headfont">
									&nbsp;&nbsp;${bTopic.topicTopic}
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<c:if test="${currentPage==1}">
					<tr class="author" id="t${btopic.topicId}">
						<td width="20%" class="tdleft" height="20" bgcolor="#E8F3FD"
							style="font-weight: bold;">
							&nbsp;&nbsp;${bUser.userNName}
						</td>
						<td width="400" class="tdtopbot">
							������${bTopic.topicTime}&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
						<td width="403" class="tdright" align="right">
							<a href="#?" onClick="t${index+1}.className='t_bigfont'">��</a>&nbsp;&nbsp;
							<a href="#?" onClick="t${index+1}.className='t_msgfont'">��</a>&nbsp;&nbsp;
							<a href="#?" onClick="t${index+1}.className='t_smallfont'">С</a>&nbsp;&nbsp;
							<span>#${index+1}</span>
						</td>
					</tr>
					<tr>
						<td width="200" class="tdleft" bgcolor="#E8F3FD" rowspan="2">

							<table width="100%" style="height:163px" cellpadding="0" cellspacing="0"
								class="author">
								<tr>
									<td colspan="2">
										<div class="avatar" align="center">
											<!--<img class="avatar"
												src="<%=path%>/OutPicture?userId=${bUser.userId}"
												width="70" height="70"
												onerror="this.src='images/default/noavatar.gif'" />-->
												<img class="avatar"
												src="images/default/noavatar.gif"
												width="70" height="70"
												onerror="this.src='images/default/noavatar.gif'" />
										</div>
									</td>
								</tr>
								<tr>
									<td align="center">
										����:
									</td>
									<td>
										${bUser.userClassName}
									</td>
								</tr>
								<tr>
									<td align="center">
										����:
									</td>
									<td>
										${bUser.userPoint}
									</td>
								</tr>
								<tr>
									<td align="center">
										ע������:
									</td>
									<td>
										${bUser.userRegDate}
									</td>
								</tr>
							</table>
						</td>
					  <td height="130" colspan="2" class="tdright" id="t${index+1}">
							ԭ����
							<br />
							<c:if test="${bUser.bunState>1}">
								<div style="width: 500px"><span class="notice">
									��ʾ:
									<em>���߱���ֹ��ɾ�� �����Զ����Σ�ֻ�й���Ա�ɼ�</em>
								</span></div>
							</c:if>
							<c:if test="${bTopic.topicBunState==1&&bUser.bunState==1}">
								<div style="width: 500px"><span class="notice">
									��ʾ:
									<em>�������ӱ����Σ�ֻ�й���Ա�Ͱ����ɼ�</em>
								</span></div>
							</c:if>
						    
						    <c:if test="${user.userClass==3||(bUser.bunState==1&&bTopic.topicBunState==0)}">
					        ${bTopic.topicContent}						</c:if>
			           </td>
					</tr>
					<tr>
						<td height="30" align="right" colspan="2" class="tdright"
							bgcolor="F7F7F7">
							<c:if
								test="${user.userId == section.sectionMasterID||user.userClass==3}">
								<a
									href="show.do?method=bunPosts&topicSign=1&author=${author}&topicid=${bTopic.topicId}&pages=${currentPage}">����</a>
							</c:if>
							<c:if test="${(user!=null&&bUser.bunState==1)}">
								<c:if test="${bUser.userName==user.userName||user.userId==section.sectionMasterID||user.userClass==3}">
									<a
										href="editAndQuoteTopic.do?opration=1&topicSign=1&author=${author}&topicid=${bTopic.topicId}&pages=${currentPage}">�༭</a>
									<a
										href="show.do?method=deleteTopic&topicSign=1&sectionId=${section.sectionId}&author=${author}&topicid=${bTopic.topicId}">ɾ��</a>
								</c:if>
								<a
									href="editAndQuoteTopic.do?opration=0&topicSign=1&author=${author}&topicid=${bTopic.topicId}&pages=${currentPage}">����</a>

								<a href="#reply" onClick="reply('${index+1}')">�ظ�</a>
							</c:if>
							<a href="#top">TOP</a>
						</td>
					</tr>
					<c:if test="${topicList!=null}">
						<tr>
							<td height="12" colspan="3" class="tdbottom"></td>
						</tr>
					</c:if>
					<c:if test="${topicList==null}">
						<tr>
							<td height="12" colspan="3"></td>
						</tr>
					</c:if>

					<c:if test="${topicList!=null}">
						<logic:present name="topicList">
							<logic:iterate id="list" name="topicList" indexId="i">
								<%
									//for (int a = Page.spage; a < Page.ipage * Page.cpage - 1
															//&& a < list.size(); a++) {
															//ReplyAndUser uu = (ReplyAndUser) list.get(a);
								%>
								<tr class="author" id="r${list.replyID}">
									<td width="27%" class="tdleft" height="20" bgcolor="#E8F3FD">
										&nbsp;&nbsp;${list.userNName }
									</td>
									<td width="40%" class="tdtopbot">
										������${list.replyTime}
									</td>
									<td width="40%" class="tdright" align="right">
										<a href="#?"
											onClick="f${currentPage*10-8+i}.className='t_bigfont'">��</a>&nbsp;&nbsp;
										<a href="#?"
											onClick="f${currentPage*10-8+i}.className='t_msgfont'">��</a>&nbsp;&nbsp;
										<a href="#?"
											onclick="f${currentPage*10-8+i}.className='t_smallfont'">С</a>&nbsp;&nbsp;
										<span> #${currentPage*10-8+i}</span>
									</td>
								</tr>
								<tr>
									<td width="20%" bgcolor="#E8F3FD" class="tdleft" rowspan="2">
										<table width="100%" height="133" cellpadding="0"
											cellspacing="0" class="author">
											<tr>
												<td colspan="2">
													<div class="avatar" align="center">
														<!--<img class="avatar" id="${(currentPage-1)*10+i+2}"
															src="<%=path%>/OutPicture?userId=${list.userId}"
															width="70" height="70"
															onerror="this.src='images/default/noavatar.gif'" />-->
															<img class="avatar"  src="images/default/noavatar.gif"
															width="70" height="70"
															onerror="this.src='images/default/noavatar.gif'" />
													</div>
												</td>
											</tr>
											<tr>
												<td align="center">
													����:
												</td>
												<td>
													${list.userClassName}
												</td>
											</tr>
											<tr>
												<td align="center">
													����:
												</td>
												<td>
													${list.userPoint}
												</td>
											</tr>
											<tr>
												<td align="center">
													ע������:
												</td>
												<td>
													${list.userRegDate}
												</td>
											</tr>
										</table>
									</td>
									<td width="100%" height="130" colspan="2" class="tdright"
										id="f${currentPage*10-8+i}">
										<c:if test="${list.bunState>1}">
											<div class="notice" style="width: 500px">
												��ʾ:
												<em>���߱���ֹ��ɾ�� �����Զ����Σ�ֻ�й���Ա�ɼ�${list.replyState==1}</em>
											</div>
										</c:if>
										<c:if test="${list.replyState==1&&list.bunState==1}">
											<div class="notice" style="width: 500px">
												��ʾ:
												<em>�������ӱ����Σ�ֻ�й���Ա�ɼ�</em>
											</div>
										</c:if>
										<c:if test="${user.userClass==3||(list.replyState==0&&list.bunState==1)}">
											<div id="replay${currentPage*10-8+i}">
												${list.replyContent}
											</div>
											<div class="notice" id="pingBi${currentPage*10-8+i}"
												style="width: 500px; display: none">
												��ʾ:
												<em>���߱���ֹ��ɾ�� �����Զ����Σ�ֻ�й���Ա�ɼ�</em>
											</div>
										</c:if>
									</td>
								</tr>
								<tr>
									<td height="30" align="right" colspan="2" bgcolor="F7F7F7"
										class="tdright">
										<c:if
											test="${user.userId == section.sectionMasterID||user.userClass==3}">
											<a
												href="show.do?method=bunPosts&replyid=${list.replyID}&author=${author}&topicid=${bTopic.topicId}&topicSign=0&pages=${currentPage}">����</a>
										</c:if>
										<c:if
											test="${(user!=null&&list.bunState==1)||user.userClass==3}">
											<c:if
												test="${list.userName==user.userName||user.userId==section.sectionMasterID||user.userClass==3}">
												<a
													href="editAndQuoteTopic.do?opration=1&replyid=${list.replyID}&author=${author}&topicid=${bTopic.topicId}&topicSign=0&pages=${currentPage}">�༭</a>
												<a
													href="show.do?method=deleteTopic&sectionId=${section.sectionId}&replyid=${list.replyID}&author=${author}&topicid=${bTopic.topicId}&topicSign=0">ɾ��</a>
											</c:if>
											<a
												href="editAndQuoteTopic.do?opration=0&replyid=${list.replyID}&author=${author}&topicid=${bTopic.topicId}&topicSign=0&pages=${currentPage}">����</a>

											<a href="#reply" onClick="reply('${(currentPage-1)*10+i+2}')">�ظ�</a>
										</c:if>
										<a href="#top">TOP</a>
									</td>
								</tr>
								<tr>
									<td height="12" colspan="3"></td>
								</tr>
							</logic:iterate>
						</logic:present>
					</c:if>
				</c:if>
				<c:if test="${currentPage>1}">
					<logic:present name="topicList">
						<logic:iterate id="list" name="topicList" indexId="i">
							<%
								//for (int a = Page.spage; a < Page.ipage * Page.cpage - 1
													//&& a < list.size(); a++) {
													//ReplyAndUser uu = (ReplyAndUser) list.get(a);
							%>
							<tr id="r${list.replyID}" class="author">
								<td width="27%" class="tdleft" height="20" bgcolor="#E8F3FD">
									&nbsp;&nbsp;${list.userNName }
								</td>
								<td width="40%" class="tdtopbot" id="">
									������${list.replyTime}
								</td>
								<td width="40%" class="tdright" align="right">
									<a href="#?"
										onClick="f${currentPage*10-8+i}.className='t_bigfont'">��</a>&nbsp;&nbsp;
									<a href="#?"
										onClick="f${currentPage*10-8+i}.className='t_msgfont'">��</a>&nbsp;&nbsp;
									<a href="#?"
										onclick="f${currentPage*10-8+i}.className='t_smallfont'">С</a>&nbsp;&nbsp;
									<span> #${currentPage*10-9+i}</span>
								</td>
							</tr>
							<tr>
								<td width="20%" bgcolor="#E8F3FD" class="tdleft" rowspan="2">
									<table width="100%" height="133" cellpadding="0"
										cellspacing="0" class="author">
										<tr>
											<td colspan="2">
												<div class="avatar" align="center">
													<!-- <img class="avatar"
														src="<%=path%>/OutPicture?userId=${list.userId}"
														width="70" height="70"
														onerror="this.src='images/default/noavatar.gif'" />-->
														<img class="avatar" src="images/default/noavatar.gif" width="70" height="70"
														onerror="this.src='images/default/noavatar.gif'" />
												</div>
											</td>
										</tr>
										<tr>
											<td align="center">
												����:
											</td>
											<td>
												${list.userClassName}
											</td>
										</tr>
										<tr>
											<td align="center">
												����:
											</td>
											<td>
												${list.userPoint}
											</td>
										</tr>
										<tr>
											<td align="center">
												ע������:
											</td>
											<td>
												${list.userRegDate}
											</td>
										</tr>
									</table>
								</td>
								<td width="100%" height="130" colspan="2" class="tdright"
									id="f${currentPage*10-8+i}">

									<c:if test="${list.bunState>1}">
										<div class="notice" style="width: 500px">
											��ʾ:
											<em>���߱���ֹ��ɾ�� �����Զ����Σ�ֻ�й���Ա�ɼ�${list.replyState==1}</em>
										</div>
									</c:if>
									<c:if test="${list.replyState==1&&list.bunState==1}">
										<div class="notice" style="width: 500px">
											��ʾ:
											<em>�������ӱ����Σ�ֻ�й���Ա�ɼ�</em>
										</div>
									</c:if>
									<c:if
										test="${user.userClass==3||(list.bunState==1&&list.replyState==0)}">
										<div id="replay${currentPage*10-8+i}">
											${list.replyContent}
										</div>
									</c:if>
								</td>
							</tr>
							<tr>
								<td height="30" align="right" colspan="2" bgcolor="F7F7F7"
									class="tdright">
									<c:if
										test="${user.userId==section.sectionMasterID||user.userClass==3}">
										<a
											href="show.do?method=bunPosts&replyid=${list.replyID}&author=${author}&topicid=${bTopic.topicId}&topicSign=0&pages=${currentPage}">����</a>
									</c:if>
									<c:if test="${(user!=null&&list.bunState==1)}">
										<c:if
											test="${list.userName==user.userName||user.userId==section.sectionMasterID||user.userClass==3}">
											<a
												href="editAndQuoteTopic.do?opration=1&replyid=${list.replyID}&author=${author}&topicid=${bTopic.topicId}&topicSign=0&pages=${currentPage}">�༭</a>
											<a
												href="show.do?method=deleteTopic&sectionId=${section.sectionId}&replyid=${list.replyID}&author=${author}&topicid=${bTopic.topicId}&topicSign=0">ɾ��</a>
										</c:if>
										<a
											href="editAndQuoteTopic.do?opration=0&replyid=${list.replyID}&author=${author}&topicid=${bTopic.topicId}&topicSign=0&pages=${currentPage}">����</a>

										<a href="#reply" onClick="reply('${currentPage*10-9+i}')">�ظ�</a>
									</c:if>
									<a href="#top">TOP</a>
								</td>
							</tr>
							<tr>
								<td height="12" colspan="3"></td>
							</tr>
						</logic:iterate>
					</logic:present>
				</c:if>
			</table>
			<div class="pages_btns" style="width: 1003px">
				<span class="pages"><a href="index.jsp" title="��̳��ҳ">��̳��ҳ</a>
					<em>��${allRecorders}����${currentPage }ҳ��${allPage}ҳ</em> 
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
				 </span>
				<span class="postbtn"><a
					href="#reply">
						<img src="images/default/reply.gif" alt="�ظ�" /> </a>&nbsp;&nbsp; 
					<a href="saveToken.do?path=post&id=${section.sectionId}"><img
							src="images/default/newtopic.gif" alt="���»���" /> </a> </span>
			</div>
			<c:if test="${user!=null&&bunUser.bunState==1}">
				<div align="left" class="box" style="width: 1003px" id="reply">
					<h4>
						�츴�ظ�����
					</h4>
					<html:form action="show.do?method=replyTopic">
						<table width="1003" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="225" height="200" valign="top">
									<input type="hidden" name="topicid" value="${topicId}" />
									<input type="hidden" name="author" value="${author}" />
									
									<h5>
										&nbsp;&nbsp;&nbsp;�û���
									</h5>
									<h5>
										&nbsp;&nbsp;&nbsp;����
									</h5>
								</td>
								<td width="778" height="200" valign="top">
									${user.userName}
									<!-- 
									<div class="editor_tb" align="right">
										<span class="right">
											 <a href="javascript:advanceReply();">�߼��ظ�</a>
										</span>
									</div>
									 -->
									<div style="width: 601px" id="reply">
									     <!--<FCK:editor instanceName="topicContent" toolbarSet="easy"></FCK:editor>-->
										 <textarea rows="12" cols="116" name="topicContent"></textarea>
									</div>
									<div style="margin-top: 5px;">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="submit" class="button"
											onClick="validate()" value="����ظ�" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span class="right"><a href="#reply"
											onclick="clearContent()">�������</a> </span>
									</div>
									<p>&nbsp;
										
									</p>
								</td>
							</tr>
						</table>
					</html:form>
				</div>

			</c:if>
			<p>&nbsp;
				
			</p>
		</center>
		<jsp:include page="/foot.jsp"></jsp:include>
		<script type="text/javascript">
  
     

 </script>
	</body>
</html>

																				
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
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
		<base href="<%=basePath%>">

		<title>��̨��Ϣ��ʾ</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="css/admincp.css" type="text/css" rel="stylesheet">
	</head>
	<body>
		<center>
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<div style="width: 500px;height:400px">
				<table border="0" cellpadding="0" cellspacing="0" align="center"
					class="tableborder">
					<tr class="header">
						<td>
							<%=Snippet.BBS_NAME%>��ʾ
						</td>
					</tr>
					<tr>
						<td class="altbg2">
							<div align="center">
								<c:if test="${message=='addsuccess'}">
									<p>
										����û��ɹ���
									</p>
									<p>
									  <a href="displayUser.do?method=show">[ �������ص��༭�û�ҳ��]</a>
									</p>
								</c:if>
								<c:if test="${message=='adderror'}">
									<p>
										����û�ʧ�ܡ�
									</p>
									<p>
										<a href="saveToken.do?path=addUser">[ ������ﷵ����һҳ ]</a>
									</p>
								</c:if>
								<c:if test="${message=='sectionSuccess'}">
									<p>
										��Ӱ��ɹ�,��
									</p>
									<p>
		                              <a href="displaySection.do?method=show">[ �������ص��༭���ҳ��]</a>

									</p>
								</c:if>
								<c:if test="${message=='sectionError'}">
									<p>
										��Ӱ��ʧ�ܡ�
									</p>
									<p>
										<a href="saveToken.do?path=add_section">[ ������ﷵ����һҳ ]</a>
									</p>
								</c:if>
								<c:if test="${message=='bunError'}">
									<p>
										��ֹ�û�����ʧ�ܣ�
									</p>
									<p>
										<a href="saveToken.do?path=bunUser">[ ������ﷵ����һҳ ]</a>
									</p>
								</c:if>
								<c:if test="${message=='bunSuccess'}">
									<p>
										��ֹ�û������ɹ���
									</p>
									<p>
										<script type="text/javascript">
		         setTimeout("location.href='saveToken.do?path=bunUser'",2000);
		        </script>
										<a href="saveToken.do?path=bunUser">[ �������������һҳ ]</a>
									<p>
								</c:if>
							</div>
							<br />
							<br />
						</td>
					</tr>
				</table>
			</div>
			<br />
			<br />
			<br />
			<br />
			<br />
			<jsp:include page="/web/admin/foot.jsp"></jsp:include>
		</center>
	</body>
</html>

<%@ page language="java" pageEncoding="gbk"%>
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

		<title><%=Snippet.BBS_NAME%>-��Ϣ��ʾ</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>
		<center>
			<jsp:include page="/top.jsp" />
			<%
				request.getAttribute("userPassword");
			%>
			<div id="foruminfo" style="width: 1003px">
				<div id="nav" align="left" style="width: 1003px">
					<a href="index.jsp"><%=Snippet.BBS_NAME %></a>&raquo; ��ʾ��Ϣ
				</div>
			</div>
			<div class="box message" align="center" style="width:700px">
				<h1>
					<%=Snippet.BBS_NAME %>�� ��ʾ��Ϣ
				</h1>
				<c:if test="${message=='success'}">

					<p>
						�����һسɹ���
						<br />
						��һ���������ڵ�ע�����������ȡ�µ����룡
					</p>
				</c:if>
				<c:if test="${message =='error'}">
					<p>
						�û�����Email ��ַ��ȫ���ʲ�ƥ�䣬�뷵���޸ġ�
					</p>
					<p>
						<a href="saveToken.do?path=findpassword">[ ������ﷵ����һҳ ]</a>
					</p>

				</c:if>
				<c:if test="${message=='editsuccess'}">
					<p>
						�޸��û���Ϣ�ɹ���3�����ת����̳��ҳ��
					</p>
					<script type="text/javascript">
		        setTimeout("location.href='index.jsp'",3000);
		        </script>
					<div id="time"></div>
				</c:if>
				<c:if test="${message=='editediterror'}">
					<p>
						�޸��û���Ϣʧ�ܡ�
					</p>
					<p>
						<a href="javascript:history.back()">[ ������ﷵ����һҳ ]</a>
					</p>
				</c:if>
				<c:if test="${message=='guanshui'}">
					<p>
						��ֹ��ˮ��������15���Ժ��ٽ��д˲�����
					</p>
					<p>
						<a href="javascript:history.back()">[ ������ﷵ����һҳ ]</a>
					</p>
				</c:if>
				<c:if test="${message=='addsuccess'}">
					<p>
						�û�ע��ɹ���3�����ת����̳��ҳ��
					</p>
					<p>
						<script type="text/javascript">
						//location.href="login.do?flag=login&userName=${userName}&userPassword=${userPassword}&pointInfo=register";
		               setTimeout("location.href='login.do?flag=login&userName=${userName}&userPassword=${userPassword}&pointInfo=register'",3000);
		        </script>
					</p>
				</c:if>
				<c:if test="${message=='adderror'}">
					<p>
						�û�ע��ʧ�ܡ�
					</p>
					<p>
						<a href="savaToken.do?path=register">[ ������ﷵ��ע��ҳ ]</a>
					</p>
				</c:if>

				<c:if test="${bunUser.bunState==3&&out==null}">
					<p>
						���û�����ֹ���ʡ���ֹ����ʱ�䵽${bunUser.overTime}������
					</p>
					<p>
						<a href="login.do?flag=out">[ �����������������ҳ ]</a>
					</p>
				</c:if>
				<c:if test="${out!=null}">
					<p>
						�˳��ɹ���3�����ת����̳��ҳ��${bunUser.bunState}
					</p>
					<script type="text/javascript">
		           setTimeout("location.href='index.jsp'",3000);
		        </script>
					<p>
						<a href="web/login.jsp">[ ��������������ص�¼ҳ ]</a>
					</p>
				</c:if>
				<c:if test="${bunUser.bunState!=3&&bunUser.bunState!=null&&out==null&&param.post==null&&message==null}">
					<p>
						��¼�ɹ���3�����ת����̳��ҳ!
					</p>
					<script type="text/javascript">
		           setTimeout("location.href='index.jsp'",3000);
		        </script>
					<p>
						<a href="index.jsp">[ �����������������ҳ ]</a>
					</p>
				</c:if>
				<c:if test="${bunUser.bunState==2&&param.post!=null}">
					<p>
						����Ȩ���е�ǰ�����������������ԭ��֮һ���
					</p>
					<p>
						<b>�����ڵ��û���(��ֹ����)�޷����д˲��������ʱ��Ϊ${bunUser.overTime}</b>
					</p>
					<p>
						���Ѿ���¼���������ʺŻ������ڵ��û�����Ȩ���ʵ�ǰҳ�档
					</p>
				</c:if>
			</div>
			<jsp:include page="/foot.jsp" />
		</center>
	</body>
</html>

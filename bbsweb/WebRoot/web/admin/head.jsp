<%@ page contentType="text/html;charset=GB2312"%>
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
		<title></title>
		<base href="<%=basePath%>">
		<link rel="stylesheet" type="text/css" id="css"
			href="css/admincp.css" />
		<script>

//var menus = new Array('basic', 'forums', 'users', 'posts', 'extends', 'others', 'safety', 'tools', 'home');
//���� ---��չ���á���̳��ȫ
var menus = new Array('basic', 'forums', 'users', 'posts', 'others', 'tools', 'home');


function sethighlight(n) {
	var lis = document.getElementsByTagName('li');
	for(var i = 0; i < lis.length; i++) {
		lis[i].id = '';
	}
	lis[n].id = 'menuon';
}
</script>
		<script src="js/menu.js" type="text/javascript"></script>
	</head>
	<body style="width:100%">
		<table border="0" cellpadding="0" cellspacing="0" class="topmenubg" style="width:100%">
			<tr>
				<td rowspan="2" width="175">
					<div class="logo">
						<a href="//" target="_blank"><!--  <img
								src="images/admincp/logo.gif" alt="JF!"
								class="logoimg" border="0" />-->
						</a>
						<span class="editiontext"><%=Snippet.BBS_NAME%><br />��̨����</span>
					</div>
				</td>
				<td>
					<div class="topmenu">
						<ul>
							<li>
								<span><a href="displayUser.do?method=show"
									target="main" onClick="sethighlight(0);">�û�����</a>
								</span>
							</li>
							<li>
								<span><a
									href="displaySection.do?method=show"
									target="main" onClick="sethighlight(1); ">������</a>
								</span>
							</li>
							<li>
								<span><a
									href="displayTopic.do?method=show&resultState=0"
									target="main" onClick="sethighlight(2);">���ӹ���</a>
								</span>
							</li>
						</ul>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>

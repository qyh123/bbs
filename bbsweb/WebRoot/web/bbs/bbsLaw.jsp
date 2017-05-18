<%@ page language="java" pageEncoding="gb2312"%>
<%@ page import="snippet.Snippet"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%>
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><%=Snippet.BBS_NAME%>-站规</title>
        <base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
			<link href="css/style_1.css" rel="stylesheet" type="text/css">
	</head>

	<body><center>
		<jsp:include page="/top.jsp"></jsp:include>
		
		<div class="box message" align="left" style="width: 1003px">
		<h1>
				<%=Snippet.BBS_NAME%>站规！
		</h1>
		<p>
			<a href="web/bbs/bbsLaw.jsp#one">一、服务条款的确认和接纳</a><br>
<a href="web/bbs/bbsLaw.jsp#two">二、权利及义务</a><br>
<a href="web/bbs/bbsLaw.jsp#three">三、关于网络知识产权</a><br>
<a href="web/bbs/bbsLaw.jsp#four">四、关于责任</a><br>
<a href="web/bbs/bbsLaw.jsp#five">五、服务条款的修改和服务修订</a><br>
<a href="web/bbs/bbsLaw.jsp#six">六、通告</a><br>
<a href="web/bbs/bbsLaw.jsp#seven">七、法律管辖</a><br>
</p>
<h2>内容：</h2>

    JF是一个面向中国软件和软件人的综合社区网站。我们希望您能够在JF上交流、学习、进步。<br>

<p id="one">一、服务条款的确认和接纳</p>
    JF的各项电子服务的所有权和运作权归世纪乐知(北京)网络技术有限公司。JF提供的服务将完全按照其发布的章程、服务条款和操作规则严格执行。会员必须完全同意所有服务条款并完成注册程序，才能成为JF的正式注册会员享受更JF提供的更全面的服务。

<p id="two">二、权利及义务</p>

JF的权利义务：<br>
<p>
1、尊重会员隐私制度<br>
     尊重会员个人隐私、保障会员隐私安全是JF的一项基本政策，JF不会公开、编辑或透露会员的注册资料，除非符合以下情况：<br>
    (1) 根据中华人民共和国国家安全机构、公安部门的要求及根据相应的法律程序要求。<br>
    (2) 维护JF的商标所有权及其它权益。<br>
    (3) 在紧急情况下竭力维护会员个人、其它社会个体和社会大众的安全。<br>
    (4) 严重违反JF有关规定。<br>
    JF保留结束会员使用网络服务资格的权利，并保证结束会员资格后仍为会员保密所有个人隐私。<br>
</p>
<p>2、维护JF网站的正常工作，提供网络服务。</p>

<p>3、会员管理<br>
    JF对会员的管理依据国家法律、地方法律和国际法律等的标准，同时会员在论坛上的行为将受JF论坛行为准则的约束。<br>
</p>
<p>4、对会员信息的存储和限制<br>
    JF不对会员所发布信息的删除或储存失败任何法律负责。JF有判定会员的行为是否符合JF服务条款的要求和精神的保留权利，如果会员违背了服务条款的规定，JF有权选择适当的处理方法直至取消会员资格的权利。<br>
</p>

会员的权利义务：<br>
<p>
1、会员必须遵循：<br>
(1)中国关于网络和BBS的相关法规。<br>
(2)使用网络服务不作非法用途。<br>
(3)不干扰或混乱网络服务。<br>
(4)遵守所有使用网络服务的网络协议、规定、程序和惯例。<br>
</p>
<p>
同时会员承诺：<br>
(1)不传输任何非法的、骚扰性的、中伤他人的、辱骂性的、恐吓性的、伤害性的、庸俗的，淫秽等信息资料；<br>
(2)不传输任何教唆他人构成犯罪行为的资料；<br>
(3)不传输任何不符合当地法规、国家法律和国际法律的资料；<br>
(4)未经许可而非法进入其它电脑系统是禁止的；<br>
(5)法律规定的其他义务；<br>
(6)在论坛讨论的时候接受JF论坛行为准则；<br>
(7)接受网管和版主对矛盾纠纷的调节，并配合相关行动决定，以行使投诉权力来解决纠纷。<br>

     若会员的行为不符合以上提到的服务条款，JF将作出独立判断，并采用相关行动。会员需对自己在网上的行为承担法律责任。会员若在JF上散布和传播反动、色情或其他违反国家法律的信息，JF将完全配合司法机关的行动。<br>
</p>
<p>2、基于网络服务的特性及重要性，会员同意：<br>
(1)不断更新注册资料，符合及时、详尽、准确的要求。<br>
(2)自行配备上网的所需设备， 包括个人电脑、调制解调器或其他必备上网装置。<br>
(3)自行负担个人上网所支付的与此服务有关的电话费用、 网络费用。<br>
</p>
<p>3、会员的帐号、密码和安全性 <br>
    会员一旦注册成功，成为JF的合法会员，将得到一个密码和会员名。会员对自己的帐号、密码和安全将负全部责任。<br>
    会员同意若发现任何非法使用该用户帐号或其它有损用户利益的情况，立即通告JF。JF在收到正确的email或者用户身份证传真件后，可以对用户密码进行更改。用户亦可在输入正确的email地址，自己更改密码。<br>
    JF建议会员定期更换密码，避免不必要的密码泄漏。另外，每个会员都要对以其会员名进行的所有活动和事件负全责。<br>
</p>
<p>4、会员发布宣传及广告信息<br>
    会员在他们发表的信息中加入宣传资料或参与广告策划、人才招聘等，在JF的免费服务上展示他们的产品，任何这类促销方法，包括运输货物、付款、服务、商业条件、担保及与广告有关的描述都只是在相应的会员和广告销售商之间发生。JF不承担任何责任，JF没有义务为这类广告销售负任何一部分的责任。会员需要对此承担全部责任。<br>
</p>
<p>5、会员同意保障和维护JF全体会员的利益。<br>
</p>
<p id="three">三、关于网络知识产权</p>
1、会员对自己发表的文章拥有版权。<br>
2、JF对所有发表在JF的文章拥有使用权。<br>
3、如果转载文章请注名作者和出处。
4、如果发生版权纠纷，JF有权在纠纷解决前删除有纠纷的文章<br>
5、转载JF的文章必须得到该文章作者或者JF的授权，并标明作者和出处。<br>

<p id="four">四、关于责任</p>
      JF对任何直接、间接、偶然、特殊及继起的损害不负法律责任，这些损害可能来自：他人的行为、会员间文件的交流、在网上进行交易，非法使用网络服务或用户传送的信息有所变动。这些行为都有可能会导致JF的形象受损，所以JF事先提出这种损害的可能性。<br>
JF对本身提供的有偿服务按照相关法律承担责任。

<p id="five">五、服务条款的修改和服务修订</p>
      JF有权在必要时修改服务条款，JF服务条款一旦发生变动，将会在重要页面上提示修改内容。如果不同意所改动的内容，会员可以在指定的讨论区中讨论、投诉或者申请取消网络服务，但是在修正前必须执行现行的服务条款。JF保留随时修改或中断服务而不需知照用户的权利。JF行使修改或中断服务的权利，不需对用户或第三方负责。<br>
对于涉及有偿服务的改动JF承担相关法律责任。<br>

<p id="six">六、通告</p>
      JF所有发给会员的通告都可通过重要页面的公告或电子邮件。服务条款的修改、服务变更、或其它重要事件的通告都会以此形式进行。<br>
对于JF的系统通告email用户必须接收，但是一般提供退定功能，对于不可以退定的email通告，用户有权投诉。<br>

<p id="seven">七、法律管辖</p>
      网络服务条款要与中华人民共和国的法律解释相一致，会员和JF一致同意服从中国法院管辖。如发生JF服务条款与中华人民共和国法律相抵触时，以法律为准，同时这些条款将完全按法律规定重新解释，而其它条款则依旧保持对会员产生法律效力和影响。<br>

      有限公司对以上条款有最终解释权。  <br>

</div>
		

		<jsp:include page="/foot.jsp"></jsp:include>
		</center>
	</body>

</html>


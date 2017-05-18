//����XMLHttpRequest����ʵ��
var http_request = false;
//����ɸ��õ�http�����ͺ���
function send_request(method,url,content,responseType,callback) {//��ʼ����ָ������������������ĺ���
	http_request = false;
	//��ʼ��ʼ��XMLHttpRequest����
	if(window.XMLHttpRequest) { //Mozilla �����
		http_request = new XMLHttpRequest();
		if (http_request.overrideMimeType) {//����MiME���
			http_request.overrideMimeType("text/xml");
		}
	}
	else if (window.ActiveXObject) { // IE�����
		try {
			http_request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				http_request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {}
		}
	}
	if (!http_request) { // �쳣����������ʵ��ʧ��
		window.alert("���ܴ���XMLHttpRequest����ʵ��.");
		return false;
	}
	if(responseType.toLowerCase()=="text") {
		//http_request.onreadystatechange = processTextResponse;
		http_request.onreadystatechange = callback;
	}
	else if(responseType.toLowerCase()=="xml") {
		//http_request.onreadystatechange = processXMLResponse;
		http_request.onreadystatechange = callback;
	}
	else {
		window.alert("��Ӧ����������");
		return false;
	}
	// ȷ����������ķ�ʽ��URL�Լ��Ƿ��첽ִ���¶δ���
	if(method.toLowerCase()=="get") {
		http_request.open(method, url, true);
	}
	else if(method.toLowerCase()=="post") {
		http_request.open(method, url, true);
		http_request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	}
	else {
		window.alert("http��������������");
		return false;
	}
	http_request.send(content);
}
// �������ı���ʽ��Ϣ�ĺ���
function processTextResponse() {
	if (http_request.readyState == 4) { // �ж϶���״̬
		if (http_request.status == 200) { // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
			//alert(http_request.responseText);
			alert("Text�ĵ���Ӧ��");
		} else { //ҳ�治����
			alert("���������ҳ�����쳣��");
		}
	}
}
//�����ص�XML��ʽ�ĵ��ĺ���
function processXMLResponse() {
	if (http_request.readyState == 4) { // �ж϶���״̬
		if (http_request.status == 200) { // ��Ϣ�Ѿ��ɹ����أ���ʼ������Ϣ
			//alert(http_request.responseXML);
			alert("XML�ĵ���Ӧ��");
		} else { //ҳ�治����
			alert("���������ҳ�����쳣��");
		}
	}
}

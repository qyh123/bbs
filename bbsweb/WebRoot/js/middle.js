
function checkAll() {
	var eles = document.getElementsByName("chkButton");
	for (var i = 0; i < eles.length; i++) {
		if (i != 0) {
			eles[i].checked = eles[0].checked;
		} else {
			if (eles[0].value == 0) {
				eles[0].checked = true;
				eles[0].value = 1;
			} else {
				eles[i].checked = false;
				eles[0].value = 0;
			}
		}
	}
}
function getSelectedCheckBoxNumber() {
	var recordNumber = 1;
	var k = 0;
	var checkbutton = document.getElementsByName("chkButton");
	recordNumber = checkbutton.length;
	for (var i = 0; i < recordNumber; i++) {
		if (checkbutton[i].checked) {
			k = k + 1;
		}
	}
	return k;
}

//div��tr��tbody����ʾ
function display1(trid, imgid) {
	if (trid.style.display == "") {
		trid.style.display = "none";
		eval(imgid).src = "/bbsweb/images/admincp/menu_add.gif";
	} else {
		trid.style.display = "";
		eval(imgid).src = "/bbsweb/images/admincp/menu_reduce.gif";
	}
}
//��ҳ������չ��
function collapsed(trid, imgid) {
	if (document.getElementById("category_1").style.display == "") {
		document.getElementById("category_1").style.display = "none";
		document.getElementById("category_1_img").src = "/bbsweb/images/default/collapsed_yes.gif";
		//eval(document.getElementById(imgid)).src = "/bbsweb/images/default/collapsed_yes.gif";
	} else {
		document.getElementById("category_1").style.display = "";
		document.getElementById("category_1_img").src = "/bbsweb/images/default/collapsed_yes.gif";
		//eval(document.getElementById(imgid)).src = "/bbsweb/images/default/collapsed_no.gif";
	}
}
function search(str) {
	if (str == 1) {
		if (document.getElementsByName("sectionSearch")[0].value == "") {
			alert("\u8bf7\u8f93\u5165\u641c\u7d22\u5185\u5bb9\uff01");
			document.getElementsByName("sectionSearch")[0].focus();
			return false;
		}
		document.searchForm.submit();
		return true;
	} else {
		if (document.getElementsByName("userSearch")[0].value == "") {
			alert("\u8bf7\u8f93\u5165\u641c\u5bfb\u5185\u5bb9\uff01");
			document.getElementsByName("userSearch")[0].focus();
			return false;
		}
		document.userForm.submit();
		return true;
	}
}

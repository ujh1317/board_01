function writeSave(){
	//alert("aaa")
	if(document.writeform.writer.value==""){
	  alert("작성자를 입력하십시요.");
	  document.writeform.writer.focus();
	  return false;
	}
	if(document.writeform.subject.value==""){
	  alert("제목을 입력하십시요.");
	  document.writeform.subject.focus();
	  return false;
	}
	
	if(document.writeform.content.value==""){
	  alert("내용을 입력하십시요.");
	  document.writeform.content.focus();
	  return false;
	}
        
	if(document.writeform.pw.value==""){
	  alert(" 비밀번호를 입력하십시요.");
	  document.writeform.pw.focus();
	  return false;
	}
	//document.writeform.submit();
	return true;
 }    


function loginCheck(){
	if(document.loginForm.id.value==''){
		alert("id를 입력하시오.");
		document.loginForm.id.focus();
		return false;
	}
	if(document.loginForm.pw.value==''){
		alert("pwd를 입력하시오.");
		document.loginForm.pw.focus();
		return false;
	}
	return true;
}

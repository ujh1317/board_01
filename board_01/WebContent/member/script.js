function inputCheck(){
	
	if($('#id').val()==''){
		alert("아이디를 입력하십시요.");
		$('#id').focus();
		return false;
	}//#id
	if($('#pw').val()==""){
		alert("암호를 입력하십시오.");
		$('#pw').focus();
		return false;
	}//#pw
	if($('#pw2').val()==""){
		alert("암호 확인을 입력하십시오.");
		$('#pw2').focus();
		return false;
	}//#pw2
	
	if($('#pw').val()!=$('#pw2').val()){
		alert("암호가 일치하지 않습니다.");
		$('#pw2').val('');
		$('#pw2').focus();
		return false;
	}//#pw=#pw2

	if($('#name').val()==""){
		alert("이름을 입력하십시오.");
		$('#name').focus();
		return false;
	}//#name
	if($('#nick').val()==''){
		alert("닉네임을 입력하십시요.");
		$('#nick').focus();
		return false;
	}//#nick
	if($('#jumin1').val()==""){
		alert("주민번호를 입력하십시오.");
		$('#jumin1').focus();
		return false;
	}//#jumin1
	if($('#jumin2').val()==""){
		alert("주민번호를 입력하십시오.");
		$('#jumin2').focus();
		return false;
	}//#jumin2
	if($('#email').val()==""){
		alert("이메일을 입력하십시오.");
		$('#email').focus();
		return false;
	}//#email
	if($('#zipcode').val()==""){
		alert("우편번호를 입력하십시오.");
		$('#zipcode').focus();
		return false;
	}//#zipcode
	if($('#addr').val()==""){
		alert("주소를 입력하십시오.");
		$('#addr').focus();
		return false;
	}//#addr
	
	return true;
 }//inputCheck()

function confirmIdCheck(){
	
	if($('#id').val()==''){
		alert("아이디를 입력하십시오.");
	}else{
		$.ajax({
			type:'POST',
			url:'confirmId.jsp',
			data:"id="+$('#id').val(),
			dataType:'JSON',
			success:function(data){
				if(data.check==1){
					alert("사용중인 id입니다.");
					$('#id').val('').focus();
				}else{
					alert("사용가능한 id입니다.");
					$('#idCheck').val('1');
					$('#pw').focus();
				}//else
			}//function(data)
		});//ajax
	}//else
}//confirmIdCheck()

function onfocus_id(){
	if($('#idCheck').val()=='-1'){
		alert("ID중복체크를 하십시오.");
		$('#id').focus();
		return false;
	}//if
	return true;
}//onfocus_id()
	
function filterNumber(event){
	var code = event.keyCode;
	if(code>47 && code<58){
		return;
	}//if
	if(code==8 || code==9 || code==37 || code==39 || code==46 || code==35 || code==36){
		return;
	}
	event.preventDefault();
}//filterNumber

function openDaumPostcode(){
	   new daum.Postcode({
	      oncomplete:function(data){
	         document.getElementById('zipcode').value=data.zonecode;
	         document.getElementById('addr').value=data.address;
	       }
	   }).open();
	}//openDaumPostcode()



<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


<div class="container" style="margin-top: 50px">
	<form class="form-horizontal" id="sendForm" name="sendForm" action="/user/userInsertPro.do" method="POST">
	    <div class="form-group">
	      <label class="col-sm-2 control-label">ID</label>
	      <div class="col-sm-4">
	        <input class="form-control" id="userId" name="userId" type="text" value="" title="ID">
	      </div>

	      <div class="container">
	      	<button type="button" onclick="check();" class="btn btn-default" style="display: block;">ID 중복 체크</button>
	      </div>

	    </div>

	    <div class="form-group">
	      <label for="disabledInput " class="col-sm-2 control-label">패스워드</label>
	      <div class="col-sm-4">
	        <input class="form-control" id="pwd" name="pwd" type="password" title="패스워드" >
	      </div>
	      <label for="disabledInput " class="col-sm-2 control-label">패스워드 확인</label>
	      
	      <div class="col-sm-4">
	        <input class="form-control" id="pwdck" name="pwdck" type="password" title="패스워드 확인">
	      </div>
	    </div>

	    <div class="form-group">
	      <label for="disabledInput" class="col-sm-2 control-label">이름</label>
	      <div class="col-sm-4">
	        <input class="form-control" id="userName" name="userName" type="text" value="" title="이름" >
	      </div>
	    </div>


	    <div class="col-md-offset-4">
			<button type="button" onclick="saveBtn();" class="btn btn-primary">저장</button>
			<button type="button" id="#"class="btn btn-danger" onclick="location.href='/login/login.do'">취소</button>
	    </div>
	</form>
</div>

<script type="text/javascript">
    var flag = 0; //중복체크 서브밋방지

  function saveBtn(){
	
	console.log("123");
	var userId = $("#userId").val();
    var pwd = $("#pwd").val();
    var pwdck = $("#pwdck").val();
    var userName = $("#userName").val();
  	//아이디 값 데이터 정규화 공식
    var regul1 = /^[a-zA-Z0-9]{6,}$/;
    // 패스워드값 데이터 정규화 공식
    var regul2 = /^(?=.*[a-zA-Z])(?=.*[`!@#$%^&*()+=-_|])(?=.*[0-9]).{6,12}$/;
 	 //이름 정규화 공식
    var regul3 = /^[가-힝a-zA-Z]{2,}$/;
    //[]안에 있는 값만 쓰겠다
    
    
    	console.log("456");
	    if (userId == ""){
	        alert("아이디를 입력하지 않았습니다.");
	        userId.focus();
	    }
	    else if(!regul1.test(userId)) {
	    	alert("아이디는 6~12자만 입력 가능합니다.");
	    }
	    else if(flag == 0){
	    	alert("중복된 ID입니다.");
	    	console.log(flag);
	    }
	    else if ((pwd.value) == ""){
	        alert("비밀번호를 입력해 주세요");
	        pwd.focus();
	    }
	    else if ((pwdck.value=="")){
	        alert("비밀번호를 입력해 주세요");
	        pwdck.focus();
	    }
	    else if (!regul2.test(pwd)) {
	    	alert("숫자,영문자,특수문자 조합으로 6~12자리 입력해주세요.");
	    }
	    else if (pwd != pwdck) {
	        alert("비밀번호가 일치 하지 않습니다.");
	        pwd.focus();
	    }
	    else if ((userName.value)=="") {
	        alert("이름을 입력해 주세요");
	        userName.focus();
	    }
	    else if (!regul3.test(userName)) {
	    	alert("이름을 2자이상 입력하세요");
	    }
	    else{
	    	sendForm.submit();
	    }
    } 
    
 
 
 	// ID 중복체크
	function check(){
		id = $("#userId").val();// 아이디 벨류값
		$.ajax({
			url : '/idcheck.do', // id 체크 유알엘
			type : 'POST',  // 포스트
			dataType :'json', //서버로부터 내가 받는 데이터의 타입
			data : {"userId" : $("#userId").val()}, //db조회용 아이디
			success: function(data){
				//data.userId = 조회친 아이디 
				//id = 기존 아이디
				if(data.userId == id){
					alert("중복된 아이디 입니다.");
				}else if(data.userId != id){
					alert("사용가능한 아이디 입니다.");
					flag = 1;
				}
			},
			error: function(){
				alert("오류");
			}
		});
	}

</script>

<!-- 
	//아이디 입력 하지 않았을 경우
    if (userId == ""){
        alert("아이디를 입력하지 않았습니다.");
        userId.focus();
        return false;
    }
    //아이디 유효성 검사
    //내가 입력한 데이터를 검사하는 check()
    //만약 내가 아이디에 정규화 방식을 하나라도 지키지 않으면 if문 안으로 들어가서 alert message를 띄움
    if (!check(regul1,userId,"아이디는 6~12자만 입력 가능합니다.")) {
        return false;//반환 할 곳 없이 if문 탈출
    }
    //비밀번호 입력 하지 않았을 경우
    if ((pwd.value) == ""){
        alert("비밀번호를 입력해 주세요");
        pwd.focus();
        return false;
    }
    if ((pwdck.value=="")){
        alert("비밀번호를 입력해 주세요");
        pwdck.focus();
        return false;
    }

    //비밀번호 유효성 검사
    //만약 내가 비밀번호에 정규화 방식을 하나라도 지키지 않으면 if문 안으로 들어가서 alert message를 띄움
    if (!check(regul2,pwd,"숫자,영문자,특수문자 조합으로 6~12자리 입력해주세요.")) {
        return false;
    }
    
    //비밀번호와 비밀번호 확인이 일치 하지 않을 경우
    if ((pwd.value)!=(pwdck.value)) {
        alert("비밀번호가 일치 하지 않습니다.");
        pwd.focus();
        pwdck.focus();
        return false;
    }
    
    //이름 입력 안 한 경우
    if ((userName.value)=="") {
        alert("이름을 입력해 주세요");
        userName.focus();
        return false;
    }
    //이름에 특수 문자가 들어간 경우
    if (!check(regul3,userName,"이름이 잘못 되었습니다.")) {
        return false;
    } 
    
    -->

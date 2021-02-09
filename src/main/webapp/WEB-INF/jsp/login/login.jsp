<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


<form id="sendForm" name="sendForm" method="POST" action="/account/accountList.do">
	<div class="container col-md-offset-2 col-sm-6" style="margin-top: 100px;">
		<div class="input-group">
			<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
			<input id="userId" type="text" class="form-control valiChk" name="userId" placeholder="id" title="ID">
		</div>
		<div class="input-group">
			<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
			<input id="pwd" type="password" class="form-control valiChk" name="pwd" placeholder="Password" title="Password">
		</div>
			<br />
		<br>
		<div class="col-md-offset-4">
			<button type="button" onclick="loginBtn();" class="btn btn-primary">로그인</button>
			<button type="reset" id="#" class="btn btn-warning" onclick="">취소</button>
			<button type="button" id="#" class="btn btn-info" onclick="location.href='/user/userInsert.do'">회원가입</button>
		</div>
	</div>
</form>


<script type="text/javascript">
	
	function loginBtn(){
		id = $("#userId").val();
		pwd = $("#pwd").val();
		$.ajax({
			url : '/idCkedAjax.do',
			type : 'POST',
			dataType : 'json',
			data : {"userId" : $("#userId").val(), "pwd" : $("#pwd").val()},
			success : function(data){
				if(data.userId == id && data.pwd == pwd){
					alert("로그인 성공");
					sendForm.submit();
				}else if(id == '' || pwd == ''){
					alert("아이디 또는 비밀번호를 입력해주세요");
				}else if(data.userId != id && data.pwd != pwd){
					alert("아이디 또는 비밀번호가 틀렸습니다.");
				}
			}
		});
	}

</script>

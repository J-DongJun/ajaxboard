<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


<form name="sendForm" id="sendForm" method="post" onsubmit="return false;">

<input type="hidden" id="account_seq" name="account_seq" value="">
<input type="hidden" id="mode" name="mode" value="Cre">

<div id="wrap"  class="col-md-offset-1 col-sm-10" >
		<div align="center"><h2>회계정보리스트</h2></div>
		<div class="form_box2 col-md-offset-7" align="right" >
			<div class="right" >
				<button class="btn btn-primary" onclick="location.href='/account/accountInsert.do'" >등록</button>
				<button class="btn btn-primary" onclick="location.href='/account/downloadExcl.do'">엑셀 다운</button>
			</div>
		</div>
	    <br/>
		<table class="table table-hover">
			    <thead>
			      <tr align="center">
			        <th style="text-align: center;" >수익/비용</th>
			        <th style="text-align: center;" >관</th>
			        <th style="text-align: center;" >항</th>
			        <th style="text-align: center;" >목</th>
			        <th style="text-align: center;" >과</th>
			        <th style="text-align: center;" >금액</th>
			        <th style="text-align: center;" >등록일</th>
			        <th style="text-align: center;" >작성자</th>
			      </tr>
			      
				      
			      		<c:forEach items="${resultMap}" var="list">
			      		<tr align="center">
				      		<th style="text-align: center;" >${list.PROFIT_COST}</th>
				      		<th style="text-align: center;" >${list.BIG_GROUP}</th>
				      		<th style="text-align: center;" >${list.MIDDLE_GROUP}</th>
			      			<th style="text-align: center;" >${list.SMALL_GROUP}</th>
			      			<th style="text-align: center;" >${list.DETAIL_GROUP}</th>
				      		<th style="text-align: center;" >${list.TRANSACTION_MONEY}</th>
				      		<c:set var="TRANSACTION_DATE" value="${list.TRANSACTION_DATE}" />
				      		<%-- <th id="txtDate" style="text-align: center;" ><fmt:formatDate value="${list.REG_DATE}" pattern='yyyy년 MM월 dd일'/></th> --%>
				      		<th style="text-align: center;" >${fn:substring(TRANSACTION_DATE,0,11)}</th>
				      		<th style="text-align: center;" >${list.WRITER}</th>
				      		</tr>
			      		</c:forEach>
			      		
			    </thead>
			    <tbody>
				

			    </tbody>
			</table>
			<ui:pagination paginationInfo = "${paginationInfo}"
			type="image"
			jsFunction="linkPage"/>
</div>
</form> 

<script type="text/javascript">
	function linkPage(pageNo){
		location.href = "/account/accountList.do?pageNo="+pageNo;
	}
	
</script>



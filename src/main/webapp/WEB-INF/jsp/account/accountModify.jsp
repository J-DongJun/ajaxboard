<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

 
 
 <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
 
 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<!-- 비용 START -->
<div class="container" style="margin-top: 50px">
	<div class="col-sm-12">
		<label for="disabledInput" class="col-sm-12 control-label"></label>
	</div>
	<div class="col-sm-12">
		<label for="disabledInput" class="col-sm-12 control-label"></label>
	</div>
	<div class="col-sm-12">
		<label for="disabledInput" class="col-sm-12 control-label"></label>
	</div>
	<div class="col-sm-12">
		<label for="disabledInput" class="col-sm-12 control-label"></label>
	</div>



	<div class="col-sm-11" id="costDiv">
		<div>
		<form class="form-horizontal" id="sendForm" name="sendForm" action="/account/accountModifyPro.do" method="POST">
			<input type="hidden" id="account_seq" name="account_seq" value="${seqResultMap.ACCOUNT_SEQ}">
			<div class="col-sm-11">   
				<div class="col-sm-12">     
					<div class="col-sm-3">   
						<select class="form-control" id="profitCost" name="profitCost" title="비용" >
									<c:forEach var="list" items="${resultMap}" varStatus="cnt" >   
										<option id="profitOption" value="${list.code}"
										 	<c:if test="${list.comKor eq model.PROFIT_COST}"> selected</c:if>
										 	>${list.comKor} 
										</option>      
									</c:forEach>  
						</select>          
					</div>

					<div class="col-sm-3">
						<select class="form-control" id="bigGroup" name="bigGroup" title="관">
							<c:set var="BIG_GROUP" value="${model.BIG_GROUP}" />
									<c:forEach var="list" items="${resultMap0}" varStatus="cnt" >   
										<option id="bigOption" value="${list.code}"
										 	<c:if test="${list.comKor eq model.BIG_GROUP}"> selected</c:if>
										 	>${list.comKor}
										</option>     
									</c:forEach>  
						</select>
					</div>    
 
					<div class="col-sm-3">
						<select class="form-control" id="middleGroup" name="middleGroup" title="항">
							<c:set var="MIDDLE_GROUP" value="${model.MIDDLE_GROUP}" />
									<c:forEach var="list" items="${resultMap1}" varStatus="cnt" >   
										<option id="middleOption" value="${list.code}"
										 	<c:if test="${list.comKor eq model.MIDDLE_GROUP}"> selected</c:if>
										 	>${list.comKor}
										</option>     
									</c:forEach>  
						</select>
					</div>

					<div class="col-sm-3">
						<select class="form-control" id="smallGroup" name="smallGroup" title="목">
							<c:if test="${model.SMALL_GROUP ne '대분류선택'}">
							<c:set var="SMALL_GROUP" value="${model.SMALL_GROUP}" />
									<c:forEach var="list" items="${resultMap2}" varStatus="cnt" >   
										<option id="smallOption" value="${list.code}"
										 	<c:if test="${list.comKor eq model.SMALL_GROUP}"> selected</c:if>
										 	>${list.comKor}
										</option>     
									</c:forEach>  
							</c:if> 
							<c:if test="${model.SMALL_GROUP eq '대분류선택'}">
							<option value="0" >해당없음</option>
							</c:if>
						</select>
					</div>
				</div>

				<div class="col-sm-12">
					<label for="disabledInput" class="col-sm-12 control-label">
					</label>
				</div>
				<div class="col-sm-12">
					<div class="col-sm-3">
						<select class="form-control" id="comment1" name="comment1" title="과">
							<c:if test="${model.DETAIL_GROUP ne '대분류선택'}">
							<c:set var="DETAIL_GROUP" value="${model.DETAIL_GROUP}" />
									<c:forEach var="list" items="${resultMap3}" varStatus="cnt" >   
										<option id="commentOption" value="${list.code}"
										 	<c:if test="${list.comKor eq model.DETAIL_GROUP}"> selected</c:if>
										 	>${list.comKor}
										</option>     
									</c:forEach>  
							</c:if> 
							<c:if test="${model.DETAIL_GROUP eq '대분류선택'}">
							<option value="0">해당없음</option>
							</c:if>  
						</select>
					</div> 
					<div class="col-sm-9">
						<input class="form-control " id="comment" name="comment" type="text" value="${seqResultMap.COMMENTS}"
							placeholder="비용 상세 입력" title="비용 상세">
					</div>
				</div>

				<div class="col-sm-12">
					<label for="disabledInput" class="col-sm-12 control-label">
					</label>
				</div>
				<div class="col-sm-12">
					<label for="disabledInput" class="col-sm-1 control-label"><font
						size="1px">금액</font></label>
					<div class="col-sm-3">
						<input class="form-control" id="transactionMoney" name="transactionMoney" type="text"
							value="${seqResultMap.TRANSACTION_MONEY}" title="금액" numberonly="true">
					</div>
					<label for="disabledInput" class="col-sm-1 control-label"><font
						size="1px">거래일자</font></label>
					<div class="col-sm-3">
						
						<input class="form-contro col-sm-2" id="transactionDate" 
							name="transactionDate" type="text" value="<fmt:formatDate value="${seqResultMap.TRANSACTION_DATE}" pattern='yyyy-MM-dd'/>" style="width: 80%" title="거래일자">
					</div>
				</div>
				<div class="col-md-offset-4">
					<button type="submit" id="saveBtn" class="btn btn-primary">수정</button>
					<button type="reset" id="#" class="btn btn-warning"
						onclick="location.href='/account/accountList.do'">취소</button>
				</div>
				<div class="col-sm-12">
					<label for="disabledInput" class="col-sm-12 control-label"></label>
				</div>
				<div class="col-sm-12">
					<label for="disabledInput" class="col-sm-12 control-label"></label>
				</div>
			</div>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript">
/*   (
function(){ 
	// 입력값 수익이랑 같을때
        if("${resultMap.get(0).comKor}" == "${model.PROFIT_COST}"){
        	
        }else{
        	// 입력값 비용이랑 같을때
        	
        }
		
})()   
 */
	
	
	$('#saveBtn').click(function(){
	//	$("#smallGroup").removeAttr("disabled");
		//$("#comment1").removeAttr("disabled");
	if($('#comment').val() == '' || $('#transactionMoney').val() == '' || $('#transactionDate').val() == ''){
		alert("금액 및 거래일자를 등록해주세요.");
		return false;
	}
}); 
	
	$("#profitCost").change(function(){
		var profitCost = $('#profitCost').val();
		var bigGroup = $('#bigGroup').val();
		$.ajax({
			type: 'POST',
			url: '/account/selectCombo.do',
			dataType: 'json',
			data: {'category' : profitCost},
			success: function(data){  
				console.log(profitCost.length)
				console.log(data)
				console.log(data.result)
				console.log(data.result[0].code)  
				console.log(data.result[0].comKor)
				for(var i=0;i < data.result.length; i++){
					  bigGroup +=  '<option value="' + data.result[i].code + '"selected>' + data.result[i].comKor + '</option>';
				}
					
					$('#bigGroup').html(bigGroup); 
					$('#bigGroup').val(data.result[0].code).trigger('change');
					 
					
					// 최상위 select가 수익일때
					 if(data.category == "AA00000"){
						 $('#smallGroup').html($('#smallGroup').attr('disabled', 'true'));
						 $("#smallGroup").append("<option value='0'>해당없음</option>");
						 $('#comment1').html($('#comment1').attr('disabled', 'true'));
						 $("#comment1").append("<option value='0'>해당없음</option>");
						 
					 // 최상위 select가 비용일때
					 }else if(data.category == "AB00000"){
						 $('#smallGroup').html($('#smallGroup').removeAttr('disabled'));
						 $('#comment1').html($('#comment1').removeAttr('disabled'));
						 
					 }
					
				},
				error: function(data){
					alert("선택해주세요");
			}
		});
	});
	
	$("#bigGroup").change(function(){
		var bigGroup = $('#bigGroup').val();
		var middleGroup = $('#middleGroup').val();
		$.ajax({
			type: 'POST',
			url: '/account/selectCombo.do',
			dataType: 'json',
			data: {'category' : bigGroup},
			success: function(data){
				console.log(bigGroup.length)
				console.log(data)
				console.log(data.result)
				console.log(data.result[0].code)
				console.log(data.result[0].comKor)
				
					for(var i=0;i < data.result.length; i++){
						middleGroup += '<option value="' + data.result[i].code + '">' + data.result[i].comKor + '</option>';
					}
					$('#middleGroup').html(middleGroup);
					$('#middleGroup').val(data.result[0].code).trigger('change');
					
					
				},
				error: function(data){
					alert("에러");
			}
		});
	});
	
	$("#middleGroup").change(function(){
		var middleGroup = $('#middleGroup').val();
		var smallGroup = $('#smallGroup').val();
		$.ajax({
			type: 'POST',
			url: '/account/selectCombo.do',
			dataType: 'json',
			data: {'category' : middleGroup},
			success: function(data){
				console.log(profitCost.length)
				console.log(data)
				console.log(data.result)
				console.log(data.result[0].code)
				console.log(data.result[0].comKor)
				
					for(var i=0;i < data.result.length; i++){
						smallGroup += '<option value="' + data.result[i].code + '">' + data.result[i].comKor + '</option>';
					}
					$('#smallGroup').html(smallGroup);
					$('#smallGroup').val(data.result[0].code).trigger('change');
					
				},
				error: function(data){
					alert("에러");
			}
		});
	});
	
	$("#smallGroup").change(function(){
		var smallGroup = $('#smallGroup').val();
		var comment1 = $('#comment1').val();
		$.ajax({
			type: 'POST',
			url: '/account/selectCombo.do',
			dataType: 'json',
			data: {'category' : smallGroup},
			success: function(data){
				console.log(smallGroup.length)
				console.log(data)
				console.log(data.result)
				console.log(data.result[0].code)
				console.log(data.result[0].comKor)
				
				for(var i=0;i < data.result.length; i++){
					comment1 += '<option value="' + data.result[i].code + '">' + data.result[i].comKor + '</option>';
				}
					$('#comment1').html(comment1);
					$('#comment1').val(data.result[0].code).trigger('change');
					
				},
				error: function(data){
					alert("에러");
			}
		});
	});
	
	
	// 금액란 숫자만 입력 구현
	$(document).on("keyup", "input:text[numberOnly]", function(){
		$(this).val( $(this).val().replace(/[^0-9]/gi,"") );
		});
	
	
	
	
	// 거래일자 구현
	jQuery(function() {

	jQuery.each(jQuery("#transactionDate"), function(i) {
		jQuery(this).datepicker({
					monthNamesShort : ['1월', '2월', '3월', '4월', '5월', '6월',
							'7월', '8월', '9월', '10월', '11월', '12월'],
					dayNamesMin : ['일', '월', '화', '수', '목', '금', '토'],
					dateFormat : 'yy-mm-dd',
					showOn : 'both',
					changeMonth : true,
					changeYear : true,
					buttonImage : '/images/egovframework/common/calendar.png',
					buttonImageOnly : true,
					buttonText : "달력",
					yearRange : 'c-50:c+1',
					showButtonPanel : false
				}).css("background-color", "#e1eaf3").attr("readonly", "readonly");
	});

}); // END jQuery
console.log("${seqResultMap.PROFIT_COST}")
console.log("${resultMap.get(0).comKor}")
console.log("${model.PROFIT_COST}")
console.log("${model.resultMap0}")
console.log("${model.resultMap1}")
console.log("${model.resultMap2}")
console.log("${model.resultMap3}")
</script>


<!-- 비용 END -->
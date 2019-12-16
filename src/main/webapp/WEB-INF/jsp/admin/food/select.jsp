<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>admin</title>
<script type="text/javascript" src="/js/lib/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/js/lib/snowfall.jquery.js"></script>
<script type="text/javascript" src="/js/init.js"></script>
<link rel="stylesheet" href="/css/admin.css?201912102013" />
</head>
<body>
<h2>음식 정보 조회 수정</h2>
<script>
	init.ready = function(){
		admin.click();
	}
	
	var admin = {
		click : function(){
			$(document).on("click","button[name='updateBtn']",function(){
				var idx = $(this).attr("id").replace("updateBtn","");
				var formData = new FormData();
				formData.append('file',$("#file"+idx)[0].files[0]);
				formData.append('foodNm',$("#foodNm"+idx).val());
				formData.append('foodCd',$("#foodCd"+idx).val());
				formData.append('step01',$("#step01"+idx+" option:selected").val());
				formData.append('step02',$("#step02"+idx+" option:selected").val());
				formData.append('step03',$("#step03"+idx+" option:selected").val());
				formData.append('step04',$("#step04"+idx+" option:selected").val());
				formData.append('step05',$("#step05"+idx+" option:selected").val());
				formData.append('step06',$("#step06"+idx+" option:selected").val());
				formData.append('step07',$("#step07"+idx+" option:selected").val());
				
				common.ajaxFile('/admin/foodUpdate.do',formData,function(data){
					if(data.result){
						location.href="/admin/foodSelect.do";
					}
				},function(){
					
				});
			});
			
			$(document).on("click","button[name='deleteBtn']",function(){
				var idx = $(this).attr("id").replace("deleteBtn","");
				var formData = new FormData();

				formData.append('foodCd',$("#foodCd"+idx).val());
				
				
				common.ajaxFile('/admin/foodDelete.do',formData,function(data){
					if(data.result){
						location.href="/admin/foodSelect.do";
					}
				},function(){
					
				});
			});
			
		}
	}
</script>

<table class="foodTable">
	<tr>
		<th>음식이름</th>
		<th>step01</th>
		<th>step02</th>
		<th>step03</th>
		<th>step04</th>
		<th>step05</th>
		<th>step06</th>
		<th>step07</th>
		<th>img</th>
		<th>img</th>
		<th></th>
	</tr>
	<c:forEach var="list" items="${list}" varStatus="status">
		<tr>
			<td>
				<c:set var="index" value="${status.index}"/>
				<input type="text" value="${list.CODE_NAME}" id="foodNm${index}"/>
				<input type="hidden" value="${list.FOOD_CD}" id="foodCd${index}"/>
			</td>
			<td >
				<select id="step01${index}">
					<option></option>
					<c:forEach var="step01" items="${step01}" varStatus="status">
						<option value="${step01.CODE_ID}" <c:if test="${step01.CODE_ID eq list.STEP01}">selected</c:if>>${step01.CODE_NAME}</option>
					</c:forEach>
				</select>
			</td>
			<td >
				<select id="step02${index}">
					<option></option>
					<c:forEach var="step02" items="${step02}" varStatus="status">
						<option value="${step02.CODE_ID}" <c:if test="${step02.CODE_ID eq list.STEP02}">selected</c:if>>${step02.CODE_NAME}</option>
					</c:forEach>
				</select>
			</td>
			<td >
				<select id="step03${index}">
					<option></option>
					<c:forEach var="step03" items="${step03}" varStatus="status">
						<option value="${step03.CODE_ID}" <c:if test="${step03.CODE_ID eq list.STEP03}">selected</c:if>>${step03.CODE_NAME}</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<select id="step04${index}">
					<option></option>
					<c:forEach var="step04" items="${step04}" varStatus="status">
						<option value="${step04.CODE_ID}" <c:if test="${step04.CODE_ID eq list.STEP04}">selected</c:if>>${step04.CODE_NAME}</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<select id="step05${index}">
					<option></option>
					<c:forEach var="step05" items="${step05}" varStatus="status">
						<option value="${step05.CODE_ID}" <c:if test="${step05.CODE_ID eq list.STEP05}">selected</c:if>>${step05.CODE_NAME}</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<select id="step06${index}">
					<option></option>
					<c:forEach var="step06" items="${step06}" varStatus="status">
						<option value="${step06.CODE_ID}" <c:if test="${step06.CODE_ID eq list.STEP06}">selected</c:if>>${step06.CODE_NAME}</option>
					</c:forEach>
				</select>
			</td>
			<td >
				<select id="step07${index}">
					<option></option>
					<c:forEach var="step07" items="${step07}" varStatus="status">
						<option value="${step07.CODE_ID}" <c:if test="${step07.CODE_ID eq list.STEP07}">selected</c:if>>${step07.CODE_NAME}</option>
					</c:forEach>
				</select>
			</td>
			<td >
				${list.IMG}
			</td>
			<td >
				<input type="file" id="file${index}" name="file" value="">
			</td>
			<td >
				<button name="updateBtn" id="updateBtn${index}">수정</button>
				<button name="deleteBtn" id="deleteBtn${index}">삭제</button>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
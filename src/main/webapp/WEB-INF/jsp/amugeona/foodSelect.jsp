<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
	<head>
		<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
	</head>
	<body class="is-preload">
		<script type="text/javascript" src="/js/amugeona/foodSelect.js"></script>
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
		<!-- Banner -->
		<form id="frm" action="/amu/mapList.do" method="POST">
			<input type="hidden" id="data" name="data" value="<c:forEach var="list" items="${list}" varStatus="status">${list.FOOD_CD}|</c:forEach>"/>
			<input type="hidden" id="moreData" name="moreData" value="<c:forEach var="list" items="${list}" varStatus="status">${list.FOOD_CD}|</c:forEach><c:forEach var="list" items="${moreList}" varStatus="status">${list.FOOD_CD}|</c:forEach>"/>
			<input type="hidden" id="foodCd" name="foodCd" value=""/>
			<input type="hidden" id="typeData" name="typeData" value="${typeData}"/>
			<input type="hidden" id="stepData" name="stepData" value="${stepData}"/>
			<input type="hidden" id="listLength" name="listLength" value="${fn:length(list)}"/>
			<input type="hidden" id="moreListLength" name="moreListLength" value="${fn:length(moreList)}"/>
			<input type="hidden" id="moreFlag" name="moreFlag" value="N"/>
		</form>
		<div id="middle">
    		<p id="typeTitle">결과 ${fn:length(list)}개</p>
    		<c:if test="${fn:length(list) ne 0}">
				<div class="swiper-container type-contents" style="height:350px;">
					<div class="swiper-wrapper">
					<c:forEach var="list" items="${list}" varStatus="status">
						<div class="swiper-slide" style="background: none;">
							<div class="button style2 scrolly foodListBtn" name="foodBtn" for="foodValue1" style="right: 0px;">
								<input type="hidden" name="foodValue" id="foodValue${status.index}" value="${list.FOOD_CD}">
								<input type="hidden" name="foodNameValue" id="foodNameValue${status.index}" value="${list.CODE_NAME}">
								<div style="display:inline-block;width:30%;"><img src="${list.IMG}" onerror="this.src='/images/common/no_image.jpg'" class="foodImg2"></div>
								<div style="display:inline-block;width:70%;">${list.CODE_NAME}</div>
								
							</div>
						</div>
					</c:forEach>
					<div class="swiper-slide" style="background: none;">
						<label class="button style2 scrolly foodListBtn moreFoodBtn" for="moreFoodBtn" style="right: 0px;height:110px;">
							<font style="margin-top: 30px;display: block;">더 많은 메뉴 보기 ></font>
						</label>
					</div>
					<c:forEach var="moreList" items="${moreList}" varStatus="status">
						<div class="swiper-slide moreFoodList" style="background: none;display:none;">
							<label class="button style2 scrolly foodListBtn" name="foodBtn" for="foodValue1" style="right: 0px;">
								<input type="hidden" name="foodValue" id="foodValue${status.index}" value="${moreList.FOOD_CD}">${moreList.CODE_NAME}<br>
								<input type="hidden" name="foodNameValue" id="foodNameValue${status.index}" value="${moreList.CODE_NAME}">
								<img src="${moreList.IMG}" onerror="this.src='/images/common/no_image.jpg'" class="foodImg2">
							</label>
						</div>
					</c:forEach>
					</div>
					<!-- Add Pagination -->
					<c:if test="${fn:length(list) ne 1}">
			        <div class="swiper-pagination"></div>
			        <!-- Add Arrows -->
			        <div class="swiper-button-next"></div>
			        <div class="swiper-button-prev"></div>
			        </c:if>
				</div>
				<c:if test="${fn:length(list) eq 1}">
					<a href="#" class="button scrolly centerBtn" id="mapBtn">근처맛집찾기</a>
				</c:if>
				<c:if test="${fn:length(list) ne 1}">
					<a href="#" class="button scrolly leftBtn" id="mapBtn">근처맛집찾기</a>
					<a href="#" class="button scrolly rightBtn" id="worldCupBtn">아무거나 월드컵</a>
				</c:if>
			    
			</c:if>
			<c:if test="${fn:length(list) eq 0}">
				<div class="type-contents">
					<div class="swiper-slide" style="background: none;">
						<label class="button style2 scrolly foodListBtn moreFoodBtn"  style="right: 0px;height:150px">
							입맛이 까다로우시네요.<br>다시 한번 시도해 보세요.<br><br> 더 많은 메뉴 보기 >
						</label>
						
					</div>
					<div class="swiper-container type-contents">
						<div class="swiper-wrapper">
							<c:forEach var="moreList" items="${moreList}" varStatus="status">
								<div class="swiper-slide moreFoodList" style="background: none;display:none;">
									<label class="button style2 scrolly foodListBtn" name="foodBtn" for="foodValue1" style="right: 0px;">
										<input type="hidden" name="foodValue" id="foodValue${status.index}" value="${moreList.FOOD_CD}">${moreList.CODE_NAME}<br>
										<input type="hidden" name="foodNameValue" id="foodNameValue${status.index}" value="${moreList.CODE_NAME}">
										<img src="${moreList.IMG}" onerror="this.src='/images/common/no_image.jpg'" class="foodImg2">
									</label>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<a href="#" class="button scrolly leftBtn" id="mainBtn">처음으로</a>
			    <a href="#" class="button scrolly rightBtn" id="randomWorldCupBtn">랜덤 월드컵</a>
			</c:if>
			<div class="moreFoodListContainer" style="height:350px; display:none;">
				<div class="swiper-wrapper">
				<c:forEach var="list" items="${list}" varStatus="status">
					<div class="swiper-slide" style="background: none;">
						<label class="button style2 scrolly foodListBtn" name="foodBtn" for="foodValue1" style="right: 0px;">
							<input type="hidden" name="foodValue" id="foodValue${status.index}" value="${list.FOOD_CD}">${list.CODE_NAME}<br>
							<input type="hidden" name="foodNameValue" id="foodNameValue${status.index}" value="${list.CODE_NAME}">
							<img src="${list.IMG}" onerror="this.src='/images/common/no_image.jpg'" class="foodImg2">
						</label>
					</div>
				</c:forEach>
				<c:forEach var="moreList" items="${moreList}" varStatus="status">
					<div class="swiper-slide moreFoodList" style="background: none;">
						<label class="button style2 scrolly foodListBtn" name="foodBtn" for="foodValue1" style="right: 0px;">
							<input type="hidden" name="foodValue" id="foodValue${status.index}" value="${moreList.FOOD_CD}">${moreList.CODE_NAME}<br>
							<input type="hidden" name="foodNameValue" id="foodNameValue${status.index}" value="${moreList.CODE_NAME}">
							<img src="${moreList.IMG}" onerror="this.src='/images/common/no_image.jpg'" class="foodImg2">
						</label>
					</div>
				</c:forEach>
				</div>
				<!-- Add Pagination -->
				<c:if test="${fn:length(list) ne 1}">
		        <div class="swiper-pagination"></div>
		        <!-- Add Arrows -->
		        <div class="swiper-button-next"></div>
		        <div class="swiper-button-prev"></div>
		        </c:if>
			</div>
		</div>
		
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	</body>
</html>
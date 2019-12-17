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
			<input type="hidden" id="foodNm" name="foodNm" value=""/>
			<input type="hidden" id="typeData" name="typeData" value="${typeData}"/>
			<input type="hidden" id="stepData" name="stepData" value="${stepData}"/>
		</form>
		<div id="middle">
    		<p id="typeTitle">결과 ${fn:length(list)}개</p>
    		<c:if test="${fn:length(list) ne 0}">
				<div class="swiper-container" id="type-contents">
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
				<div id="type-contents">
					<div class="swiper-slide" style="background: none;">
						<label class="button style2 scrolly foodListBtn"  style="right: 0px;height:150px">
							입맛이 까다로우시네요.<br>다시 한번 시도해 보세요.
						</label>
					</div>
				</div>
				<a href="#" class="button scrolly leftBtn" id="mainBtn">처음으로</a>
			    <a href="#" class="button scrolly rightBtn" id="randomWorldCupBtn">랜덤 월드컵</a>
			</c:if>
			
		</div>
		
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	</body>
</html>
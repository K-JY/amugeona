<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
	<head>
		<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
	</head>
	<body class="is-preload">
		<script type="text/javascript" src="/js/amugeona/searchRestaurant.js"></script>
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
		<!-- Banner -->
		<form id="frm" action="/amu/worldCup.do" method="POST">
			<input type="hidden" id="data" name="data" value="${foodNm}"/>
		</form>
		<div id="middle">
    		<p id="typeTitle">${foodNm} 주변 음식점</p>
			<div class="swiper-container" id="type-contents">
				<div class="swiper-wrapper">
				<c:forEach var="list" items="${list}" varStatus="status">
					<div class="swiper-slide" style="background: none;">
						<label class="button style2 scrolly foodListBtn" name="foodBtn" for="foodValue1" style="right: 0px;">
							<input type="hidden" id="foodValue1" value="${list.FOOD_CD}">${list.CODE_NAME}<br>
							<img src="${list.IMG}" onerror="this.src='/images/common/no_image.jpg'" class="foodImg2">
						</label>
					</div>
				</c:forEach>
				</div>
				<!-- Add Pagination -->
		        <div class="swiper-pagination"></div>
		        <!-- Add Arrows -->
		        <div class="swiper-button-next"></div>
		        <div class="swiper-button-prev"></div>
			</div>
			<a href="#" class="button scrolly centerBtn mt10" id="">근처맛집찾기</a>
		    <br/>
		    <a href="#" class="button scrolly centerBtn mt20" id="nextBtn">아무거나 월드컵</a>
		</div>
		
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	</body>
</html>
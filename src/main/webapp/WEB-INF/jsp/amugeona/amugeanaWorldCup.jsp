<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
	<head>
		<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
	</head>
	<body class="is-preload">
		<script type="text/javascript" src="/js/amugeona/amugeonaWorldCup.js"></script>
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
		<!-- Banner -->
		<c:forEach var="list" items="${list}" varStatus="status">
			<input type="hidden" id="food${status.index}" name="foodData" value="${list.CODE_NAME}"/>
		</c:forEach>
		<section id="middle">
			<form id="frm" action="">
				<input type="hidden" name="foodData" id="foodData1" value="FD004|치킨|/images/food/chicken.jpg" />
				<input type="hidden" name="foodData" id="foodData2" value="FD002|햄버거|/images/food/hamburger.jpg" />
				<input type="hidden" name="foodData" id="foodData3" value="FD020|설렁탕|/images/food/sulrangtang.jpg" />
				<input type="hidden" name="foodData" id="foodData4" value="FD021|커리|/images/food/curry.jpg" />
				<input type="hidden" name="foodData" id="foodData5" value="FD022|쌀국수|/images/food/riceNoodle.jpg" />
			</form>
			<p id="typeTitle">메뉴 월드컵</p>
			<section id="type-contents">
				
			</section>
			
		</section>
			
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
		
	</body>
</html>
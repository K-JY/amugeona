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
				<input type="hidden" name="data" id="data" />
			</form>
			<p id="typeTitle">메뉴 월드컵</p>
			<section id="type-contents">
				<label class="button style2 scrolly foodBtn" name="foodBtn" for="foodValue2">
					<input type="hidden" id="foodValue2" value=""/>
					햄버거<br/><img src="/images/food/hamburger.jpg" class="foodImg">
				</label>
				
				<label class="button style2 scrolly foodBtn" name="typeBtn" for="foodValue2">
					<input type="hidden" id="foodValue2" value=""/>
					치킨<br/><img src="/images/food/chicken.jpg" class="foodImg">
				</label>
			</section>
			
		</section>
			
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />

	</body>
</html>
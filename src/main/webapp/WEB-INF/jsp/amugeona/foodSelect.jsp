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
		<form id="frm" action="/amu/worldCup.do" method="POST">
				<input type="hidden" id="data" name="data" value="<c:forEach var="list" items="${list}" varStatus="status">${list.FOOD_CD}|</c:forEach>"/>
		</form>
		<section id="middle">
			<p id="typeTitle"></p>
			<section id="type-contents">
				<c:forEach var="list" items="${list}" varStatus="status">
					<a href="/amu/typeSelect.do" class="button style2 scrolly mt20">${list.CODE_NAME}</a>
				</c:forEach>
			</section>
			<section id="btn-list">
				<a href="#" class="button scrolly mt20" id="nextBtn" name="typeBtn">아무거나 월드컵</a>
			</section>
		</section>
		
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
	<head>
		<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
	</head>
	<body class="is-preload">
		<script type="text/javascript" src="/js/amugeona/worldCup.js"></script>
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
		<!-- Banner -->
		<c:forEach var="list" items="${list}" varStatus="status">
			<input type="hidden" id="food${status.index}" name="foodData" value="${list.CODE_ID}|${list.CODE_NAME}|${list.IMG}"/>
		</c:forEach>
		<section id="middle">
			<p id="typeTitle">메뉴 월드컵</p>
			<section id="type-contents">
			</section>
			
		</section>
			
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
		
	</body>
</html>
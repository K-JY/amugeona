<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
	<head>
		<jsp:include page="/WEB-INF/jsp/common/head.jsp"/>
	</head>
	<body class="is-preload">
		<script type="text/javascript" src="/js/main.js?ver=1.1"></script>
		<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
		<!-- Banner -->
		<section id="middle">
			<img class="bannerImg" src="/images/common/main_icon.png"/>
			<p>오늘 저녁 무엇을 먹을지 고민이라면.</p>
			<p>내 마음에 딱 드는 메뉴를 골라드립니다..</p>
			<footer>
				<a href="#" class="button scrolly leftBtn" id="menuBtn">메뉴고르기</a>
				<a href="#" class="button scrolly rightBtn" id="randomMenuBtn">랜덤메뉴고르기</a>
			</footer>
		</section>
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
	</body>
</html>
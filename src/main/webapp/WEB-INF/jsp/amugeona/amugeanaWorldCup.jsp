<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
	<head>
		<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
	</head>
	<body class="is-preload">
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
		<!-- Banner -->
		<section id="middle">
			<form id="frm" action="">
				<input type="hidden" name="data" id="data" />
			</form>
			<p id="typeTitle"></p>
			<section id="type-contents">
				<c:forEach var="list" items="${list}" varStatus="status">
					<a href="/amu/typeSelect.do" class="button style2 scrolly">${list.CODE_NAME}</a>
				</c:forEach>
			</section>
			<section id="btn-list">
				<label class="button scrolly" id="nextBtn" name="typeBtn">아무거나 월드컵</label>
			</section>
		</section>
			
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />

	</body>
</html>
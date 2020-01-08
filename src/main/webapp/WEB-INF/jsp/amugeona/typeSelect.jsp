<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
	<head>
		<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
	</head>
	<body class="is-preload">
		<script type="text/javascript" src="/js/amugeona/typeSelect.js?ver=1.1"></script>
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
		<!-- Banner -->
		<div id="middle">
			<form id="frm" action="/amu/foodSelect.do" method="POST">
				<input type="hidden" name="typeData" id="typeData" />
				<input type="hidden" name="categoryData" id="categoryData" />
				<input type="hidden" name="stepData" id="stepData" />
			</form>
			<p id="typeTitle">주메뉴</p>
			<div class="type-contents">
				<c:forEach var="list" items="${list}" varStatus="status">
					<label class="button style2 scrolly typeBtn" name="typeBtn" for="${list.PARENT_CATEGORY}${status.index}">${list.CODE_NAME}</label>
					<input type="radio" class="hidden" name="typeRadioBox" id="${list.PARENT_CATEGORY}${status.index}" value="${list.TYPE_CD}_${list.TYPE_CATEGORY}_${list.GROUP_ID}">
				</c:forEach>
				<c:if test="${(fn:length(list)%2) eq 1}">
					<label class="button scrolly " id="noneTypeBtn">null</label>
				</c:if>
				
			</div>
			<div id="btn-list">
				<!-- <label class="button scrolly rightBtn" id="nextBtn" name="typeBtn">다음 ></label> -->
			</div> 
		</div>
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />

	</body>
</html>
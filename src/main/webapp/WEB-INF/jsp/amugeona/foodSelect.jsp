<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<!--
	Overflow by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
	<head>
		<title>아무거나를 정해드립니다.</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/css/main.css?201912021604" />
		<noscript><link rel="stylesheet" href="/css/noscript.css?201912021504" /></noscript>
		<script type="text/javascript" src="/js/lib/jquery-3.4.1.min.js"></script>
		
	</head>
	<body class="is-preload">
		<section id="lnb">
			<h2><a href="/">AMUGEONA</a></h2>
		</section>
		<!-- Banner -->
			<section id="middle">
				<form id="frm" action="">
					<input type="hidden" name="data" id="data" />
				</form>
				<p id="typeTitle">주메뉴</p>
				<section id="type-contents">
					<c:forEach var="list" items="${list}" varStatus="status">
						<label class="button style2 scrolly typeBtn" name="typeBtn">${list.CODE_NAME}</label>
					</c:forEach>
					
					
				</section>
			</section>
		<section id="footer">
			<div class="copyright">
				<ul class="menu">
					<li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="/">AMUGEONA</a></li>
				</ul>
			</div>
		</section>

	</body>
</html>
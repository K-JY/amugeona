<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>admin</title>
<script type="text/javascript" src="/js/lib/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/js/lib/snowfall.jquery.js"></script>
<script type="text/javascript" src="/js/init.js"></script>
</head>
<body>
<h2>타입관리</h2>
타입 이름 : <input type="text" id="typeName" name="typeName"/>
타입 카테고리 : 
<select id="parentCategory">
	<option>선택</option>
</select>
상위 카테고리 : 
<select id="parentCategory">
	<option>선택</option>
</select>
<button id="insertBtn" value="등록"></button>

</body>
</html>
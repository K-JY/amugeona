<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <title>키워드로 장소검색하고 목록으로 표출하기</title>
    <style>

</style>
<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
</head>
<body class="is-preload">
	<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<div id="middle" style="height:700px;">
		<div class="map_wrap">
		    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
			<input type="hidden" value="쌀국수" id="keyword" size="15"> 
		    <div id="menu_wrap" class="bg_white">
		       
		        <div class="swiper-container" class="map-contents">
		        <ul id="placesList" class="swiper-wrapper"></ul>
		        <!-- Add Pagination -->
		        <div class="swiper-pagination"></div>
		        <!-- Add Arrows -->
		        <div class="swiper-button-next"></div>
		        <div class="swiper-button-prev"></div>
		        </div>
		        <div id="pagination"></div>
		    </div>
		</div>
		<div id="btn-list" style="margin-top: 240px;">
			<label class="button scrolly" id="kakaoBtn" name="typeBtn">카카오 맵에서 자세히 보기</label>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
<script>
init.ready = function(){
	var currentOS = "else";
    var mobile = (/iphone|ipad|ipod|android/i.test(navigator.userAgent.toLowerCase()));
    alert(navigator.userAgent.toLowerCase());
    
    if (mobile) {
        var userAgent = navigator.userAgent.toLowerCase();
        if (userAgent.search("android") > -1){
        	if(userAgent.search("chrome") < 0){
        		currentOS = "android";
        	}
        }
        else if ((userAgent.search("iphone") > -1) || (userAgent.search("ipod") > -1) || (userAgent.search("ipad") > -1))
            currentOS = "ios";
        else
            currentOS = "else";
    }

    
    if(currentOS == "android"){
        // Android
        window.open("intent://www.menupick.shop#Intent;scheme=https;package=com.android.chrome;end");
    }
}
</script>
</body>
</html>
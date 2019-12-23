<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<jsp:include page="/WEB-INF/jsp/common/head.jsp" />
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8ef7de72f55e391696ebbe3bdbb83354&libraries=services"></script>
</head>
<body class="is-preload">
	<!-- <script type="text/javascript" src="/js/amugeona/mapList.js"></script> -->
	<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
	<form id="backFrm" action="/amu/foodSelect.do">
		<input type="hidden" id="typeData" name="typeData" value="${typeData}"/>
		<input type="hidden" id="stepData" name="stepData" value="${stepData}"/>
	</form>
	<input type="hidden" value="${foodCd }" id="foodCd"/>
	<div id="middle" style="height:700px;">
		<div class="map_wrap">
		    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
			<input type="hidden" value="${foodNm}" id="keyword" size="15"> 
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
			<label class="button scrolly" id="kakaoMapBtn" name="typeBtn">카카오 맵에서 자세히 보기</label>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
	<script>
	var markers = [];

	var clickLine;

	var currentPosition; // 현재 위치 
	var firstPosition; // 첫번째 보여줄 위치
	var mapContainer = document.getElementById('map');  // 지도를 표시할 div 
	var mapOption;  // 맵 옵션
	var kakaoMapUrl; // 카카오맵 이동 url
	// 지도를 생성합니다    
	var map; 

	// 장소 검색 객체를 생성합니다
	var ps = new kakao.maps.services.Places();  

	// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({zIndex:1});
	// 키워드로 장소를 검색합니다
	//searchPlaces();

	init.ready = function(){
		common.backUrl = function(){
			if($("#typeData").val() == ""){
				location.href="/main.do";
				return;
			}
			$("#backFrm").submit();
		}
		common.share.url="https://www.menupick.shop/amu/mapList.do?foodCd="+$("#foodCd").val();
		if (navigator.geolocation) { // GPS를 지원하면
		    navigator.geolocation.getCurrentPosition(function(position) {
				currentPosition = new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude);
		      
				mapOption = {
					center: currentPosition, // 지도의 중심좌표
					level: 3 // 지도의 확대 레벨
				}; 
				
				map = new kakao.maps.Map(mapContainer, mapOption); 
				map.setDraggable(false); // 지도 이동 금지
				
				searchPlaces();
				
				kakaoMapUrl = $(".swiper-slide-active input[name=placeUrl]").val();
				$("#kakaoMapBtn").on("click",function(){
					location.href = kakaoMapUrl;
				})
		      
		    }, function(error) {
		      console.error(error);
		    }, {
		      enableHighAccuracy: false,
		      maximumAge: 0,
		      timeout: Infinity
		    });
		  } else {
		    alert('GPS를 지원하지 않습니다');
		  }
	}
	// 키워드 검색을 요청하는 함수입니다
	function searchPlaces() {

	    var keyword = document.getElementById('keyword').value;

	    if (!keyword.replace(/^\s+|\s+$/g, '')) {
	        alert('키워드를 입력해주세요!');
	        return false;
	    }

	    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
	    ps.keywordSearch( keyword, placesSearchCB,{
	    	location : currentPosition,
	    	radius : 1000,
	    	sort : kakao.maps.services.SortBy.DISTANCE
	    }); 
	}

	// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
	function placesSearchCB(data, status, pagination) {
	    if (status === kakao.maps.services.Status.OK) {

	        // 정상적으로 검색이 완료됐으면
	        // 검색 목록과 마커를 표출합니다
	        displayPlaces(data);

	        // 페이지 번호를 표출합니다
	        displayPagination(pagination);
	        
	        rolling();
	        
	        kakaoMapUrl = $(".swiper-slide-active input[name=placeUrl]").val();
	        setMapTwoPosition(currentPosition,firstPosition);
	        

	    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

	        common.alertPop('','검색 결과가 존재하지 않습니다.',function(){
	        	$("#backBtn").click();
	        });
	        return;

	    } else if (status === kakao.maps.services.Status.ERROR) {

	    	common.alertPop('','검색 결과 중 오류가 발생했습니다.');
	        return;

	    }
	}

	// 검색 결과 목록과 마커를 표출하는 함수입니다
	function displayPlaces(places) {

	    var listEl = document.getElementById('placesList'), 
	    menuEl = document.getElementById('menu_wrap'),
	    fragment = document.createDocumentFragment(), 
	    bounds = new kakao.maps.LatLngBounds(), 
	    listStr = '';
	    
	    // 검색 결과 목록에 추가된 항목들을 제거합니다
	    removeAllChildNods(listEl);

	    // 지도에 표시되고 있는 마커를 제거합니다
	    removeMarker();
	    firstPosition = new kakao.maps.LatLng(places[0].y, places[0].x);
	    
	    for ( var i=0; i<places.length; i++ ) {

	        // 마커를 생성하고 지도에 표시합니다
	        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
	            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	        // LatLngBounds 객체에 좌표를 추가합니다
	        bounds.extend(placePosition);

	        fragment.appendChild(itemEl);
	    }

	    // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
	    listEl.appendChild(fragment);
	    menuEl.scrollTop = 0;
	}

	function setMapTwoPosition(placePosition1, placePosition2){
		deleteClickLine(); // 선 삭제
		removeMarker();
	    
	    
		addMarker(placePosition1); // 현재위치 마커 저장
	    addMarker(placePosition2); // 장소 위치 마커 저장
	    showMarkers(); // 마커 보이기
	    bounds = new kakao.maps.LatLngBounds(); // 보일 범위 설정
	    bounds.extend(placePosition1); // 현재위치 설정
	    bounds.extend(placePosition2); // 장소 위치 설정
	    map.setBounds(bounds); // 현재위치, 장소위치 다 보이게 범위 설정
	    
	    clickLine = new kakao.maps.Polyline({
	        map: map, // 선을 표시할 지도입니다 
	        path: [], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
	        strokeWeight: 3, // 선의 두께입니다 
	        strokeColor: '#db4040', // 선의 색깔입니다
	        strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
	        strokeStyle: 'solid' // 선의 스타일입니다
	    });
	    
	    var path = clickLine.getPath(); // 경로지정 객체 선언
	    path.push(placePosition1); // 현재위치 경로 설정
	    path.push(placePosition2); // 장소 위치 경로 설정
	    
	    clickLine.setPath(path); // 경로 그리기
	    
	    
	    var distance = Math.round(clickLine.getLength()); // 길이 계산
	    displayCircleDot(placePosition2, distance); // 점 그리기
	}

	function displayCircleDot(position, distance) {

	    // 클릭 지점을 표시할 빨간 동그라미 커스텀오버레이를 생성합니다
	    var circleOverlay = new kakao.maps.CustomOverlay({
	        content: '<span class="dot"></span>',
	        position: position,
	        zIndex: 1
	    });

	    // 지도에 표시합니다
	    circleOverlay.setMap(map);

	    $(".swiper-slide-active .distance").html("("+distance+" m)");
	}
	// 검색결과 항목을 Element로 반환하는 함수입니다
	function getListItem(index, places) {
	  
	    var el = document.createElement('li'),
	    itemStr =  '<div class="info">' +
	                '   <h5>' + places.place_name + '</h5><font class="distance"></font>';
	                
	    itemStr += '<input type="hidden" name="placeUrl" value="'+places.place_url+'"/>';
	    itemStr += '<input type="hidden" name="x" value="'+places.x+'"/>';
	    itemStr += '<input type="hidden" name="y" value="'+places.y+'"/>';

	    if (places.road_address_name) {
	        itemStr += '    <span>' + places.road_address_name +
	        '</span>' +'   <span class="jibun gray">' +  places.address_name  + '</span>';
	    } else {
	        itemStr += '    <span>' +  places.address_name  + '</span>'; 
	    }
	                 
	      itemStr += '  <span class="tel">' + places.phone  + '</span>';
	      itemStr += '</div>';           

	    el.innerHTML = itemStr;
	    el.className = 'item swiper-slide';

	    return el;
	}

	// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
	function addMarker(position) {
	    
	    // 마커를 생성합니다
	    var marker = new kakao.maps.Marker({
	        position: position,
	        test : "택스트"
	    });

	    // 마커가 지도 위에 표시되도록 설정합니다
	    marker.setMap(map);
	    
	    // 생성된 마커를 배열에 추가합니다
	    markers.push(marker);
	}

	// 지도 위에 표시되고 있는 마커를 모두 제거합니다
	function removeMarker() {
	    for ( var i = 0; i < markers.length; i++ ) {
	        markers[i].setMap(null);
	    }   
	    markers = [];
	}

	// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
	function displayPagination(pagination) {
	    var paginationEl = document.getElementById('pagination'),
	        fragment = document.createDocumentFragment(),
	        i; 

	    // 기존에 추가된 페이지번호를 삭제합니다
	    while (paginationEl.hasChildNodes()) {
	        paginationEl.removeChild (paginationEl.lastChild);
	    }

	    for (i=1; i<=pagination.last; i++) {
	        var el = document.createElement('a');
	        el.href = "#";
	        el.innerHTML = i;

	        if (i===pagination.current) {
	            el.className = 'on';
	        } else {
	            el.onclick = (function(i) {
	                return function() {
	                    pagination.gotoPage(i);
	                }
	            })(i);
	        }

	        fragment.appendChild(el);
	    }
	    paginationEl.appendChild(fragment);
	}

	// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
	// 인포윈도우에 장소명을 표시합니다
	function displayInfowindow(marker, title) {
	    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

	    infowindow.setContent(content);
	    infowindow.open(map, marker);
	}

	 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
	function removeAllChildNods(el) {   
	    while (el.hasChildNodes()) {
	        el.removeChild (el.lastChild);
	    }
	}
	 
	function rolling(){
	 var swiper = new Swiper('.swiper-container', {
	        paginationClickable: true,
	        nextButton: '.swiper-button-next',
	        prevButton: '.swiper-button-prev',
	        spaceBetween: 30,
	        slidesPerView: 1.5,
	        centeredSlides: true,
	        onSlideChangeEnd : function(swiper){
	        	var x = $(".swiper-slide-active input[name=x]").val();
	        	var y = $(".swiper-slide-active input[name=y]").val();
	        	kakaoMapUrl = $(".swiper-slide-active input[name=placeUrl]").val();
	        	setMapTwoPosition(currentPosition, new kakao.maps.LatLng(y,x));
	        }
	    });
	}
	 
	//배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
	function setMarkers(map) {
	    for (var i = 0; i < markers.length; i++) {
	        markers[i].setMap(map);
	    }            
	}

	 // "마커 보이기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에 표시하는 함수입니다
	 function showMarkers() {
	     setMarkers(map)    
	 }

	 // "마커 감추기" 버튼을 클릭하면 호출되어 배열에 추가된 마커를 지도에서 삭제하는 함수입니다
	 function hideMarkers() {
	     setMarkers(null);    
	 }
	 
	 function deleteClickLine() {
		$(".dot").remove();
	    if (clickLine) {
	        clickLine.setMap(null);    
	        clickLine = null;        
	    }
	}
	</script>
</body>
</html>
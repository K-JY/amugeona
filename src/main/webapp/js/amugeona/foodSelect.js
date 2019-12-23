init.ready = function(){
	food.click();
	food.init();
	common.share.url = "https://www.menupick.shop/amu/foodSelect.do?typeData="+$("#typeData").val()+"&stepData="+$("#stepData").val();
	common.backUrl = function(){
		location.href = "/amu/typeSelect.do";
	}
}

var food = {
	click : function(){
		$("#worldCupBtn").on("click",function(){
			$("#frm").attr("action","/amu/worldCup.do");
			$("#frm").submit();
		});
		
		$("#mapBtn").on("click",function(){
			var foodList = $(".foodListBtn.on");
			if(foodList.length == 0){
				common.alertPop("","메뉴를 선택해 주세요.");
				return;
			}
			
			var foodValue = $(".foodListBtn.on input[name=foodValue]").val();
			$("#foodCd").val(foodValue);
			$("#frm").attr("action","/amu/mapList.do");
			$("#frm").submit();
		});
		
		$(".foodListBtn").on("click",function(){
			$(".foodListBtn").removeClass("on");
			$(this).addClass("on");
		});
		
		$("#randomWorldCupBtn").on("click",function(){
			location.href="/amu/randomWorldCup.do";
		});
		
		$("#mainBtn").on("click",function(){
			location.href="/main.do";
		});
	},
	init : function(){
		var swiper = new Swiper('.swiper-container', {
	        paginationClickable: true,
	        nextButton: '.swiper-button-next',
	        prevButton: '.swiper-button-prev',
	        spaceBetween: 30,
	        slidesPerView: 1.5,
	        centeredSlides: true
	    });
	}
}
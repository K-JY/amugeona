init.ready = function(){
	food.click();
	food.init();
}

var food = {
	click : function(){
		$("#worldCupBtn").on("click",function(){
			$("#worldCupFrm").submit();
		});
		
		$("#mapBtn").on("click",function(){
			var foodList = $(".foodListBtn.on");
			if(foodList.length == 0){
				alert("메뉴를 선택해 주세요.");
				return;
			}
			
			var foodValue = $(".foodListBtn.on input[name=foodNameValue]").val();
			$("#foodName").val(foodValue);
			$("#mapListFrm").submit();
		});
		
		$(".foodListBtn").on("click",function(){
			$(".foodListBtn").removeClass("on");
			$(this).addClass("on");
		})
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
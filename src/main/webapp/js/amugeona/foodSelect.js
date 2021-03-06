init.ready = function(){
	food.click();
	food.init();
	common.share.url = "https://www.menupick.shop/amu/foodSelect.do?typeData="+$("#typeData").val()+"&stepData="+$("#stepData").val();
	common.backUrl = function(){
		common.loading.start();
		location.href = "/amu/typeSelect.do";
	}
}

var food = {
	click : function(){
		$(document).on("click","#worldCupBtn",function(){
			$("#frm").attr("action","/amu/worldCup.do");
			common.loading.start();
			$("#frm").submit();
		});
		
		$(document).on("click","#mapBtn",function(){
			var foodList = $(".foodListBtn.on");
			if(foodList.length == 0){
				common.alertPop("","메뉴를 선택해 주세요.");
				return;
			}
			
			var foodValue = $(".foodListBtn.on input[name=foodValue]").val();
			$("#foodCd").val(foodValue);
			$("#frm").attr("action","/amu/mapList.do");
			common.loading.start();
			$("#frm").submit();
		});
		
		$(document).on("click",".foodListBtn",function(){
			$(".foodListBtn").removeClass("on");
			$(this).addClass("on");
		});
		
		$(document).on("click","#randomWorldCupBtn",function(){
			location.href="/amu/randomWorldCup.do";
		});
		
		$(document).on("click","#mainBtn",function(){
			location.href="/main.do";
		});
		
		$(document).on("click",".moreFoodBtn", function(){
			$(".moreFoodBtn").closest(".swiper-slide").effect("blind",function(){
				$(".type-contents").remove();
				$(".moreFoodListContainer").addClass("swiper-container").addClass("type-contents");
				$(".moreFoodListContainer").show();
				
				var swiper = new Swiper('.swiper-container', {
					spaceBetween: 2,
			        slidesPerView: 3.1,
			        centeredSlides: true,
			        direction : 'vertical',
			        initialSlide: $("#listLength").val()
			    });
				
				var length = $("#moreListLength").val()*1 + $("#listLength").val()*1;
				
				$("#typeTitle").html("결과 "+length+"개");
				$(".leftBtn").remove();
				$(".rightBtn").remove();
				$(".centerBtn").remove();
				var html = '<div style="margin-top:10px;" class="btnDiv">'; 
				html += '<a href="#" class="button scrolly leftBtn" id="mapBtn">근처맛집찾기</a>';
				html += '<a href="#" class="button scrolly rightBtn" id="worldCupBtn">아무거나 월드컵</a>';
				html += '</div>';
					
				$(".type-contents").after(html);
				
				$("#data").val($("#moreData").val());
				
			});
			
		})
	},
	init : function(){
		var swiper = new Swiper('.swiper-container', {
	        spaceBetween: 2,
	        slidesPerView: 3.1,
	        centeredSlides: true,
	        direction : 'vertical',
	        initialSlide: 0
	    });
	}
}
init.ready = function(){
	main.click();
}


var main = {
	click : function(){
		$(document).on("click","#size1div, #size2div",function(){
			$("#size1div").removeClass("on");
			$("#size2div").removeClass("on");
			
			$(this).addClass("on");
			
			if($(this).attr("id") == "size1div"){
				$("#size1").prop("checked",true);
			}else{
				$("#size2").prop("checked",true);
			}
		});
		
		$(document).on("click","#randomMenuBtn",function(){
			var html = htmlTemplete.randomMenu();
			$("#footer").after(html);
			$(".randomPop").hide();
			$(".randomPop").show("blind",500);
			$("body").addClass("disable");
		});
		
		$(document).on("click",".randomPop > .dimBg",function(){
			$(".randomPop").effect("fold",500,function(){
				$(".randomPop").remove();
			});
			
			$("body").removeClass("disable");
		});
		
		$(document).on("click","#menuBtn",function(){
			common.loading.start();
			location.href="/amu/typeSelect.do";
		});
		
		$(document).on("click","#randomMenuGoBtn",function(){
			common.loading.start();
			$("#frm").submit();
		});
		
		
	}
}

var htmlTemplete  = {
	randomMenu : function(){
		var html = "";
		html += '<div class="dim-layer randomPop" style="display: block;">';
		html += '<div class="dimBg"></div>';
		html += '<div id="layer2" class="pop-layer">';
		html += '<div class="pop-container">';
		html += '<div class="pop-conts">';
		html += '<p class="pop-title">랜덤메뉴고르기</p>';
		html += '<p style="margin-top: 40px;margin-bottom: 0px;font-size: 15px;">선택할 메뉴의 갯수를 골라주세요</p>';
		html += '<br/>';
		html += '<form id="frm" action="/amu/randomWorldCup.do" method="POST">'
		html += '<div class="button scrolly typeBtn redBorder on" id="size1div">8</div>';
		html += '<input type="radio" class="hidden" name="size" id="size1" value="8" checked>';
		html += '<div class="button scrolly typeBtn redBorder"  id="size2div">16</div>';
		html += '<input type="radio" class="hidden" name="size" id="size2" value="16">';
		html += '</form>'
		html += '</div>';
		html += '</div>';
		html += '<label class="button scrolly style2 on firstBtn" id="randomMenuGoBtn" name="typeBtn" style="font-size: 15px;">랜덤메뉴고르기</label>';
		html += '</div>';
		html += '</div>';
		
		return html;
	}
}
init.ready = function(){
	worldCup.init();
	common.backUrl = function(){
		if($("#typeData").val() == ""){
			location.href="/main.do";
		}else{
			$("#frm").attr('action','/amu/foodSelect.do');
			$("#frm").submit();
		}
		
	}
}

var worldCup = {
	food : new Array(),
	nextFoodList : new Array(),
	event: function(){
		$(document).on('click','#firstBtn',function(){
			location.href='/';
		});
		
		$(document).on('click','label[name="foodBtn"]',function(){
			worldCup.effect($(this));
		});
		
		$(document).on('click','#mapListBtn',function(){
			$("#frm").attr('action','/amu/mapList.do');
			$("#frm").submit();
		});
	},
	effectTime : 400,
	effect : function(element){
		var idx1 = $('label[name="foodBtn"]').index(element);
		var idx2;
		
		var idx = element.attr('for').replace('foodValue','');
		
		if(idx1 == 0){
			idx2 = 1;
		}else{
			idx2 = 0;
		}
		
		$(".foodBtn").eq(idx2).animate({
			width: "0px",
			height: "0px"
		},worldCup.effectTime);
		
		$(".foodBtn").eq(idx1).animate({
			width: "50%",
			height: "350px"
		},worldCup.effectTime,function(){
			$(".foodBtn").eq(idx1).animate({
				width: "45%",
				height: "300px"
			},worldCup.effectTime,function(){
				setTimeout("worldCup.click("+idx+")", worldCup.effectTime);
				
			})
		});
		
		boom.start({number:12,count:6});

	},
	init : function(){ // 최초 초기화
		worldCup.event();
		var foodList = $("input[name=foodData]");
		for(var i = 0;i<foodList.length;i++){
			var foodCode = foodList.eq(i).val().split('|')[0];
			var foodName = foodList.eq(i).val().split('|')[1];
			var foodImg = foodList.eq(i).val().split('|')[2];
			worldCup.food.push({
				foodCode : foodCode,
				foodName : foodName,
				foodImg : foodImg,
				flag : true,
				lv : 1
			});
		}
		
		if(worldCup.check() == 1 || worldCup.check() == 0){
			worldCup.completeFood();
		}
		
		worldCup.foodRender(worldCup.random(), worldCup.random());
		worldCup.lvView();
	},
	foodRender : function(idx1, idx2){
		if(idx1 == -1 || idx2 == -1){
			worldCup.completeFood();
		}
		var html = '';
		html += htmlTemplete.food(worldCup.food[idx1].foodCode, worldCup.food[idx1].foodName, worldCup.food[idx1].foodImg, idx1);
		html += htmlTemplete.food(worldCup.food[idx2].foodCode, worldCup.food[idx2].foodName, worldCup.food[idx2].foodImg, idx2);
		
		$('#type-contents').empty();
		$('#type-contents').append(html);
		
		$(".foodBtn").eq(0).animate({
			  left:0
			},worldCup.effectTime
		);
		
		$(".foodBtn").eq(1).animate({
			  right:0
			},worldCup.effectTime
		);
		
		worldCup.lvView();
	},
	random : function(){
		for(var i = 0;i<worldCup.food.length;i++){
			if(worldCup.food[i].flag == true){
				worldCup.food[i].flag = false;
				return i;
			}
		}
		return -1;
	},
	check : function(){
		var cnt = 0;
		for(var i = 0;i<worldCup.food.length;i++){
			if(worldCup.food[i].flag == true){
				cnt++;
			}
		}
		return cnt;
	},
	completeFood : function(){
		var completeFood = worldCup.food[0];
		var html = htmlTemplete.complete(completeFood.foodName, completeFood.foodImg);
		$("#foodNm").val(completeFood.foodName);
		$("#footer").after(html);
		common.ajax('/amu/ajaxSelectFoodLog.do',{foodNm:$("#foodNm").val()});
		boom.start();
	},
	click : function(idx){
		worldCup.nextFoodList.push({
			foodCode : worldCup.food[idx].foodCode,
			foodName : worldCup.food[idx].foodName,
			foodImg : worldCup.food[idx].foodImg,
			flag : true,
			lv : worldCup.food[idx].lv+1
			
		});
		
		if(worldCup.check() == 1){ // 월드컵 중 하나가 남았을 경우에 부전승 올림

			var lastIdx;
			for(var i = 0;i<worldCup.food.length;i++){
				if(worldCup.food[i].flag == true){
					lastIdx = i;
				}
			}
			
			worldCup.nextFoodList.push({
				foodCode : worldCup.food[lastIdx].foodCode,
				foodName : worldCup.food[lastIdx].foodName,
				foodImg : worldCup.food[lastIdx].foodImg,
				flag : true,
				lv : worldCup.food[lastIdx].lv+1
				
			});
			
			worldCup.changeArray();
			
		}else if(worldCup.check() == 0){ // 음식이 남은게 없을 때
			worldCup.changeArray();
		}
		
		worldCup.foodRender(worldCup.random(), worldCup.random())
	},
	changeArray : function(){
		worldCup.food = worldCup.nextFoodList;
		worldCup.nextFoodList = new Array();
	},
	lvView : function(){
		var result = worldCup.food.length;

		if(result == 2){
			$("#typeTitle").html('메뉴 월드컵 결승');
		}else{
			$("#typeTitle").html('메뉴 월드컵 '+result+'강');
		}
		
	}
}

var htmlTemplete = {
	food : function(foodCd, foodName, foodImg, idx){
		var html = '';
		html += '<label class="button style2 scrolly foodBtn" name="foodBtn" for="foodValue'+idx+'">';
		html += '<input type="hidden" id="foodValue'+idx+'" value="'+foodCd+'"/>';
		html += foodName+'<br/><img src="'+foodImg+'"onerror="this.src=\'/images/common/no_image.jpg\'"  class="foodImg2">';
		html += '</label>';

		return html;
	},
	complete : function(foodName, foodImg){
		var html = '';
		html += '<div class="dim-layer" style="display: block;">';
		html += '<div class="dimBg"></div>';
		html += '<div id="layer2" class="pop-layer" style="padding: 0px 20px;">';
		html += '<div class="pop-container" style="margin-bottom: 30px;">';
		html += '<div class="pop-conts">';
		html += '<img src="/images/common/1st.png" class="medalImg">';
		html += '<p class="pop-title">'+foodName+'</p><img src="'+foodImg+'" onerror="this.src=\'/images/common/no_image.jpg\'" class="foodImg1">';
		html += '</div>';
		html += '</div>';
		html += '<label class="button scrolly style2 on rightBtn" style="font-size: 15px;" id="firstBtn" name="typeBtn">처음으로</label>';
		html += '<label class="button scrolly style2 on leftBtn" style="font-size: 15px;" id="mapListBtn" name="typeBtn">근처맛집찾기</label>';
		html += '</div>';
		html += '</div>';
		
		return html;
	}
}


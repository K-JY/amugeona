var worldCup = {
	food : new Array(),
	nextFoodList : new Array(),
	init : function(){ // 최초 초기화
		var foodList = $("input[name=foodData]");
		for(var i = 0;i<foodList.length;i++){
			var foodCode = foodList.val().split('|')[0];
			var foodName = foodList.val().split('|')[1];
			var foodImg = foodList.val().split('|')[2];
			worldCup.food.push({
				foodCode : foodCode,
				foodName : foodName,
				foodImg : foodImg,
				flag : true,
				lv : 1
			});
		}
		
		if(worldCup.check() == 1 || worldCup.check() == 0){
			completeFood();
		}
		
		foodRender(worldCup.random(), worldCup.random)
	},
	foodRender : function(idx1, idx2){
		if(idx1 == -1 || idx2 == -1){
			completeFood();
		}
		var html = '';
		html += html.food(worldCup[idx1].food.foodCode, worldCup[idx1].food.foodName, worldCup[idx1].food.foodImg, idx1);
		html += html.food(worldCup[idx2].food.foodCode, worldCup[idx2].food.foodName, worldCup[idx2].food.foodImg, idx2);
		
		$("#type-contents").empty();
		$("#type-contents").append(html);
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
		
		foodRender(worldCup.random(), worldCup.random())
	},
	changeArray : function(){
		worldCup.food = worldCup.nextFoodList;
		worldCup.nextFoodList = new Array();
	}
}

var html = {
	food : function(foodCd, foodName, foodImg, idx){
		var html = '';
		html += '<label class="button style2 scrolly foodBtn" name="foodBtn" for="foodValue2">';
		html += '<input type="hidden" id="foodValue2" value="'+foodCd+'"/>';
		html += foodName+'<br/><img src="'+foodImg+'" class="foodImg">';
		html += '</label>';

		return html;
	}
}

init.ready = function(){
	
}
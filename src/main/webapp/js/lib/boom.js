var boom = {
	staticValue : {
		number : 8,
		length : 300,
		count : 20,
		speed : 800,
		interval : 50
	},
	number : 8,
	length : 300,
	count : 20,
	speed : 800,
	interval : 50,
	top : new Array(),
	left : new Array(),
	idx : 0,
	topList : [-1,-1,3,3],
	leftList : [0,2,2,-4],
	color : ["rgb(242, 71, 5)","rgb(242, 5, 5)","rgb(151, 242, 5)","rgb(20, 242, 5)","rgb(5, 242, 220)","rgb(5, 89, 242)","rgb(129, 5, 242)","rgb(242, 5, 224)","rgb(242, 5, 114)"],
	start : function(option){
		var height = window.innerHeight;
		var width = window.innerWidth;
		this.idx = 0;
		if(typeof(option) != "undefined"){
			this.number = (option.number==null)?this.staticValue.number:option.number;
			this.length = (option.length==null)?this.staticValue.length:option.length;
			this.count = (option.count==null)?this.staticValue.count:option.count;
			this.speed = (option.speed==null)?this.staticValue.speed:option.speed;
			this.interval = (option.interval==null)?this.staticValue.interval:option.interval;
		}else{
			this.number = this.staticValue.number;
			this.length = this.staticValue.length;
			this.count = this.staticValue.count;
			this.speed = this.staticValue.speed;
			this.interval = this.staticValue.interval;
		}
		
		
		this.boomHtml(height,width);
		
		
	},
	boomHtml : function(top, left){
/*		for(var j = 0;j<4;j++){
			for(var i = (this.number/4)*j;i<(this.number/4)*(j+1);i++){
				this.top.push((this.topList[j]+(i/2))*this.speed);
				this.left.push((this.leftList[j]+(i/2))*this.speed);
			}
		}*/
		
		for(var i = (this.number/4)*0;i<(this.number/4)*1;i++){
			this.top.push((-1+(i/2))*this.length);
			this.left.push((0+(i/2))*this.length);
		}
		
		for(var i = (this.number/4)*1;i<(this.number/4)*2;i++){
			this.top.push((-1+(i/2))*this.length);
			this.left.push((2-(i/2))*this.length);
		}
		
		for(var i = (this.number/4)*2;i<(this.number/4)*3;i++){
			this.top.push((3-(i/2))*this.length);
			this.left.push((2-(i/2))*this.length);
		}
		
		for(var i = (this.number/4)*3;i<(this.number/4)*4;i++){
			this.top.push((3-(i/2))*this.length);
			this.left.push(((-4)+(i/2))*this.length);
		}
		
		for(var j = 0;j<boom.count;j++){
			var newTop = Math.round( Math.random()*top );
			var newLeft = Math.round( Math.random()*left );

			var idx = j;
			for(var i = 0;i<this.number;i++){
				var html = '';
				html += '<div class="boom" name="boom'+idx+'"style="top:'+newTop+';left:'+newLeft+'"></div>';
				$("#footer").after(html);
			}
			
			setTimeout(function(){
				boom.moveBoom(boom.idx);
				boom.idx += 1;
			},this.interval*(idx));
		}
		var cnt = 0;
		/*var lightEffect = setInterval(function(){
			cnt++;
			if($(".dim-layer .dimBg").length != 0){
				if($(".dim-layer .dimBg").css("background-color") == "rgb(0, 0, 0)"){
					$(".dim-layer .dimBg").css("background-color", "rgba(0, 0, 0, 0.90)");
				}else{
					$(".dim-layer .dimBg").css("background-color", "rgb(0, 0, 0)");
				}
			}
			
			if(cnt == boom.count){
				clearInterval(lightEffect);
			}
			
		},this.interval)*/
		
		
		
	},
	moveBoom : function(idx){
		$("div[name=boom"+idx+"]").css("display","block");
		$("div[name=boom"+idx+"]").css("background",boom.color[idx%boom.color.length]);
		for(var i = 0;i<boom.number;i++){
			$("div[name=boom"+idx+"]").eq(i).animate({
				top:($("div[name=boom"+idx+"]").eq(i).css("top").replace("px","")*1)+boom.top[i],
				left:($("div[name=boom"+idx+"]").eq(i).css("left").replace("px","")*1)+boom.left[i]
			},this.speed,null,function(){
				$(this).remove();
			});
		}

		
	}
}